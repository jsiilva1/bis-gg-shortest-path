package br.com.bisgg.persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;

public class Persistencia implements PersistenciaInterface {

	private String ext = ".txt";
	private String dir = "data/";

	/*
	 *
	 * Método para obter objetos de um dado arquivo
	 *
	 * @arguments String filename
	 * FileName: Nome do arquivo que será consumido
	 *
	 * @return Object
	 *
	 * */
	public LinkedList<String> get (String fileName) throws IOException, ClassNotFoundException {

		BufferedReader br = null;
		FileReader fr = null;

		LinkedList<String> objects = new LinkedList<>();

		try {
			fr = new FileReader(dir+fileName+ext);
			br = new BufferedReader(fr);

			String line = br.readLine();

			while (line != null) {
				objects.add(line);
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return objects;
	}

	/*
	 *
	 * Método para verificar se um arquivo existe
	 *
	 * @arguments String filename
	 * FileName: Nome do arquivo que será verificado
	 *
	 * @return Boolean
	 *
	 * */
	public boolean isExists (String fileName) throws IOException {
		File file = new File(dir+fileName+ext);
		
		if (file.exists())
			return true; 
		
		return false;
	}

	public boolean isEmpty (String fileName) throws IOException {
		File file = new File(dir+fileName+ext);

		if (file.length() == 0)
			return true;

		return false;
	}
}
