package com.tetra.teste_ibge.service;

import java.io.IOException;
import java.time.LocalDateTime;

public interface IBGEService {

	long calcularProjecaoPopulacional(LocalDateTime dataFutura) throws IOException;

}
