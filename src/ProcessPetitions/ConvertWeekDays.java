package ProcessPetitions;

import fileclasses.Config;
import fileclasses.Petition;
import readers.ConfigReader;
import readers.PetitionReader;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class ConvertWeekDays {
    public static List<DayOfWeek> convert(Petition petition) {
        Config config = ConfigReader.getConfig();
        String inputLang = config.getInputLang();
        String weekDays = petition.getWeekDays();
        List<DayOfWeek> arrayProcessedDays = new ArrayList<>();;

        //use getters from International
        String[] abbreviations = {"L","M","C","J","V","S","G"};
        String[] weekDaysNames = {"Dilluns","Dimarts","Dimecres","Dijous","Divendres","Dissabte","Diumenge"};

        String[] weekDaysArray = weekDays.split("");
        for(String weekDay : weekDaysArray){
            int i = 0;
            if(abbreviations[i]==weekDay){
                arrayProcessedDays.add(DayOfWeek.of(i+1));
            }
            i++;
        }
        return arrayProcessedDays;

    }

}
