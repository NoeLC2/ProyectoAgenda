package output;

import ProcessPetitions.CreateArrayPetitions;
import fileclasses.Config;
import fileclasses.Petition;
import fileclasses.ProcessedPetition;
import readers.PetitionReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class OutputHTML {
    public static void generateHTML(String room, List<ProcessedPetition> processedPetitions, Config config){

        //String[][] arrayPetitions = CreateArrayPetitions.getArray(processedPetitions);
        String[][] arrayPetitions = new String[31][24];
        for(int i = 0; i<30; i++){
            if(i%3==0){
                arrayPetitions[i][5]+= i;
            }
        }
        int month = config.getMonth().getValue();
        int year = config.getYear().getValue();

        List<Petition> petitions = PetitionReader.getPetitions();

        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        LocalDate localDate = LocalDate.of(year, month, 01);
        LocalDate localDate2 = LocalDate.of(year, month, 01);
        java.time.DayOfWeek dayWeek = localDate.getDayOfWeek();


        int day = 2-dayWeek.getValue();
        int day2 = day;

        int numberOfWeeks = (int) ChronoUnit.WEEKS.between(localDate, localDate2);


        String[] weekDays = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Title Of the page");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body>");
        for(int m=0;m<=numberOfWeeks;m++){
            sb.append("<table border=\"1\" style=\"width:100%\"><tr>");
            for(int i=0;i<7;i++){
                sb.append("<th>");
                sb.append(weekDays[i] + " " + day2);
                sb.append("</th>");
                day2++;
            }
            for(int j=0;j<24;j++){
                sb.append("<tr>");
                for(int k=0;k<weekDays.length;k++){
                    sb.append("<td");
                    if(day<=0 || day>daysInMonth){
                        sb.append(" bgcolor=\"gray\"");
                    }
                    sb.append(">");
                    //meter un if que compruebe si day es igual a uno de los days
                    sb.append(arrayPetitions[day][j]);
                    sb.append("</td>");
                    day++;
                }
                sb.append("</tr>");
                day-=7;
            }
            day+=7;
            sb.append("</table></br>");
        }

        sb.append("</body>");
        sb.append("</html>");
        FileWriter fstream = null;
        try {
            fstream = new FileWriter("MyHtml.html");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(sb.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}