package processpetitions;

import fileclasses.ProcessedPetition;

import java.util.ArrayList;
import java.util.List;

public interface SeparatePetitionsByRoom {
    static List<ProcessedPetition> getPetitions(List<ProcessedPetition> petitions, String room){
        List<ProcessedPetition> petitionsInRoom = new ArrayList<>();
        for(ProcessedPetition petition : petitions){
            if(petition.getRoom().equals(room)){
                petitionsInRoom.add(petition);
            }
        }
        return petitionsInRoom;
    }
}
