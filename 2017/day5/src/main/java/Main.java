import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            ArrayList<Integer> instructionList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                instructionList.add(Integer.valueOf(line));

//                    System.out.println("---------------------");
            }

//            System.out.println();
//            System.out.println("instructionList: " + instructionList);
//            System.out.println();
            int part1 = MyHelpers.stepsToReachExitPart1(instructionList);
            System.out.println("part1: " + part1);
            System.out.println();
            int part2 = MyHelpers.stepsToReachExitPart2(instructionList);
            System.out.println("part2: " + part2);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




