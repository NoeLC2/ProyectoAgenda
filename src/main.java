import fileclasses.Petition;
import output.OutputHTML;
import readers.PetitionReader;

import java.util.List;

public class main {
    public static void main(String[] args) {
        //List<Petition> petitions = PetitionReader.getPetitions();
        //System.out.println(petitions);

        OutputHTML.generateHTML();

        //En el main ejecutaríamos el escritor de html y el comprobador principal de excepciones
    }
}
