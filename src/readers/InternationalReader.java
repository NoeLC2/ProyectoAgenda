package readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import fileclasses.International;

public class InternationalReader {
	
	public static International getInternacional(){

	    International international = null;

	    //File file = new File("international." + config.getOutputLang);
		File file = new File("internacional.CAT");
		
		try(Scanner sc = new Scanner(file)) {
			String line1 = sc.nextLine();
			String[] dataTitle = line1.split(";");
			String title = dataTitle[1].trim();
			
			String line2 = sc.nextLine();
			String[] dataWeekDays = line2.split(";");
			String[] weekDays = dataWeekDays[1].split(",");

            String line3 = sc.nextLine();
            String[] dataWeekDaysAbbr = line3.split(";");
            String weekDaysAbbr = dataWeekDaysAbbr[1].trim();

            String line4 = sc.nextLine();
            String[] dataMonths = line4.split(";");
            String[] months = dataMonths[1].split(",");

            String line5 = sc.nextLine();
            String[] dataTimeWords = line5.split(";");
            String[] timeWords = dataTimeWords[1].split(",");

            String line6 = sc.nextLine();
            String[] dataGeneratedBy = line6.split(";");
            String generatedBy = dataGeneratedBy[1].trim();

            String line7 = sc.nextLine();
            String[] dataClosed = line7.split(";");
            String closed = dataClosed[1].trim();

            String line8 = sc.nextLine();
            String[] dataError = line7.split(";");
            String error = dataError[1].trim();

            international = new International(title, weekDays, weekDaysAbbr, months, timeWords, generatedBy, closed, error);



		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return international;
		
	}
}
