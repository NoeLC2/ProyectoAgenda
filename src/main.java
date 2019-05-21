import ProcessPetitions.ConvertWeekDays;
import ProcessPetitions.CreateArrayPetitions;
import ProcessPetitions.ProcessPetitionsMonth;
import fileclasses.Config;
import fileclasses.Petition;
import fileclasses.ProcessedPetition;
import output.OutputHTML;
import readers.ConfigReader;
import readers.PetitionReader;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<Petition> petitions = PetitionReader.getPetitions();
        //System.out.println(petitions);
        Config config = ConfigReader.getConfig();
        List<ProcessedPetition> processedPetitions = new ArrayList<>();
        processedPetitions.add(ProcessPetitionsMonth.getProcessedPetitions(config, petitions.get(14)));
        /*for (Petition petition: petitions){
            if(ProcessPetitionsMonth.getProcessedPetitions(config, petition) != null) {
                processedPetitions.add(ProcessPetitionsMonth.getProcessedPetitions(config, petition));
            }
        }*/
        OutputHTML.generateHTML("lol", processedPetitions, config);

        /*ProcessedPetition pp = ProcessPetitionsMonth.getProcessedPetitions(config, petitions.get(14));
        List<DayOfWeek> weekDays = ConvertWeekDays.convert(petitions.get(8));
        System.out.println(pp.getDaysMonth());

        String[][] arrayPetitions = CreateArrayPetitions.getArray(processedPetitions);

        weekDays.forEach(System.out::println);

        System.out.println(arrayPetitions[24][9]);*/


        /*LocalDate startDate = petitions.get(8).getStartDate();
        LocalDate endDate = petitions.get(8).getEndDate();
        List<LocalDate> totalDates = new ArrayList<>();
        while (startDate.isBefore(endDate)) {
            totalDates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        totalDates.forEach(System.out::println);*/


        //En el main ejecutaríamos el escritor de html y el comprobador principal de excepciones
    }
}
