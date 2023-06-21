import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws Exception {
        String test = "./../inputs/test.txt";
        String file = "./../inputs/input.txt";

        List<Integer> allResults = new ArrayList<Integer>();
        int result = 0;
        int runs = 0;

        while (true) {
            String line;
            BufferedReader reader = new BufferedReader(
                new FileReader(file)
            );
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                String addOrSub = line.substring(0,1);
                String num = line.substring(1,line.length());
                // System.out.println(line);
                if(addOrSub.equals("+")) {
                    result += Integer.parseInt(num);
                } else {
                    result -= Integer.parseInt(num);
                }
                if(allResults.contains(result)) {
                    System.out.println("result: " + result);
                    System.out.println("runs: " + runs);
                    System.exit(0);
                }
                allResults.add(result);
            }
            ++runs;
        }
    }
}
