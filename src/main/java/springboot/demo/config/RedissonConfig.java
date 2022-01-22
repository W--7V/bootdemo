package springboot.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    String redisHost;

    @Value("${spring.redis.port}")
    Integer port;

    @Value("${spring.redis.password}")
    String password;

    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        // 单机模式 依次设置redis地址和密码
        config.useSingleServer().setAddress("redis://"+redisHost+":"+port).setConnectionMinimumIdleSize(5)
                .setConnectTimeout(10000).setConnectionPoolSize(10).setPassword(password);
        return Redisson.create(config);
    }
}
