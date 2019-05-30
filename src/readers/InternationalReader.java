package readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fileclasses.Config;
import fileclasses.International;

public class InternationalReader {

    private static final String FILE_NAME = "internacional";
	
	public static International getInternacional(String language){



	    International international = null;

	    File file = new File(FILE_NAME + "." + language);
		
		try(Scanner sc = new Scanner(file)) {
			String line1 = sc.nextLine();
			String[] result = line1.split(";");
			int id = Integer.valueOf(result[0]);
			String value1 = line1[1];
		
			
			String line2 = sc.nextLine();
			String[] result2 = line2.split(";");
			int id = result[0];
			String value2 = result2[1];
			String value3 = result3[2];
		}
		return null;
		
	}
}
