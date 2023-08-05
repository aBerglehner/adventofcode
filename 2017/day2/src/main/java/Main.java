import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws Exception {
//            InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
            InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

            if (inputStream != null) {
                // Wrap the InputStream in a BufferedReader to read the file line by line
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                ArrayList<Integer> arr = new ArrayList<Integer>();
                String line;
                while ((line = reader.readLine()) != null) {
                    // Process each line of the file
                    line = line.replaceAll("\\s+", " ");
//                    System.out.println(line);
                    ArrayList<Integer> rowArr = new ArrayList<>();
                    Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).forEach(rowArr::add);
//                    System.out.println("rowArr: " +rowArr);
                    for(int i = 0; i < rowArr.size(); ++i){
                        for(int j = i + 1; j < rowArr.size(); ++j) {
                            int max = Math.max(rowArr.get(i),rowArr.get(j));
                            int min = Math.min(rowArr.get(i),rowArr.get(j));
                            if(max % min == 0) {
                                arr.add(max / min);
                            }
                        }
                    }
//                    System.out.println("min: " +min);
//                    System.out.println("max: " + max);

//                    System.out.println("---------------------");
                }
                System.out.println();
                System.out.println("arr: " + arr);
                System.out.println();
                int result = arr.stream().mapToInt(Integer::intValue).sum();
                System.out.println("result: " + result);

                reader.close();
            } else {
                System.out.println("No input file not found.");
            }
    }
}




