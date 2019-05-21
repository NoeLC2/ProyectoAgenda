package ProcessPetitions;

import fileclasses.Config;
import fileclasses.Petition;
import fileclasses.ProcessedPetition;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProcessPetitionsMonth {
    public static ProcessedPetition getProcessedPetitions(Config config, Petition petition){
        Month month = config.getMonth();
        List<LocalDate> allDatesBetween = getDatesBetween(petition.getStartDate(), petition.getEndDate());
        List<DayOfWeek> weekDays = ConvertWeekDays.convert(petition);

        for (Iterator<LocalDate> iter = allDatesBetween.listIterator(); iter.hasNext(); ) {
            LocalDate date = iter.next();
            if(date.getMonth() != month){
                iter.remove();
            }
            else if(!weekDays.contains(date.getDayOfWeek())){
                iter.remove();
            }
        }
        /*for(LocalDate date : allDatesBetween){
            if(date.getMonth()!=month) {
                allDatesBetween.remove(date);
            }
            else if(!weekDays.contains(date.getDayOfWeek())){
                allDatesBetween.remove(date);
            }
        }*/

        if(allDatesBetween.isEmpty()){
            return null;
        } else{
            List<Integer> hours = getSchedule(petition);
            ProcessedPetition processedPetition = new ProcessedPetition(petition.getActivity(), petition.getRoom(), allDatesBetween, hours);
            return processedPetition;
        }



    }

    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        //Stackoverflow
        List<LocalDate> totalDates = new ArrayList<>();
        while (startDate.isBefore(endDate)) {
            totalDates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return totalDates;

        /*long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());*/
    }

    public static List<Integer> getSchedule(Petition petition){
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
