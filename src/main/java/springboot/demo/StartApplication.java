package springboot.demo;

import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("springboot.demo.dao")
public class StartApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(StartApplication.class, args);
	}

}
