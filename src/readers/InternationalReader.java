package readers;

import java.io.File;
import java.util.Scanner;

import fileclasses.International;

public class InternationalReader {
	
	public static International getInternacional(){
		
		File file = new File("international.CAT");
		
		try(Scanner sc = new Scanner(file)) {
			String line1 = sc.nextLine();
			String[] result = line1.split(";");
			int id = Integer.valueOf(result[0]);
			String value1 = resutl[1];
			System.out.println(result);
			
			String line2 = sc.nextLine();
			String[] result2 = line2.split(";");
			int id = result[0];
			String value2 = result2[1];
			String value3 = result3[2];
		}
		return null;
		
	}
}
