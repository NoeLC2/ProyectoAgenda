import ProcessPetitions.ProcessPetitionsMonth;
import fileclasses.Config;
import fileclasses.Petition;
import fileclasses.ProcessedPetition;
import output.OutputHTML;
import readers.ConfigReader;
import readers.PetitionReader;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<Petition> petitions = PetitionReader.getPetitions();
        //System.out.println(petitions);
        Config config = ConfigReader.getConfig();
        List<ProcessedPetition> processedPetitions = new ArrayList<>();
        processedPetitions.add(ProcessPetitionsMonth.getProcessedPetitions(config, petitions.get(8)));
        /*for (Petition petition: petitions){
            if(ProcessPetitionsMonth.getProcessedPetitions(config, petition) != null) {
                processedPetitions.add(ProcessPetitionsMonth.getProcessedPetitions(config, petition));
            }
        }*/
        OutputHTML.generateHTML("lol", processedPetitions, config);



        //En el main ejecutaríamos el escritor de html y el comprobador principal de excepciones
    }
}
