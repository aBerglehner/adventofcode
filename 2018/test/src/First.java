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
        // TODO: first test todo
        List<Integer> allResults = new ArrayList<Integer>();

        int result = 0;
        String line;
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
        }
        System.out.println("---------------------");
        System.out.println("result: " + result);

    }
}
