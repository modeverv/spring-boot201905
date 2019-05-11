/**
 * 
 */
package com.example.demo.trySpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
