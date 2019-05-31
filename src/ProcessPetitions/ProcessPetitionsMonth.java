package ProcessPetitions;

import fileclasses.Config;
import fileclasses.International;
import fileclasses.Petition;
import fileclasses.ProcessedPetition;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ProcessPetitionsMonth {
    static ProcessedPetition getProcessedPetitions(Config config, Petition petition, International international){
        int month = config.getMonth().getValue();
        int year = config.getYear().getValue();
        List<DayOfWeek> weekDays = ConvertWeekDays.convert(petition, international);
        List<LocalDate> allDatesBetween = getDatesBetween(petition.getStartDate(), petition.getEndDate(), weekDays, month, year);

        if(allDatesBetween.isEmpty()){
            return null;
        } else{
            List<Integer> hours = getSchedule(petition);
            ProcessedPetition processedPetition = new ProcessedPetition(petition.getActivity(), petition.getRoom(), petition.getStartDate(), petition.getEndDate(), petition.getWeekDays(), petition.getSchedule(), allDatesBetween, hours);
            return processedPetition;
        }
    }

    static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate, List<DayOfWeek> weekDays, int month, int year) {
        List<LocalDate> totalDates = new ArrayList<>();
        LocalDate monthStartDate = LocalDate.of(year, month, 01).minusDays(1);
        LocalDate monthEndDate = LocalDate.of(year, month, 01).plusMonths(1);
        if(startDate.isBefore(monthEndDate) && monthStartDate.isBefore(endDate)){
            LocalDate iterDate = monthStartDate;
            while (iterDate.isBefore(monthEndDate)) {
                if(iterDate.isBefore(endDate.plusDays(1)) && iterDate.isAfter(startDate.minusDays(1)) && weekDays.contains(iterDate.getDayOfWeek())) {
                    totalDates.add(iterDate);
                }
                iterDate = iterDate.plusDays(1);
            }
        }

        return totalDates;
    }

    static List<Integer> getSchedule(Petition petition){
        List<Integer> listOfTimePeriods = new ArrayList<>();
        String[] timePeriods = petition.getSchedule();
        for(String timePeriod : timePeriods){
            String[] startEnd = timePeriod.split("-");
            for(int i=Integer.parseInt(startEnd[0]);i<Integer.parseInt(startEnd[1]);i++){
                listOfTimePeriods.add(i);
            }
        }
        return listOfTimePeriods;
    }
}
