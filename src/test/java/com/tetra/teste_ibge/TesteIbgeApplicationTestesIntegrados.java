package com.tetra.teste_ibge;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.client.RestTemplate;

import com.tetra.teste_ibge.controller.ProjecaoPopulacionalController;
import com.tetra.teste_ibge.repository.IBGERepository;
import com.tetra.teste_ibge.repository.LocalFileRepository;
import com.tetra.teste_ibge.service.IBGEService;
import com.tetra.teste_ibge.service.impl.IBGEServiceImpl;
import com.tetra.teste_ibge.service.impl.LogServiceImpl;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@SpringBootTest(classes = { IBGEServiceImpl.class, IBGERepository.class, LocalFileRepository.class })
class TesteIbgeApplicationTestesIntegrados {

	@Autowired
	IBGEService iBGEService;

	@Test
	public void getProjecaoPopulacionalTesteIntegrado() throws IOException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse("2030-01-01 00:00", formatter);

		var result = iBGEService.calcularProjecaoPopulacional(dateTime);

		assertNotNull(result);
	}
	
	

}
