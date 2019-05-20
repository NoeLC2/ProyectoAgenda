package output;

import fileclasses.Petition;
import readers.PetitionReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class OutputHTML {
    public static void generateHTML(){

        List<Petition> petitions = PetitionReader.getPetitions();

        YearMonth yearMonthObject = YearMonth.of(2019, 05);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        LocalDate localDate = LocalDate.of(2019, 05, 01);
        LocalDate localDate2 = LocalDate.of(2019, 06, 01);
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
                    sb.append(day);
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