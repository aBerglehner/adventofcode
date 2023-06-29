import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws Exception {
        String test = "./../inputs/test.txt";
        String file = "./../inputs/input.txt";
        BufferedReader reader = new BufferedReader(
            new FileReader(file)
        );
        // System.out.println("---------------------");
        // TODO: second test todo
        List<List<String>> allResults = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            // Process each line of the file
            // System.out.println(line);
            List<String> curList = new ArrayList<>();
            for(String c : line.split("")) {
                // System.out.println(c);
                curList.add(c);
            }
            allResults.add(curList);
        }
        // System.out.println("---------------------");
        // System.out.println("allResults: " + allResults);
        // System.out.println("---------------------");

        int rows = allResults.size();
        int cols = allResults.get(0).size();
        for(int row = 0; row < rows - 1; ++row) {
            List<String> curRow = allResults.get(row);
            for(int j = row + 1; j < rows; ++j) {
                int diffs = 0;
                List<String> nextRow = allResults.get(j);
                // System.out.println("curRow-" + row + ": " + curRow);
                for(int col = 0; col < cols; ++col) {
                    String cur = curRow.get(col);
                    String next = nextRow.get(col);
                    // System.out.println("cur: " + cur);
                    // System.out.println("next: " + next);
                    // System.out.println("---------------------");
                    if(!cur.equals(next)) {
                        diffs++;
                    }
                }
                // System.out.println("###################################");
                // System.out.println("diffs: " + diffs);
                if(diffs == 1) {
                    System.out.println(String.join("",curRow));
                    // System.out.println("result_row-" + row + ": " + String.join("",curRow));
                    // System.out.println("next___row-" + j + ": " + nextRow);
                }
            }
        }


    }
}
