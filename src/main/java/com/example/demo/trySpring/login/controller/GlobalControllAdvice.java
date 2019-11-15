package com.example.demo.trySpring.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalControllAdvice {
	@ExceptionHandler(DataAccessException.class)
	public String dataErro(DataAccessException e, Model m) {
		m.addAttribute("error", "サーバーエラー (DB)");
		m.addAttribute("message", "サインアップでエラー");
		m.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String dataErro(Exception e, Model m) {
		m.addAttribute("error", "サーバーエラー (一般)");
		m.addAttribute("message", "サインアップでエラー");
		m.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}

}
