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
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class OutputHTML {
    public static void generateHTML(String sala, International international, List<ProcessedPetition> processedPetitions, Config config){

        String[][] arrayPetitions = CreateArrayPetitions.getArray(processedPetitions);
        /*String[][] arrayPetitions = new String[31][24];
        for(int i = 0; i<30; i++){
            if(i%3==0){
                arrayPetitions[i][5]+= i;
            }
        }*/
        int month = config.getMonth().getValue();
        int year = config.getYear().getValue();

        List<Petition> petitions = PetitionReader.getPetitions();

        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        //muy cutre
        LocalDate localDate = null;
        LocalDate localDate2 = null;
        if (month !=12) {
            localDate = LocalDate.of(year, month, 01);
            localDate2 = LocalDate.of(year, month + 1, 01);
        } else{
            localDate = LocalDate.of(year, month, 01);
            localDate2 = LocalDate.of(year+1, 01, 01);
        }
        java.time.DayOfWeek dayWeek = localDate.getDayOfWeek();


        int day = 2-dayWeek.getValue();
        int day2 = day;

        int numberOfWeeks = (int) ChronoUnit.WEEKS.between(localDate, localDate2);


        String[] weekDays = international.getWeekDays();

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Title Of the page");
        sb.append("</title>");
        sb.append("<style>td{text-align: center; vertical-align: middle;}</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1 align=\"center\">" + sala + "</h1>");
        sb.append("<h1 align=\"center\">" + international.getTitle() + " " + international.getMonths()[month-1] + " " + year + "</h1>");
        for(int m=0;m<=numberOfWeeks;m++){
            sb.append("<table border=\"1\" style=\"width:100%\"><tr>");
            for(int i=-1;i<weekDays.length;i++){
                sb.append("<th>");
                if(i==-1){
                    LocalDate date = LocalDate.of(year, month, day+6);
                    TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
                    int weekNumber = date.get(woy);
                    sb.append(international.getTimeWords()[2] + " " +  weekNumber);
                    sb.append("</td>");
                } else{
                    sb.append(weekDays[i] + " " + day2);
                }

                sb.append("</th>");
                day2++;
            }
            for(int j=0;j<24;j++){
                sb.append("<tr>");
                for(int k=-1;k<weekDays.length;k++){
                    if(k==-1){
                        sb.append("<td>");
                        sb.append(j + "-" + (j+1) + "h");
                        sb.append("</td>");
                    } else {
                        sb.append("<td");
                        if(day>0 && arrayPetitions[day - 1][j]==null){
                            sb.append(" bgcolor=\"lightgreen\"");
                        }
                        else if (day <= 0 || day > daysInMonth) {
                            sb.append(" bgcolor=\"gray\"");
                        }
                        else if(arrayPetitions[day - 1][j].equals(international.getClosed())){
                            sb.append(" bgcolor=\"red\"");
                        }else{
                            sb.append(" bgcolor=\"lightblue\"");
                        }
                        sb.append(">");
                        if (day > 0) {
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

        sb.append("<h4>" + international.getGeneratedBy() + ": " + System.getProperty("user.name") + "</h4>");
        LocalDateTime dateTime = LocalDateTime.now();
        sb.append("<h4>" + dateTime.getDayOfMonth() + " " + international.getMonths()[dateTime.getMonthValue()-1] +
                " " + dateTime.getYear() + ", " + dateTime.getHour() + ":" + dateTime.getMinute() + ":" + dateTime.getSecond() + "</h4>");
        sb.append("</body>");
        sb.append("</html>");
        FileWriter fstream = null;
        try {
            fstream = new FileWriter(sala + ".html");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(sb.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}