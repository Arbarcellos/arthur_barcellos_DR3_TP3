package br.com.arthur.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;


public class App {
	@GetMapping("/nome")
	public String obterMeuNome() {
		return "Arthur Teixeira Barcellos";
	}
	@GetMapping("/linguagem")
	public String obterMinhaLinguagem() {
		return "Java";
	}
	@GetMapping("/idade")
	public int obterMinhaIdade() {
		return 35;
	}
	@GetMapping("/salario")
	public double obterMeuSalario() {
		return  10000;
	}
	@GetMapping("/aluno")
	public boolean ehAluno() {
		return true;
	}
}

