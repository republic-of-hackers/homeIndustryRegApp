package com.nagarro.HIRegApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.HIRegApp.services.FileStorageService;
import com.nagarro.HIRegApp.services.FileStorageServiceImpl;

@Configuration
public class FileStorageConfig {
	
	@Bean
	public FileStorageService storageService() {
		return new FileStorageServiceImpl();
	}
	

}
