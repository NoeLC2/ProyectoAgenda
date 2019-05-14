package output;

import fileclasses.Petition;
import readers.PetitionReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputHTML {
    public static void generateHTML(){
        List<Petition> petitions = PetitionReader.getPetitions();
        String[] weekDays = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Title Of the page");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body>");
        for(int i=0;i<4;i++){
            sb.append("<table border=\"1\" style=\"width:100%\"><tr>");
            for(i=0;i<weekDays.length;i++){
                sb.append("<th>");
                sb.append(weekDays[i]);
                sb.append("</th>");
            }
            for(int j=0;j<24;j++){
                sb.append("<tr>");
                for(int k=0;k<weekDays.length;k++){
                    sb.append("<td>");
                    sb.append("foobar");
                    sb.append("</td>");
                }
                sb.append("</tr>");
            }
            sb.append("</table>");
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