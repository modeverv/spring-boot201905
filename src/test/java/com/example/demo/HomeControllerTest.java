package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.trySpring.login.domain.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;
	
	@Test
	@WithMockUser
	public void ユーザーリスト() throws Exception {
		when(service.count()).thenReturn(10);
		mockMvc.perform(get("/userList")).andExpect(status().isOk()).andExpect(content().string(containsString("10件")));
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = { "classpath:test.sql" })
	public void 終了処理() {

	}

}
