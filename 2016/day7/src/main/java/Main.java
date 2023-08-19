import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Main {

    private static boolean checkString(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (checkIfTLS(s, i)) return true;
        }
        return false;
    }

    private static boolean checkIfTLS(String line, int startIndex) {
        for (int i = startIndex; i < line.length() - 3; ++i) {
            char first = line.charAt(i);
            char second = line.charAt(i + 1);
            char third = line.charAt(i + 2);
            char fourth = line.charAt(i + 3);

            if (first == second) continue;
            if (first == fourth && second == third) return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int result = 0;
            while ((line = reader.readLine()) != null) {
                StringBuilder outside = new StringBuilder();
                StringBuilder inside = new StringBuilder();
                boolean isInsideBrackets = false;
                boolean isValid = false;
                for (int i = 0; i < line.length(); ++i) {
                    if (line.charAt(i) == '[') {
                        isInsideBrackets = true;
//                        boolean isOutsideTLS = checkString(outside.toString());
////                        outside.setLength(0);
////                        if (isOutsideTLS) {
////                            isValid = true;
////                        }
                    } else if (line.charAt(i) == ']') {
                        isInsideBrackets = false;
                        boolean isInsideTLS = checkString(inside.toString());
//                        inside.setLength(0);
//                        if (isInsideTLS) {
//                            isValid = false;
//                            break;
//                        }
                    } else { /*check if valid TLS*/
                        if (isInsideBrackets) {
                            inside.append(line.charAt(i));
                        } else {
                            outside.append(line.charAt(i));
                        }
                    }
                }

//                if (isValid) result++;
                boolean isOutsideTLS = checkString(outside.toString());
                boolean isInsideTLS = checkString(inside.toString());
                if (isOutsideTLS && !isInsideTLS) result++;

//                System.out.println("line: " + line);
//                System.out.println("outside: " + outside);
//                System.out.println("isOutsideTLS: " + isOutsideTLS);
//                System.out.println("inside: " + inside);
//                System.out.println("isInsideTLS: " + isInsideTLS);
//                System.out.println();
//                System.out.println("result: " + (isOutsideTLS && !isInsideTLS));
//                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("result: " + result);
//            37 was wrong
//            190 is too high
//            126 wrong
//            115 is right


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




