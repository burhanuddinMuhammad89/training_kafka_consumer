package com.kafka.demo;

import com.kafka.demo.model.Employee;
import com.kafka.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.kafka.demo")
public class DemoApplication implements ApplicationRunner {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private EmployeeService employeeService;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@KafkaListener(topics = "test-kafka-baru", groupId = "test-consumer-group")
	public void listen(String message) {
		System.out.println("message is : "+message);
		employeeService.insertToDb(message);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
}
