package com.tetra.teste_ibge.service;

import java.io.IOException;
import java.util.List;

public interface LogService {

	List<String> obterUltimosNLogs(int numLogs) throws IOException;
}
