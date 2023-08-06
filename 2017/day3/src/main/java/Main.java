import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws Exception {
//            InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
            InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

            if (inputStream != null) {
                // Wrap the InputStream in a BufferedReader to read the file line by line
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Process each line of the file
//                    System.out.println(line);

//                    System.out.println("---------------------");
                }


                reader.close();
            } else {
                System.out.println("No input file not found.");
            }
    }
}




