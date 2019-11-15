package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSampleApplicationTests {

	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = { "classpath:test.sql" })
	public void contextLoads() {
	}
}
