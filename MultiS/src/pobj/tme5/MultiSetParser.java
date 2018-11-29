package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {

	public static MultiSet<String> parse (String filename, MultiSet<String> ms)
									throws InvalidMultiSetFormat{
		try {
			String valeurs [] = new String [2];
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while((line = br.readLine()) != null) {
				line = line.trim();
				valeurs = line.split(":");
				if (valeurs[0].equals(""))
					continue;
				ms.add(valeurs[0],Integer.decode(valeurs[1]));
			}
			
		}catch (FileNotFoundException e) {
			throw new InvalidMultiSetFormat("fichier introuvable", e);
		}catch (IOException e) {
			throw new InvalidMultiSetFormat("erreur entree sortie", e);
		}catch (NumberFormatException e) {
			throw new InvalidMultiSetFormat("fichier tranqué\nla valeur n'est pas un entier",e);
		}catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidMultiSetFormat("fichier tranqué\nune ligne n'est pas du type <clef>:<valeur>",e);
		}
		return null;
	}
}
