package output;

import fileclasses.Petition;
import fileclasses.ProcessedPetition;

import java.io.*;
import java.util.Arrays;
import java.util.List;


public class OutputLogIncidents {
    private static final String FILENAME = "incidencias.log";

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
    public static int failedOverlappingPetitionsCount = 0;
    public static int failedFormatPetitionsCount = 0;


    public static void writeConflict(String conflictType, Petition petition){
        StringBuilder sb = new StringBuilder();
        String info = petition.getActivity() + " in " + petition.getRoom() + ", from " + petition.getStartDate() + " to " + petition.getEndDate() + " with schedule " + Arrays.toString(petition.getSchedule()) + "\n";
        //These should be more specific, the output should at least indicate the line(s) where the conflict happened
        switch(conflictType){
            case "wrongDateFormat":
                sb.append("Incorrect date format detected for ");
                sb.append(info);
                failedFormatPetitionsCount++;
                break;
            case "OverlappingEvents":
                sb.append("Two events are overlapping: couldn't schedule petition for ");
                sb.append(info);
                failedOverlappingPetitionsCount++;
                break;
            case "TooManyTimePeriods":
                sb.append("An event takes too many time periods (the maximum should be 5): ");
                sb.append(info);
                failedFormatPetitionsCount++;
                break;
            case "wrongScheduleFormat":
                sb.append("This petition has an incorrect schedule format: ");
                sb.append(info);
                failedFormatPetitionsCount++;
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

    public static void writeSuccessfulPetitions(List<Petition> allPetitions, List<ProcessedPetition> processedPetitions){
        StringBuilder sb = new StringBuilder();
        sb.append("\nPetitions that weren't processed because of formatting issues: " + failedFormatPetitionsCount + " out of " + (allPetitions.size()+failedFormatPetitionsCount));
        sb.append("\nProcessed petitions for the month that failed due to overlapping issues: " + failedOverlappingPetitionsCount + " out of " + processedPetitions.size());

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