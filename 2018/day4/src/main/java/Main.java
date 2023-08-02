import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;


public class Main {

    // Define a custom Comparator
    static Comparator<List<String>> comparator = (list1, list2) -> {
        String date1 = list1.get(0);
        String time1 = list1.get(1);
        String date2 = list2.get(0);
        String time2 = list2.get(1);

        // Compare dates first
        int dateComparison = date1.compareTo(date2);
        if (dateComparison != 0) {
            return dateComparison;
        }

        // If dates are equal, compare times
        return time1.compareTo(time2);
    };
    public static void main(String[] args) throws Exception {
            InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
//            InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                ArrayList<List<String>> inputList = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
                    String removedBrackets = Arrays.stream(line.split("]"))
                            .map(part -> part.replace("[", ""))
                            .collect(Collectors.joining(" "));
//                    System.out.println(removedBrackets);
                    List<String> formattedList = Arrays.stream(removedBrackets.split(" "))
                            .filter(part -> !part.isEmpty())
                            .toList();
                    inputList.add(formattedList);
                    System.out.println(formattedList);


//                    System.out.println("---------------------");
                }

                System.out.println("---------------------");
                System.out.println(inputList);
                System.out.println("---------------------");


                // Sort the inputList using the custom Comparator
                Collections.sort(inputList, comparator);

                // Print the sorted inputList
                for (List<String> entry : inputList) {
                    System.out.println(entry);
                }


                reader.close();
            } else {
                System.out.println("No input file not found.");
            }
    }
}




