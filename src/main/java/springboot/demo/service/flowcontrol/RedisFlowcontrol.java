package springboot.demo.service.flowcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Component
public class RedisFlowcontrol {
    @Autowired
    JedisPool jedisPool;

    public boolean isActionAllowed(String userId, int period, int maxCount) {
        // 生成唯一的key
        Jedis jedis = jedisPool.getResource();
        String key = String.format("hist:%s", userId);
        long nowTimeMillis = System.currentTimeMillis();
        // 使用管道
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        // 整理zset，删除时间窗口外的数据
        pipe.zremrangeByScore(key, 0, nowTimeMillis - period * 1000);
        Response<Long> count = pipe.zcard(key);

        if (count.get() < maxCount) {
            // 添加当前操作当zset中
            pipe.zadd(key, nowTimeMillis, "" + nowTimeMillis);
            pipe.expire(key, period + 1);
            pipe.exec();
            try {
                pipe.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        jedisPool.returnResource(jedis);

        return count.get() <= maxCount;
    }

    public Object countdown(List<String> keys, List<String> args) {
        URL scriptPath = this.getClass().getResource("/flowcontrol.lua");
        File scriptFile = null;
        try {
            scriptFile = new File(scriptPath.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String script = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFile))) {
            StringBuffer sbf = new StringBuffer();
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
                sbf.append("\n");
            }
            script = sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Jedis jedis = jedisPool.getResource();
        Object res = jedis.eval(script, keys, args);
        jedisPool.returnResource(jedis);

        return res;
    }
}
