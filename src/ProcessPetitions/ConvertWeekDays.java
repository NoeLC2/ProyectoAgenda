package processpetitions;

import fileclasses.International;
import fileclasses.Petition;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ConvertWeekDays {
    static List<DayOfWeek> convert(Petition petition, International international) {
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
