package ProcessPetitions;

import fileclasses.Config;
import fileclasses.International;
import fileclasses.ProcessedPetition;
import output.OutputLogIncidents;
import readers.InternationalReader;

import java.time.LocalDate;
import java.util.List;

public class CreateArrayPetitions {
    public static String[][] getArray(List<ProcessedPetition> processedPetitions, Config config, International internationalOut, International internationalIn){
        //We have a matrix of 31*24, 31 because that's the maximum number of days
        // in a month and 24 because we have 24 time zones
        String[][] arraySchedules = new String[31][24];

        for(ProcessedPetition petitionClosed : processedPetitions) {
            if (petitionClosed.getActivity().equals(internationalIn.getClosed())) {
                List<LocalDate> daysMonth = petitionClosed.getDaysMonth();
                for (LocalDate day : daysMonth) {
                    List<Integer> hours = petitionClosed.getHours();
                    for (Integer hour : hours) {
                        arraySchedules[day.getDayOfMonth()-1][hour] = internationalOut.getClosed();
                    }
                }
            }
        }
        for(ProcessedPetition petition : processedPetitions) {
            boolean isLogged = false;
            if (!petition.getActivity().equals(internationalIn.getClosed())) {
                List<LocalDate> daysMonth = petition.getDaysMonth();
                //We'll go through the array twice, first to check for overlapping schedules,
                // and then we'll add them into the array

                for (LocalDate day : daysMonth) {
                    List<Integer> hours = petition.getHours();
                    for (Integer hour : hours) {
                        if (arraySchedules[day.getDayOfMonth() - 1][hour] != null && !isLogged) {
                            isLogged = true;
                            OutputLogIncidents.writeConflict("OverlappingEvents", petition);
                        }
                    }
                }
                if (!isLogged) {
                    for (LocalDate day2 : daysMonth) {
                        List<Integer> hours2 = petition.getHours();
                        for (Integer hour2 : hours2) {
                            arraySchedules[day2.getDayOfMonth() - 1][hour2] = petition.getActivity();
                        }
                    }
                }
            }
        }


        return arraySchedules;
    }
}
