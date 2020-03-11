package com.awg.j20.cartrawler;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import com.awg.j20.cartrawler.workflow.MainWorkflow;

/**
 * Main application entry point
 */
@SpringBootApplication
public class CarTrawlerApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger("CarTrawlerApplication");
	@Autowired
    private Environment env;
	@Autowired
	private MainWorkflow mainWorkflow;

	public static void main(String[] args) {
		new SpringApplicationBuilder(CarTrawlerApplication.class)
					.web(WebApplicationType.SERVLET)
					.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> arguments = Arrays.asList(args);
		logger.info("Starting CarTrawlerApplication with parameters: " + arguments);
		
		logger.info("Active Mode: " + env.getProperty("spring.application.name"));		
		logger.info("Active Profiles: " + Arrays.toString(env.getActiveProfiles()));
		
		mainWorkflow.executeAllOperationsFlow();
	}
}
