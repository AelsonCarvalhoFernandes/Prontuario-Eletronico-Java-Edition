package com.PI.ProntuarioEletronico;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Prontuário Eletronico", version = "1", description = "Api para consumir " +
		"de uma aplicação desktop e tambem de um site em SPA. Esse site é apenas para testes então será desativado na" +
		" versão final"))
public class ProntuarioEletronicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProntuarioEletronicoApplication.class, args);
	}

}
