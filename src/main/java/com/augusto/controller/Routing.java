package com.augusto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping
public class Routing {
	
	@GetMapping("/")
	public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes) {
		return new RedirectView("/login");
	}

	@RequestMapping({ "/login/{src}", "login" })
	public String view(@PathVariable(required = false) String src) {
		if (src == null) {
			return "forward:/index.html";
		}
		return String.format("forward:/%s", src);
	}
}
