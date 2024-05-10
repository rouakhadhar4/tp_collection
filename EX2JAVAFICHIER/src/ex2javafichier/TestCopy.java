package ex2javafichier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestCopy {

	public static void main(String[] args) throws IOException {
		File inputFile = new File("D:\\fichiers\\in.txt");
		File outFile = new File("D:\\fichiers\\out.txt");
		FileReader in = new FileReader(inputFile);
		FileWriter out = new FileWriter(outFile);
		int c;
		while((c=in.read())!=-1)
			{
			out.write(c);
			  
			}
		in.close();
		out.close();
		  System.out.println("Copie terminée.");

	}

}
