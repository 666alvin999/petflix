package com.petflix.infrastructure.dbinit;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

@Component
public class DbInit {

	private JdbcTemplate jdbcTemplate;

	public DbInit(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PostConstruct
	public void init() throws IOException {
		this.jdbcTemplate.execute(
			new String(readAllBytes(Paths.get("src/main/java/com/petflix/infrastructure/dbinit/data_init.sql")))
		);
	}

}
