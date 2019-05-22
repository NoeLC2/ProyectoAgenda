package ProcessPetitions;

import fileclasses.Config;
import fileclasses.International;
import fileclasses.Petition;
import readers.ConfigReader;
import readers.PetitionReader;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertWeekDays {
    public static List<DayOfWeek> convert(Petition petition, International international) {
        Config config = ConfigReader.getConfig();
        String inputLang = config.getInputLang();
        String weekDays = petition.getWeekDays();
        List<DayOfWeek> arrayProcessedDays = new ArrayList<>();

        international.getAbbreviatedWeekDays();
        String[] abbreviations = international.getAbbreviatedWeekDays().split("");

        String[] weekDaysArray = weekDays.split("");
        int i =0;
        for(String abbreviation : abbreviations){
            if(Arrays.stream(weekDaysArray).anyMatch(abbreviation::equals)){
                arrayProcessedDays.add(DayOfWeek.of(i+1));
            }
            i++;
        }
        return arrayProcessedDays;

    }

}
