/**
 *
 */
package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author seijiro
 *
 */
@Controller
public class HelloController {

	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

	@GetMapping("/")
	public String getIndex() {
		return "hello";
	}

	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1") String str, Model model) {
		model.addAttribute("sample", str);
		return "helloResponse";
	}

	@Autowired
	private HelloService helloService;

	@PostMapping("/hello/db")
	public String postDbRequest(@RequestParam("text2") String str, Model m) {
		int id = Integer.parseInt(str);
		Employee e = helloService.findOne(id);
		m.addAttribute("id", e.getEmployeeId());
		m.addAttribute("name", e.getEmployeeName());
		m.addAttribute("age", e.getAge());
		return "helloResponseDB";
	}

}
