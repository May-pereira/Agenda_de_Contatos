package com.agenda.senai.Spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.agenda.senai.Spring.Repository.ContatoRepository;

@Controller
public class IndexController {
	
	@Autowired
	private ContatoRepository rs;

	@GetMapping("/")
	public String index() {
		return "index";
	}

}
