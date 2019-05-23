package output;

import ProcessPetitions.CreateArrayPetitions;
import fileclasses.Config;
import fileclasses.International;
import fileclasses.Petition;
import fileclasses.ProcessedPetition;
import readers.PetitionReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class OutputHTML {
    public static void generateHTML(String room, International internationalOut, International internationalIn, List<ProcessedPetition> processedPetitions, Config config){

        String[][] arrayPetitions = CreateArrayPetitions.getArray(processedPetitions, config, internationalOut, internationalIn);

        //This is just so I can have different colors for each activity
        Set<String> activitiesAsSet = new TreeSet<String>();
        for (ProcessedPetition p : processedPetitions) {
            activitiesAsSet.add(p.getActivity());
        }
        String[] colors = {"Cornsilk", "LightSkyBlue", "LightPink", "MediumTurquoise", "Thistle", "LightSteelBlue"};


        int month = config.getMonth().getValue();
        int year = config.getYear().getValue();

        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate localDate = LocalDate.of(year, month, 01);
        LocalDate localDate2 =  LocalDate.of(year, month, 01).plusMonths(1);

        java.time.DayOfWeek dayWeek = localDate.getDayOfWeek();


        //We need two different day counters because it's easier to deal with th and td separately
        //Looking back, this could have been implemented in a better way
        int day = 2-dayWeek.getValue();
        int day2 = day;

        int numberOfWeeks = (int) ChronoUnit.WEEKS.between(localDate, localDate2);


        String[] weekDays = internationalOut.getWeekDays();

        //We'll create the HTML file with a StringBuilder, which we will pass to a FileWriter later
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>" + internationalOut.getTitle() + " " + room.replaceAll("([^_])([1-9])", "$1 $2") + " " + internationalOut.getMonths()[month-1] + " " + year);
        sb.append("</title>");
        sb.append("<style>td{text-align: center; vertical-align: middle;}table, th, td {border: 1px solid black;border-collapse: collapse;width:80vw;margin-left:auto;margin-right:auto;}</style>");
        sb.append("</head>");
        sb.append("<body bgcolor=\"MintCream\">");
        sb.append("<h1 align=\"center\">" + room.replaceAll("([^_])([1-9])", "$1 $2") + "</h1>");
        sb.append("<h1 align=\"center\">" + internationalOut.getTitle() + " " + internationalOut.getMonths()[month-1] + " " + year + "</h1>");
        //We create all the tables we need for this month (one for each week)
        for(int m=0;m<=numberOfWeeks;m++){
            sb.append("<table bgcolor=\"Lavender\" border=\"1\"><tr>");
            for(int i=-1;i<weekDays.length;i++){
                sb.append("<th bgcolor=\"PowderBlue\">");
                if(i==-1){
                    LocalDate date = null;
                    if(day2<1) {
                        date = LocalDate.of(year, month, 1);
                    } else{
                        date = LocalDate.of(year, month, day2);
                    }
                    TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
                    int weekNumber = date.get(woy);
                    sb.append(internationalOut.getTimeWords()[2] + " " +  weekNumber);
                } else if(day2<=0 || day2>daysInMonth){
                    sb.append(weekDays[i] + "   ");
                    day2++;
                }else{
                    sb.append(weekDays[i] + " " + day2);
                    day2++;
                }

                sb.append("</th>");
            }
            for(int j=0;j<24;j++){
                sb.append("<tr>");
                for(int k=-1;k<weekDays.length;k++){
                    if(k==-1){
                        sb.append("<td width=\"12.5%\">");
                        sb.append(j + "-" + (j+1) + "h");
                        sb.append("</td>");
                    } else {
                        sb.append("<td width=\"12.5%\"");
                        if (day <= 0 || day > daysInMonth) {
                            sb.append(" bgcolor=\"lightgray\"");
                        }
                        else if(day>0 && arrayPetitions[day - 1][j]==null){
                            sb.append("bgcolor=\"lightgreen\"");
                        }
                        else if(arrayPetitions[day - 1][j].equals(internationalOut.getClosed())){
                            sb.append(" bgcolor=\"grey\"");
                        }else{
                            if(colors.length >= activitiesAsSet.size()) {
                                sb.append(" bgcolor=\"" + colors[((TreeSet<String>) activitiesAsSet).headSet(arrayPetitions[day - 1][j]).size()] + "\"");
                            } else{
                                sb.append(" bgcolor=\"lightblue\"");
                            }
                            //int num = arrayPetitions[day - 1][j].charAt(arrayPetitions[day - 1][j].length()-1);
                            //sb.append(" style=\"backgroundcolor:(" + 255 + "," + 255 + "," + 255 + ")\"");
                        }
                        sb.append(">");
                        if (day > 0 && day <= daysInMonth && arrayPetitions[day - 1][j]!=null) {
                            sb.append(arrayPetitions[day - 1][j]);
                        }
                        sb.append("</td>");
                        day++;
                    }

                }
                sb.append("</tr>");
                day-=7;
            }
            day+=7;
            sb.append("</table></br>");
        }

        sb.append("<h4>" + internationalOut.getGeneratedBy() + ": " + System.getProperty("user.name") + "</h4>");
        LocalDateTime dateTime = LocalDateTime.now();
        sb.append("<h4>" + dateTime.getDayOfMonth() + " " + internationalOut.getMonths()[dateTime.getMonthValue()-1] +
                " " + dateTime.getYear() + ", " + dateTime.toLocalTime().toString() + "</h4>");
        sb.append("</body>");
        sb.append("</html>");
        FileWriter fstream = null;
        try {
            fstream = new FileWriter(room + ".html");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(sb.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}