import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kyriba {
    public static void main(String[] args) {
        ArrayList<String> logDates = new ArrayList<>();
        try {
            File f = new File("C:\\Users\\grayb\\IdeaProjects\\Kyriba\\src\\Folder");
            FilenameFilter filter = (f1, name) -> name.endsWith(".log");
            File[] files = f.listFiles(filter);
            PrintWriter f0 = new PrintWriter(new FileWriter("C:\\Users\\grayb\\IdeaProjects\\Kyriba\\src\\Folder\\FilteredLogs.log"));
            for (File file : files) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String regex = "\\d{4}-\\d{2}-\\d{2}";
                        Matcher m = Pattern.compile(regex).matcher(line);
                        if (m.find()) {
                            logDates.add(m.group());
                            f0.println(line);
                        }
                    }
                }
            }
            f0.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        ArrayList<String> sl = new ArrayList<>();
        Set<String> set = new HashSet<>(logDates);
        for (String eDate : set) {
            sl.add(eDate + ": " + Collections.frequency(logDates, eDate));
        }
        Collections.sort(sl);
        sl.forEach(System.out::println);
    }
}
