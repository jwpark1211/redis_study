package redisEx.demo413;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Demo413Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo413Application.class, args);
	}

}
