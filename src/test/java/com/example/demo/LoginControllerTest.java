package com.example.demo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void ログイン画面の表示() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk())
				.andExpect(content().string(containsString("ユーザーID")));
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = { "classpath:test.sql" })
	public void 終了処理() {

	}

}
