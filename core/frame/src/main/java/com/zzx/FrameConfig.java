package com.zzx;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zzx.dao.CommonDaoImpl;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = CommonDaoImpl.class)
public class FrameConfig {

}
