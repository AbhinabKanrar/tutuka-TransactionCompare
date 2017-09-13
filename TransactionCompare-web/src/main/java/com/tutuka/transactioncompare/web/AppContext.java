/**
 * 
 */
package com.tutuka.transactioncompare.web;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.tutuka.transactioncompare.service.ServiceConfig;
import com.tutuka.transactioncompare.util.CommonUtil;

/**
 * The core application context
 * 
 * @author abhinab
 *
 */
@SpringBootApplication
@Import(ServiceConfig.class)
@EnableAsync
public class AppContext {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppContext.class, args);
	}

	/**
	 * 
	 * aync task scheduler creator
	 */
	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(CommonUtil.MIN_THREAD_POOL);
		executor.setMaxPoolSize(CommonUtil.MIN_THREAD_POOL);
		executor.setQueueCapacity(CommonUtil.MAX_QUEUE_CAPACITY);
		executor.setThreadNamePrefix("Tutuka-");
		executor.initialize();
		return executor;
	}

}
