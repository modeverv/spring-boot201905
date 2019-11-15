package com.example.demo.trySpring.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.trySpring.login.domain.model.SignupForm;
import com.example.demo.trySpring.login.domain.model.User;
import com.example.demo.trySpring.login.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("contents", "login/home :: home_contents");
		return "login/homeLayout";
	}

	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}

	@GetMapping("/userList")
	public String getUserList(Model model) {
		model.addAttribute("contents", "login/userList :: userList_contents");
		model.addAttribute("userList", userService.selectMany());
		model.addAttribute("userListCount", userService.count());
		return "login/homeLayout";
	}
	
	@GetMapping("/userList/csv")
	public ResponseEntity<byte[]> getUserListCsv(Model m) {
		userService.userCsvOut();
		byte[] bytes = null;
		try {
			bytes = userService.getFile("sample.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders header = new HttpHeaders();
		header.add("Content-TYpe", "text/csv;charset=UTF-8");
		header.setContentDispositionFormData("filename", "sameple.csv");
		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
	}

	private java.util.Map<String, String> radioMarriage;

	private java.util.Map<String, String> initRadioMarriage() {
		java.util.Map<String, String> radio = new LinkedHashMap<>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}

	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model m,
			@PathVariable("id") String userId) {
		m.addAttribute("contents", "login/userDetail :: userDetail_contents");		
		radioMarriage = initRadioMarriage();
		m.addAttribute("radioMarriage", radioMarriage);
		if (userId != null && userId.length() > 0) {
			User u = userService.selectOne(userId);
			form.setUserId(u.getUserId());
			form.setUserName(u.getUserName());
			form.setBirthday(u.getBirthday());
			form.setAge(u.getAge());
			form.setMarriage(u.isMarriage());
			m.addAttribute("signupForm", form);
		}
		return "login/homeLayout";
	}

	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		User u = new User();
		u.setUserId(form.getUserId());
		u.setPassword(form.getPassword());
		u.setUserName(form.getUserName());
		u.setBirthday(form.getBirthday());
		u.setAge(form.getAge());
		u.setMarriage(form.isMarriage());
		try {
		boolean result = userService.updateOne(u);
		if (result) {
			model.addAttribute("result", "更新成功");
		} else {
			model.addAttribute("result", "更新失敗");
		}
		} catch (Exception e) {
			model.addAttribute("result", "更新失敗(トランザクションテスト)");
		}
		return getUserList(model);
	}

	@PostMapping(value = "/userDetail", params = "delete")
	public String postUserDetailDelete(@ModelAttribute SignupForm f, Model model) {
		boolean result = userService.deleteOne(f.getUserId());
		model.addAttribute("result", "更新" + (result ? "成功" : "失敗"));
		return getUserList(model);
	}

	@GetMapping("/admin")
	public String getAdmin(Model m) {
		m.addAttribute("contents", "login/admin :: admin_contents");
		return "login/homeLayout";
	}
}
