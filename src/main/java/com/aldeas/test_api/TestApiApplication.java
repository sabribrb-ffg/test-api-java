package com.aldeas.test_api;

import com.aldeas.test_api.login.LoginOperation;
import com.aldeas.test_api.login.LoginResponse;
import com.aldeas.test_api.query.AccountQueryRecords;
import com.aldeas.test_api.query.QueryOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApiApplication {

	private static final Logger log = LoggerFactory.getLogger(TestApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TestApiApplication.class, args);
	}

	@Autowired
	private LoginOperation loginOperation;

	@Autowired
	private QueryOperation queryOperation;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {

		return args -> { //shortcut way of defining the CommandLineRunner.run method
			log.debug("commandLineRunner: start");

			LoginResponse loginResponse = loginOperation.login();
			System.out.println("loginResponse="+loginResponse);

			AccountQueryRecords records = queryOperation.query(loginResponse,"select Id, Name from Account limit 10", AccountQueryRecords.class);
			System.out.println("records="+records);

			log.debug("commandLineRunner: stop");
		};
	}

}
