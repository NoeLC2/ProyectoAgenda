import processpetitions.ProcessPetitionsMonth;
import processpetitions.SeparatePetitionsByRoom;
import fileclasses.Config;
import fileclasses.International;
import fileclasses.Petition;
import fileclasses.ProcessedPetition;
import output.OutputHTML;
import output.OutputLogIncidents;
import readers.ConfigReader;
import readers.InternationalReader;
import readers.PetitionReader;

import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        long pre = LocalTime.now().toNanoOfDay();
        Config config = ConfigReader.getConfig();
        //Config config = new Config(Year.of(2019), Month.of(5), "ESP", "ENG");

        //Petitions filename, Config object, start and end time for the tables, boolean allowClosedCollision
        execute("FísicaHoraris", config, 8, 20, true);

        long post = LocalTime.now().toNanoOfDay();
        System.out.println("Execution time: " + (post-pre)/1000000 + " ms");
    }


    public static void execute(String filename, Config config, int startTime, int endTime, boolean allowClosedCollision){
        //We we'll create every object that we'll need
        OutputLogIncidents.clearLog();
        List<Petition> petitions = PetitionReader.getPetitions(filename);

        International internationalIn = InternationalReader.getInternacional(config.getInputLang());
        International internationalOut = InternationalReader.getInternacional(config.getOutputLang());
        List<ProcessedPetition> processedPetitions = ProcessPetitionsMonth.getProcessedPetitions(config, petitions, internationalIn);


        //We get a set of different room names
        Set<String> roomsAsSet = new HashSet<String>();
        for (ProcessedPetition p : processedPetitions) {
            roomsAsSet.add(p.getRoom());
        }
        //Now we print an HTML file for each room name
        roomsAsSet.forEach((e) -> { OutputHTML.generateHTML((e), internationalOut, internationalIn,
                SeparatePetitionsByRoom.getPetitions(processedPetitions, (e)), config, startTime, endTime, allowClosedCollision); });
        OutputLogIncidents.writeSuccessfulPetitions(petitions, processedPetitions);
    }
}
