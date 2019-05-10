package readers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import fileclasses.Config;

public class ConfigReader {
	
	public static List<Congig> getCongig(){
		
		List<Config> config = Collections.emptyList();
		
		File file = new File("config.txt");
		
		try(Scanner sc = newScanner(file)){
			config = new ArrayList<>();
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				Config config = createConfig(line);
				config.add(config);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	private static Config createConfig(String line) {
		
		
		final byte YEAR = 0, MONTH = 1, INCOMINGLENG = 2, OUTGOINGLENG = 3;
		
		String[] dataArray = line.split(" ");
				
		int year = Integer.valueOf(dataArray[YEAR]);
		int month = Integer.valueOf(dataArray[MONTH]);
		String incomingLeng = dataArray[INCOMINGLENG];
		String outgoinLeng = dataArray[OUTGOINGLENG];
		
		return new Config(year, month, incomingLeng, outgoinLeng);
	}
		
}
