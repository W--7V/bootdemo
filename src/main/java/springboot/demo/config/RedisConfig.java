package springboot.demo.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.*;

@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
//		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//		template.setConnectionFactory(factory);
//		//使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
//        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
//
//        ObjectMapper om = new ObjectMapper();
//        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jacksonSeial.setObjectMapper(om);
//
//        // 值采用json序列化
//        template.setValueSerializer(jacksonSeial);
//        //使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//
//        // 设置hash key 和value序列化模式
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(jacksonSeial);
//        template.afterPropertiesSet();
//
//		return template;
//	}

    //    	@Bean
    public JedisSentinelPool getJedisSentinelPool() {
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.1.104:26379");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTestOnBorrow(true);

        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, jedisPoolConfig);
        return pool;
    }

    @Bean
    public JedisPool getJedis() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.1.104", 6380, Protocol.DEFAULT_TIMEOUT, "123456");
//        System.out.println(jedisPool.getResource().get("1"));
        return jedisPool;
//        return new JedisPool(jedisPoolConfig, "127.0.0.1", Protocol.DEFAULT_PORT, Protocol.DEFAULT_TIMEOUT);
    }
}
