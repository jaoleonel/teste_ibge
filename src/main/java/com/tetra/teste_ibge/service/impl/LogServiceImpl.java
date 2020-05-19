package com.tetra.teste_ibge.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tetra.teste_ibge.repository.LocalFileRepository;
import com.tetra.teste_ibge.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LocalFileRepository localFileRepository;
	
	@Override
	public List<String> obterUltimosNLogs(int numLogs) throws IOException {
		return localFileRepository.ObterUltimasProjecoes(numLogs);
	}

	
}
