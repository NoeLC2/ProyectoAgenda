package readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Month;
import java.time.Year;
import java.util.Scanner;

import fileclasses.Config;

public class ConfigReader {
	
	public static Config getConfig(){

		Config config = null;
		
		File file = new File("config.txt");
		
		try(Scanner sc = new Scanner(file)) {
			String line1 = sc.nextLine();
			String[] dataArray1 = line1.split(" ");
			Year year = Year.of(Integer.parseInt(dataArray1[0]));
			Month month = Month.of(Integer.parseInt(dataArray1[1]));

			String line2 = sc.nextLine();
			String[] dataArray2 = line2.split(" ");
			String inputLang = dataArray2[0];
			String outputLang = dataArray2[1];
			config = new Config(year,month,inputLang,outputLang);

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return config;
	}
		
}
