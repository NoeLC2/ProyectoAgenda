package readers;

import fileclasses.Petition;
import output.OutputLogIncidents;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PetitionReader {
    public static List<Petition> getPetitions(){
        //Por comodidad usamos la misma estructura que el lector 
    	//de coches que hicimos de ejemplo

        List<Petition> petitions = Collections.emptyList();

        File file = new File("peticions.txt");

        try(Scanner sc = new Scanner(file)) {
            petitions = new ArrayList<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                Petition petition = createPetition(line);
                petitions.add(petition);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return petitions;
    }

    private static Petition createPetition(String line) {

        final byte ACTIVITY=0, ROOM=1, STARTDATE=2, ENDDATE=3, WEEKDAYS=4, SCHEDULE=5;

        String[] dataArray = line.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String activity = dataArray[ACTIVITY];
        String room = dataArray[ROOM];

        //Throw exception in case the date format is incorrect
        LocalDate startDate = LocalDate.parse(dataArray[STARTDATE], formatter);
        LocalDate endDate = LocalDate.parse(dataArray[ENDDATE], formatter);
        String weekDays = dataArray[WEEKDAYS];
        String schedule = dataArray[SCHEDULE];
        String[] scheduleArray = schedule.split("_");
        if (scheduleArray.length > 5){
            OutputLogIncidents.writeConflict("TooManyTimePeriods");
            return null;
        } else if(endDate.isBefore(endDate)){
            //OutputLogIncidents.writeConflict("fgfgfg");
            return null;
        }
        else {
            return new Petition(activity, room, startDate, endDate, weekDays, scheduleArray);
        }
    }
}
