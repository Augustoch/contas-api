package com.augusto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class Routing {
	
	 @RequestMapping({"/login/{src}", "login"})
	public String view(@PathVariable(required = false) String src) {
		 if(src == null) {
			 return "forward:/index.html";
		 }
		 return String.format("forward:/%s", src);
	}
}
