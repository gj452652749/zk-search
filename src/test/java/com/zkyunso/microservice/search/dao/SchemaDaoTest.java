package com.zkyunso.microservice.search.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zkyunso.microservice.search.stmt.SearchStmt;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchemaDaoTest {
	@Autowired
	SchemaDao dao;
	@Test
	public void getAll() {
		String core="gj_core1";
		String json=dao.getALl(core);
		System.out.println(json);
	}
	@Test
	public void save() {
		String core="gj_core1";
		String json="{\"name\":\"sell-date\",\"type\":\"tdate\",\"stored\":true }";
		dao.save(core, json);
	}
	@Test
	public void update() {
		String core="gj_core1";
		String json="{\"name\":\"sell-date\",\"type\":\"string\",\"stored\":true }";
		dao.update(core, json);
	}
	@Test
	public void remove() {
		String core="gj_core1";
		String json="{\"name\":\"sell-date\"}";
		dao.remove(core, json);
	}
}
