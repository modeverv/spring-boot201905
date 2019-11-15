package com.example.demo.trySpring.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.trySpring.login.domain.model.User;
import com.example.demo.trySpring.login.domain.service.RestService;

@RestController
public class UserRestController {

	@Autowired
	RestService service;

	@GetMapping("/rest/get")
	public List<User> getUserMany() {
		return service.selectMany();
	}

	@GetMapping("/rest/get/{id:.+}")
	public User getUserOne(@PathVariable("id") String userId) {
		return service.selectOne(userId);
	}

	@PostMapping("/rest/insert")
	public String postUserOne(@RequestBody User user) {
		boolean result = service.insert(user);
		String str = "";
		if (result) {
			str = "{'result': 'ok'}";
		} else {
			str = "{'result': 'error'}";
		}
		return str;
	}

}
