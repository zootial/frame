package com.zzx.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zzx.common.dao.CommonDaoImpl;

@SpringBootApplication
@EnableTransactionManagement
//@ComponentScan(basePackageClasses = FrameConfig.class)
@EnableJpaRepositories(repositoryBaseClass = CommonDaoImpl.class)
@EnableJpaAuditing
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
