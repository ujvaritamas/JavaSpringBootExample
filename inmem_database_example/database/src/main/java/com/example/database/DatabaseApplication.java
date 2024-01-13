package com.example.database;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DatabaseApplication implements CommandLineRunner{
	
	private final DataSource dataSource;
	
	private static final Logger log = LoggerFactory.getLogger(DatabaseApplication.class);


	public DatabaseApplication(final DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}



	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		log.info("Datasource: "+dataSource.toString());
		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
		restTemplate.execute("select 1");
	}

}
