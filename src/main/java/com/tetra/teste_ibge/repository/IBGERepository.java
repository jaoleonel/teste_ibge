package com.tetra.teste_ibge.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.tetra.teste_ibge.model.ProjecaoPopulacional;

@Repository
public class IBGERepository {

	@Autowired
	RestTemplate restTemplate;

	@Value("${ibge_url}")
	private String url;

	public ProjecaoPopulacional GetProjecaoPopulacional() {
		return restTemplate.getForObject(url,ProjecaoPopulacional.class);
	}
	
	@Bean
	 public RestTemplate rest() {
	 return new RestTemplate();
	 }
}
