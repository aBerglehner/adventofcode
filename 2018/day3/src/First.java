import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
//
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day1 {
    public static void main(String[] args) throws Exception {
        String test = "./../inputs/test.txt";
        String file = "./../inputs/input.txt";
        BufferedReader reader = new BufferedReader(
            new FileReader(test)
        );
        // TODO: first test todo
        List<Integer> allResults = new ArrayList<Integer>();

        String line;
        while ((line = reader.readLine()) != null) {
            // Process each line of the file
            System.out.println(line);

            String pattern = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(line);

            if (matcher.matches()) {
                String id = matcher.group(1);
                String startCol = matcher.group(2);
                String startRow = matcher.group(3);
                String colsWide = matcher.group(4);
                String rowsHigh = matcher.group(5);

                System.out.println("id: " + id);
                System.out.println("startCol: " + startCol);
                System.out.println("startRow: " + startRow);
                System.out.println("colsWide: " + colsWide);
                System.out.println("rowsHigh: " + rowsHigh);
            } else {
                System.out.println("No match found.");
            }
            System.out.println("---------------------");
        }

        System.out.println("---------------------");

    }
}
