package com.tetra.teste_ibge.repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tetra.teste_ibge.model.Log;
import com.tetra.teste_ibge.model.ProjecaoPopulacional;
import com.tetra.teste_ibge.util.Util;

@Repository
public class LocalFileRepository {

	@Value("${log_file_name}")
	private String logFileName;

	public void SalvarProjecao(ProjecaoPopulacional projecaoIBGE, long projecaoPopulacionalCalculada)
			throws IOException {

		Util.log(projecaoIBGE.horario);
		var log = new Log(LocalDateTime.now().toString(), projecaoIBGE.horario,
				String.valueOf(projecaoIBGE.projecao.populacao), String.valueOf(projecaoPopulacionalCalculada));
		var logJsonString = Util.obterStringJsonFromObject(log);

		var linhas = Arrays.asList(logJsonString);

		Util.writeTextFile(logFileName, linhas);
	}

	public List<String> ObterUltimasProjecoes(int numLines) throws IOException {
		return Util.readNLastLinesFromTextFile(logFileName, numLines);
	}

}
