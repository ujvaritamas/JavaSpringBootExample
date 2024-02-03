package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
@EnableAspectJAutoProxy
public class AopExampleApplication implements CommandLineRunner {

	@Autowired
	private SimpleAdder simpleAdder;

	public AopExampleApplication(SimpleAdder simpleAdder) {
		this.simpleAdder = simpleAdder;
	}

	public static void main(String[] args) {
		SpringApplication.run(AopExampleApplication.class, args);
	}

	@PostConstruct
	public void executeAfterSpringStartup() {
		int result = simpleAdder.add(2, 3);
		System.out.println("The result is:" +result);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("run performed");
	}
}
