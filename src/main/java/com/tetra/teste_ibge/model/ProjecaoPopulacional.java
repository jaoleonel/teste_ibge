package com.tetra.teste_ibge.model;

import java.io.Serializable;

public class ProjecaoPopulacional implements Serializable {

	public String localidade;
	public String horario;
	public Projecao projecao;

	public class Projecao {
		
		public int populacao;
		public PeriodoMedio periodoMedio;

		
		public class PeriodoMedio {

			public String incrementoPopulacional;
			public String nascimento;
			public String obito;

		}

	}
}
