package com.capiau.minhasfinancas.api.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaygroundResource {
	
	@GetMapping("/")
	public String hello() {
		return "Qualquer coisa";
	}
}