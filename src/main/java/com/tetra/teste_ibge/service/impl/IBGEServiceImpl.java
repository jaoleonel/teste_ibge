package com.tetra.teste_ibge.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tetra.teste_ibge.repository.IBGERepository;
import com.tetra.teste_ibge.repository.LocalFileRepository;
import com.tetra.teste_ibge.service.IBGEService;

@Service
public class IBGEServiceImpl implements IBGEService {

	@Autowired
	private IBGERepository iBGERepository;
	
	@Autowired
	private LocalFileRepository localFileRepository;

	@Override
	public long calcularProjecaoPopulacional(LocalDateTime dataFutura) throws IOException {
		var projecaoIBGEHoje = iBGERepository.GetProjecaoPopulacional();

		LocalDateTime now = LocalDateTime.now();

		Duration duration = Duration.between(now, dataFutura.plusDays(1L));
		var diff = Math.abs(duration.toMillis());
		var tempoIncrementoDeUm = Long.parseLong(projecaoIBGEHoje.projecao.periodoMedio.incrementoPopulacional); 
		var incrementoPopulacional = (long) diff / tempoIncrementoDeUm ;
		var populacaoAtual = projecaoIBGEHoje.projecao.populacao;
		
		var projecaoPopulacionalCalculada = incrementoPopulacional + populacaoAtual;
		
		localFileRepository.SalvarProjecao(projecaoIBGEHoje, projecaoPopulacionalCalculada);
		return  projecaoPopulacionalCalculada;
	}
}
