package com.zzx.config.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApp {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServerApp.class).web(true).run(args);
	}
}
