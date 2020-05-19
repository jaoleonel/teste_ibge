package com.tetra.teste_ibge.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tetra.teste_ibge.service.IBGEService;
import com.tetra.teste_ibge.service.LogService;

@RestController
public class ProjecaoPopulacionalController {

	@Autowired
	private IBGEService iBGEService;
	
	@Autowired
	private LogService logService; 
	
	@Value("${num_logs}")
	private int numLogs;
	
	@GetMapping("/projecaopopulacional/{dataFutura}")
	public @ResponseBody ResponseEntity<Long>
	  getProjecaoPopulacional(@PathVariable String dataFutura) {
	    
		dataFutura = dataFutura+" 00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dataFutura, formatter);
		
		try {
			return new ResponseEntity<Long>(
			  iBGEService.calcularProjecaoPopulacional(dateTime), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ultimasprojecoes")
	public @ResponseBody ResponseEntity<List<String>> getUltimasProjecoes(){
		try {
			return new ResponseEntity<List<String>>(
					logService.obterUltimosNLogs(numLogs), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
