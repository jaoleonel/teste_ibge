package com.tetra.teste_ibge.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Util {

	final static Charset ENCODING = StandardCharsets.UTF_8;

	private static final String USER_DIR = System.getProperty("user.dir");
	private static final String OS_NAME = System.getProperty("os.name");

	public static String getContentFolder() {
		return OS_NAME.contains("Windows") ? USER_DIR + "\\contents\\" : USER_DIR + "/contents/";
	}

	public static List<String> readNLastLinesFromTextFile(String fileName, int numLines) throws IOException {
		Path path = Paths.get(getContentFolder() + fileName);

		var linhas = new ArrayList<String>();
		var retorno = new ArrayList<String>();

		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				linhas.add(scanner.nextLine());
			}
		}
		int inicio = linhas.size() - 1;
		int fim;
		if (linhas.size() - numLines < 0) {
			fim = 0;
		} else {
			fim = linhas.size() - numLines;
		}
		for (int i = inicio; i > fim; i--) {
			retorno.add(linhas.get(i));
		}
		return retorno;
	}

	public static List<String> readWholeTextFile(String fileName) throws IOException {
		Path path = Paths.get(getContentFolder() + fileName);
		var linhas = new ArrayList<String>();

		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				linhas.add(scanner.nextLine());
			}
		}
		return linhas;
	}

	public static void readWholeTextFileAlternate(String fileName) throws IOException {
		Path path = Paths.get(getContentFolder() + fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				// process each line in some way
				log(line);
			}
		}
	}

	public static void writeTextFile(String fileName, List<String> lines) throws IOException {
	
		Path pathFile = Paths.get(getContentFolder() + fileName);
		Path pathDir = Paths.get(getContentFolder());

		if(!Files.isDirectory(pathDir))
			Files.createDirectories(pathDir);
		
		if (Files.notExists(pathFile))
			Files.createFile(pathFile);

		var fileOpen = readWholeTextFile(fileName);
		for(String line : lines) {
			fileOpen.add(line);
		}
		try (BufferedWriter writer = Files.newBufferedWriter(pathFile, ENCODING)) {
			for (String line : fileOpen) {
				writer.write(line);
				writer.newLine();
			}
		}
	}

	public static String obterStringJsonFromObject(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public static void log(Object msg) {
		System.out.println(String.valueOf(msg));
	}

}
