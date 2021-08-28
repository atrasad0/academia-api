package com.api.cadastroAcademia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = CadastroAcademiaApplication.class)
@MapperScan(basePackages = "com.api.cadastroAcademia.service.business.mapper")
@SpringBootApplication
public class CadastroAcademiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroAcademiaApplication.class, args);
	}

}
