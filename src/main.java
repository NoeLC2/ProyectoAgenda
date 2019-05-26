import ProcessPetitions.ProcessPetitionsMonth;
import ProcessPetitions.SeparatePetitionsByRoom;
import fileclasses.Config;
import fileclasses.International;
import fileclasses.Petition;
import fileclasses.ProcessedPetition;
import output.OutputHTML;
import output.OutputLogIncidents;
import readers.ConfigReader;
import readers.InternationalReader;
import readers.PetitionReader;
import java.util.*;

public class main {
    public static void main(String[] args) {
        //We we'll create every object that we'll need
        OutputLogIncidents.clearLog();
        List<Petition> petitions = PetitionReader.getPetitions("FísicaHoraris");
        Config config = ConfigReader.getConfig();
        International internationalIn = InternationalReader.getInternacional(config.getInputLang());
        International internationalOut = InternationalReader.getInternacional(config.getOutputLang());
        List<ProcessedPetition> processedPetitions = new ArrayList<>();

        //We'll add all the petitions into the ArrayList
        //I could have added them all in getProcessedPetitions, but I wanted to be able to print a specific
        // petition through the main method
        for (Petition petition: petitions){
            if(ProcessPetitionsMonth.getProcessedPetitions(config, petition, internationalIn) != null) {
                processedPetitions.add(ProcessPetitionsMonth.getProcessedPetitions(config, petition, internationalIn));
            }
        }

        //We get a set of different room names
        Set<String> roomsAsSet = new HashSet<String>();
        for (ProcessedPetition p : processedPetitions) {
            roomsAsSet.add(p.getRoom());
        }
        //Now we print an HTML file for each room name
        roomsAsSet.forEach((e) -> { OutputHTML.generateHTML((e), internationalOut, internationalIn, SeparatePetitionsByRoom.getPetitions(processedPetitions, (e)), config); });
        OutputLogIncidents.writeSuccessfulPetitions(petitions, processedPetitions);
    }
}
