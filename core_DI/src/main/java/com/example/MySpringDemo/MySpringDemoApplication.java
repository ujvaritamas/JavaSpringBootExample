package com.example.MySpringDemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.example.MySpringDemo.services.ColourPrinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
@ComponentScan(basePackages = "com.example.MySpringDemo")
@PropertySource("classpath:application.properties")

public class MySpringDemoApplication implements CommandLineRunner{
	
	private ColourPrinter colourPrinter;
	
	private static final Logger log = LoggerFactory.getLogger(MySpringDemoApplication.class);

	
	

	public MySpringDemoApplication(ColourPrinter colourPrinter) {
		super();
		this.colourPrinter = colourPrinter;
	}

	public static void main(String[] args) {
		//SpringApplication.run(MySpringDemoApplication.class, args);
		SpringApplication application = new SpringApplication(MySpringDemoApplication.class);
        application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hello Spring");
		
		System.out.println(colourPrinter.print());
		log.info("Hello hello log.info");
	}

}
