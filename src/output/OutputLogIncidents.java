package output;

import fileclasses.Petition;

import java.io.*;
import java.util.Arrays;


public class OutputLogIncidents {
    private static final String FILENAME = "incidencias.log";
    //Esta clase tendría un método que añade una nueva línea al archivo incidencias.log
    //Las otras clases llamarían a esta clase para escribir cada posible conflicto en el log.

    public static void clearLog(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILENAME);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void writeConflict(String conflictType, Petition petition){
        StringBuilder sb = new StringBuilder();
        String info = petition.getActivity() + " in " + petition.getRoom() + ", from " + petition.getStartDate() + " to " + petition.getEndDate() + " with schedule " + Arrays.toString(petition.getSchedule()) + "\n";
        //These should be more specific, the output should at least indicate the line(s) where the conflict happened
        switch(conflictType){
            case "wrongDateFormat":
                sb.append("Incorrect date format detected for ");
                sb.append(info);
                break;
            case "OverlappingEvents":
                sb.append("Two events are overlapping: couldn't schedule petition for ");
                sb.append(info);
                break;
            case "TooManyTimePeriods":
                sb.append("An event takes too many time periods (the maximum should be 5): ");
                sb.append(info);
                break;
            case "wrongScheduleFormat":
                sb.append("This petition has an incorrect schedule format: ");
                sb.append(info);
                break;
        }
        FileWriter fstream = null;
        try {
            fstream = new FileWriter(FILENAME, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(sb.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}