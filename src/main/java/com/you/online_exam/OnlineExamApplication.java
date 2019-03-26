package com.you.online_exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.you.online_exam.*")
public class OnlineExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineExamApplication.class, args);
	}

}
