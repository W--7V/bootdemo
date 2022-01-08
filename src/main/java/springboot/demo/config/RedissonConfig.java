package springboot.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

	@Bean
	public RedissonClient getRedissonClient() {
		Config config = new Config();
		// 单机模式 依次设置redis地址和密码
		config.useSingleServer().setAddress("redis://192.168.1.104:6380").setConnectionMinimumIdleSize(5)
				.setConnectTimeout(10000).setConnectionPoolSize(10).setPassword("123456");
		return Redisson.create(config);
	}
}
