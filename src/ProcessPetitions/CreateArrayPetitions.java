package ProcessPetitions;

import fileclasses.ProcessedPetition;

import java.time.LocalDate;
import java.util.List;

public class CreateArrayPetitions {
    public static String[][] getArray(List<ProcessedPetition> processedPetitions){
        String[][] arraySchedules = new String[31][24];
        for(ProcessedPetition petition :processedPetitions){
            List<LocalDate> daysMonth = petition.getDaysMoth();
            for(LocalDate day : daysMonth){
                List<Integer> hours = petition.getHours();
                for(Integer hour : hours){

                    arraySchedules[day.getDayOfMonth()-1][hour] = petition.getActivity();
                }
            }
        }
        return arraySchedules;
    }
}
