package com.petflix.infrastructure.dao;

import com.petflix.domain.bean.PresentationVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class PresentationVideoDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public PresentationVideoDao() {
	}

	public PresentationVideoDao(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

}
