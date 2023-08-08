import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            ArrayList<String> arr = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                Collections.addAll(arr, line.split(""));

//                    System.out.println("---------------------");
            }
            System.out.println();
//            System.out.println("arr: " + arr);
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            List<String> alphabetArr = Arrays.stream(alphabet.split("")).toList();

            List<Integer> resultList = new ArrayList<>();
            for (String s : alphabetArr) {
                List<String> newArr = arr.stream().filter(e -> !e.toLowerCase().equals(s)).toList();
                int result = MyHelpers.getPolymerSize(newArr);
                System.out.println("result: " + result);
                resultList.add(result);
            }

//            System.out.println();
//            System.out.println("resultList: " + resultList);
            System.out.println();
            int result = resultList.stream().mapToInt(integer -> integer).min().getAsInt();
            System.out.println("result: " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




