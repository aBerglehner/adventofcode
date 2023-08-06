import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            int[] primeValues = {
                    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101
            };
            int result = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                    System.out.println(line);
                List<String> passphrase = Arrays.stream(line.split(" ")).toList();
                Set<Integer> mySet = new HashSet<>();
                for (String phrase : passphrase) {
                    int product = 1;
                    for (char c : phrase.toCharArray()) {
                        product *= primeValues[c - 'a'];
                    }
                    mySet.add(product);
                }
//                System.out.println("passphrase: " + passphrase);
//                System.out.println("mySet: " + mySet);
//                System.out.println();
                result += passphrase.size() == mySet.size() ? 1 : 0;
//                    System.out.println("---------------------");
            }
            System.out.println("result: " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




