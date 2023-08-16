import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    private static void print2DList(ArrayList<ArrayList<String>> matrix) {
        for (ArrayList<String> row : matrix) {
            System.out.println(row);
        }
    }

    private static String mostCommonColumnChar(ArrayList<ArrayList<String>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        StringBuilder result = new StringBuilder();
        for (int col = 0; col < cols; ++col) {
            int[] mostUsed = new int[26];
            for (int row = 0; row < rows; ++row) {
                String cur = matrix.get(row).get(col);
                char c = cur.charAt(0);
                mostUsed[c - 'a']++;
            }
//            System.out.println("mostUsed: " + Arrays.toString(mostUsed));
            int max = getMinValueIndex(mostUsed);
//            System.out.println("max: " + max);
            char c = (char) ('a' + max);
            result.append(c);
//            System.out.println(c);
//            System.out.println();

        }

        return result.toString();
    }

    private static int getMinValueIndex(int[] arr) {
        int result = Integer.MAX_VALUE;
        int curMin = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < curMin && arr[i] != 0) {
                result = i;
                curMin = arr[i];
            }

        }
        return result;
    }

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            ArrayList<ArrayList<String>> matrix = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                    System.out.println(line);
                ArrayList<String> charList = new ArrayList<>();
                Arrays.stream(line.split("")).forEach(charList::add);
                matrix.add(charList);


//                    System.out.println("---------------------");
            }
//            System.out.println("matrix:");
//            print2DList(matrix);
//            System.out.println();
            String result = mostCommonColumnChar(matrix);
            System.out.println("result: " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




