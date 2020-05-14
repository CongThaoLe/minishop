package vn.actvn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Error")
public class ErrorController {
	@GetMapping
	public String init () {
		return "Error";
	}
}
