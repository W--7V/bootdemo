package springboot.demo.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import springboot.demo.service.NovelService;
import springboot.demo.service.flowcontrol.RedisFlowcontrol;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class CacheController {
    @Autowired
    JedisPool jedisPool;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    RedisFlowcontrol redisFlowcontrol;

    @Autowired
    NovelService novelService;

    private static Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

    @RequestMapping("/incrWithRedisLock")
    public void incrWithRedisLock(@RequestParam Integer id) throws InterruptedException {
        RLock lock = redissonClient.getLock("lock");
        lock.lock();
        novelService.incrementById(id);
        lock.unlock();
    }

    @RequestMapping("/incr")
    public void incr(@RequestParam Integer id) throws InterruptedException {
        novelService.incrementById(id);
    }

    @RequestMapping("/setKV")
    public void setKV(@RequestParam("key") String key, @RequestParam("val") String val) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, val);
        jedis.close();
    }

    @RequestMapping("/getKV")
    public String getKV(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        LOGGER.info(value);
        jedis.close();
        return value;
//        return "";
    }

    @RequestMapping("/publishEvent")
    public void publishEvent() {
//        System.out.println("publishEvent thread:" + Thread.currentThread().getName());
//        applicationContext.publishEvent(new MyEvent(this));
//        MyListener bean = (MyListener) applicationContext.getBean("myListener");
//        System.out.println(bean);
//        Jedis jedis = jedisPool.getResource();
//        jedis.incrBy("1", 3);
//        jedis.multi();
//        System.out.println(jedisPool.getResource().get("1"));
    }

    @RequestMapping("/click")
    public boolean testFlow() {
        return redisFlowcontrol.isActionAllowed("local", 5, 5);
    }

    @RequestMapping("/flow")
    public Object testFlowControl() {
        return redisFlowcontrol.countdown(Arrays.asList("sms"), Arrays.asList("1", "200", "60"));
    }
}
