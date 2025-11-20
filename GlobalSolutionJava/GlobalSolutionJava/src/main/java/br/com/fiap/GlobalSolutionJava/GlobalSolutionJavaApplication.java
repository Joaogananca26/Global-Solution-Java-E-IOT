package br.com.fiap.GlobalSolutionJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GlobalSolutionJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalSolutionJavaApplication.class, args);
	}

}
