package readers;

import fileclasses.Petition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class PetitionReader {
    public List<Petition> getPetitions(){
        String fileName = "peticions.txt";
        try {
            Stream<String> stream = Files.lines(Paths.get(fileName))
        } catch (IOException e) {
            e.printStackTrace();
        });
        return null;
    }
}
