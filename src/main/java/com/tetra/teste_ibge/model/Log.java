package com.tetra.teste_ibge.model;

public class Log {

	public String DataHoraChamada;
	public String DataHoraSolicitacao;
	public String PopulacaoEstimadaNoMomentoDaChamada;
	public String PopulacaoProjetada;

	public Log(String dataHoraChamada, String dataHoraSolicitacao, String populacaoEstimadaNoMomentoDaChamada,
			String populacaoProjetada) {
		DataHoraChamada = dataHoraChamada;
		DataHoraSolicitacao = dataHoraSolicitacao;
		PopulacaoEstimadaNoMomentoDaChamada = populacaoEstimadaNoMomentoDaChamada;
		PopulacaoProjetada = populacaoProjetada;
	}

}
