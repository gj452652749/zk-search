package com.zkyunso.microservice.search.stmt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserTest {
	@Autowired
	StmtParser parser;
	@Test
	public void parseStmt() {
		SearchStmt stmt=new SearchStmt("gj-core","ss","type:2");
		String url=parser.parseStmt(stmt);
		System.out.println(url);
	}
}
