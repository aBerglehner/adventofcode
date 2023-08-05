import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    private static int matchesPrev(int cur, int prev) {
        return cur == prev ? cur : 0;
    }

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            ArrayList<Integer> arr = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                    System.out.println(line);
                Arrays.stream(line.split("")).mapToInt(Integer::parseInt).forEach(arr::add);
//                    System.out.println("---------------------");
            }
            System.out.println("arr: " + arr);
            System.out.println();

            int result = 0;
            int half = arr.size() / 2;
            int len = arr.size();
            for (int i = 0; i < len; ++i) {
                int cur = arr.get(i);
                int nextIndex = (i + half) % len;
                int next = arr.get(nextIndex);
                result += matchesPrev(cur, next);
            }
            System.out.println("result: " + result);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




