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
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Title Of the page");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body> <b>Hello World</b>");
        sb.append("<span>" + petitions.toString() + "</span>");
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