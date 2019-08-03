package com.zkyunso.microservice.search.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zkyunso.microservice.search.stmt.AnalysisStmt;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalysisDaoTest {
	@Autowired
	AnalysisDao dao;
	@Test
	public void get() {
		AnalysisStmt stmt=new AnalysisStmt("address","text_ik_mutable","武汉市中科院");
		//dao.get(stmt);
	}

}
