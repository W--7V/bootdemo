package springboot.demo.controller;

//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import springboot.demo.service.flowcontrol.RedisFlowcontrol;
import springboot.demo.system.MyEvent;
import springboot.demo.system.MyListener;

import java.util.Arrays;

@RestController
public class CacheController {


//    @Autowired
//    JedisPool jedisPool;

    @Autowired
    ApplicationContext applicationContext;

//    @Autowired
//    private RedissonClient redissonClient;

    @Autowired
    RedisFlowcontrol redisFlowcontrol;


    @RequestMapping("/redisLock")
    public void redisLock() throws InterruptedException {
//        RLock lock = redissonClient.getLock("lock");
//        lock.lock();
//        Thread.sleep(30000);
//        lock.unlock();
    }

    @RequestMapping("/setKV")
    public void setKV(@RequestParam("key") String key, @RequestParam("val") String val) {
//        jedisPool.getResource().set(key, val);
    }

    @RequestMapping("/getKV")
    public String getKV(String key) {
//        return jedisPool.getResource().get(key);
		return "";
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
    public boolean testFlow(){
        return redisFlowcontrol.isActionAllowed("local",5,5);
    }

    @RequestMapping("/flow")
    public Object testFlowControl(){
        return redisFlowcontrol.countdown(Arrays.asList("sms"),Arrays.asList("10","20"));
    }
}
