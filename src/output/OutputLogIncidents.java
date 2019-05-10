package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputLogIncidents {
    //Esta clase tendría un método que añade una nueva línea al archivo incidencias.log
    //Las otras clases llamarían a esta clase para escribir cada posible conflicto en el log.


    public static void writeConflict(String conflictType){
        StringBuilder sb = new StringBuilder();
        //These should be more specific, the output should at least indicate the line(s) where the conflict happened
        switch(conflictType){
            case "wrongDateFormat":
                sb.append("Incorrect date format detected");
                break;
            case "OverlappingEvents":
                sb.append("Two events are overlapping");
            case "TooManyTimePeriods":
                sb.append("This even takes too many time periods (the maximum should be 5)");
        }
        FileWriter fstream = null;
        try {
            fstream = new FileWriter("incidencias.log");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(sb.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}