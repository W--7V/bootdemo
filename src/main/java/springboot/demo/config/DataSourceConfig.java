package springboot.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource set() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		hikariDataSource.setJdbcUrl("jdbc:mysql://119.91.195.175:23306/library?charactorEncoding=utf-8&useUnicode=true&useSSL=false");
		hikariDataSource.setUsername("root");
		hikariDataSource.setPassword("mysql://23306");
		hikariDataSource.setMinimumIdle(10);
		hikariDataSource.setMaximumPoolSize(20);
		hikariDataSource.setConnectionTimeout(30000);
		hikariDataSource.setConnectionInitSql("select 1");
		hikariDataSource.setConnectionTestQuery("select 1");
		return hikariDataSource;
	}
}
