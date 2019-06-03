package output;

import fileclasses.Petition;
import fileclasses.ProcessedPetition;

import java.io.*;
import java.util.Arrays;
import java.util.List;

//Can't have non final variables in an interface, so I'll leave it as a class
public class OutputLogIncidents {
    private static final String FILENAME = "HTMLOutputFiles/incidencias.log";

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
        if(failedFormatPetitionsCount==0){
            sb.append("Petitions that weren't processed due to formatting issues:\n");
        }
        String info = petition.getActivity() + " in " + petition.getRoom() + ", from " + petition.getStartDate() + " to " + petition.getEndDate() + " with schedule " + Arrays.toString(petition.getSchedule()) + "\n";
        //These should be more specific, the output should at least indicate the line(s) where the conflict happened
        switch(conflictType){
            case "conflictingDates":
                sb.append("- The start date shouldn't be later than the end date. Incident in petition ");
                sb.append(info);
                failedFormatPetitionsCount++;
                break;
            case "TooManyTimePeriods":
                sb.append("- An event takes too many time periods (the maximum should be 5): ");
                sb.append(info);
                failedFormatPetitionsCount++;
                break;
            case "wrongScheduleFormat":
                sb.append("- Incorrect schedule format detected for ");
                sb.append(info);
                failedFormatPetitionsCount++;
                break;
        }
        write(sb);

    }
    public static void writeOverlappingConflict(Petition petition, String conflictedActivity){
        StringBuilder sb = new StringBuilder();
        if(failedOverlappingPetitionsCount==0){
            sb.append("\nConflicting petitions due to overlapping schedules:\n");
        }
        String info = petition.getActivity() + " in " + petition.getRoom() + ", from " + petition.getStartDate() + " to " + petition.getEndDate() + " with schedule " + Arrays.toString(petition.getSchedule()) + "\n";
        sb.append("- Couldn't schedule petition for ");
        sb.append(info);
        sb.append(" (In conflict with " + conflictedActivity + ")\n");
        failedOverlappingPetitionsCount++;
        write(sb);
    }

    public static void writeSuccessfulPetitions(List<Petition> allPetitions, List<ProcessedPetition> processedPetitions){
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nPetitions that weren't processed because of formatting issues: " + failedFormatPetitionsCount + " out of " + (allPetitions.size()+failedFormatPetitionsCount));
        sb.append("\nProcessed petitions for the month that failed due to overlapping issues: " + failedOverlappingPetitionsCount + " out of " + processedPetitions.size());
        write(sb);
    }

    static void write(StringBuilder sb){
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