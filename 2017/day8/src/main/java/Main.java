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

            int max = 0;
            String line;
            Map<String, Integer> registers = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                List<String> input = Arrays.stream(line.split(" ")).toList();
                String modified = input.get(0);
                String compared = input.get(4);
                String symbol = input.get(5);
                int value = Integer.parseInt(input.get(2));
                int against = Integer.parseInt(input.get(6));
//                System.out.println("compared: " + compared);
                if (!registers.containsKey(modified)) {
                    registers.put(modified, 0);
                }
                if (!registers.containsKey(compared)) {
                    registers.put(compared, 0);
                }
                Integer regC = registers.get(compared);

                if (symbol.equals("==")) {
                    if (regC == against) {
                        valuer(registers, modified, input.get(1), value);
                    }
                }
                if (symbol.equals("!=")) {
                    if (regC != against) {
                        valuer(registers, modified, input.get(1), value);
                    }
                }
                if (symbol.equals(">")) {
                    if (regC > against) {
                        valuer(registers, modified, input.get(1), value);
                    }
                }
                if (symbol.equals(">=")) {
                    if (regC >= against) {
                        valuer(registers, modified, input.get(1), value);
                    }
                }
                if (symbol.equals("<")) {
                    if (regC < against) {
                        valuer(registers, modified, input.get(1), value);
                    }
                }
                if (symbol.equals("<=")) {
                    if (regC <= against) {
                        valuer(registers, modified, input.get(1), value);
                    }
                }

                Optional<Integer> curMax = registers.values().stream().max(Integer::compareTo);
//            System.out.println("registers: " + registers);
                if (curMax.isPresent()) {
//                    System.out.println("result: " + curMax.get());
                    max = curMax.get() > max ? curMax.get() : max;
                } else {
                    System.out.println("max is empty!!!");
                }
//                System.out.println("---------------------");
            }
            System.out.println("max: " + max);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static void valuer(Map<String, Integer> reg, String modified, String arithmetic, int value) {
        int curValue = reg.get(modified);
        int res;
        if (arithmetic.equals("inc")) {
            res = curValue + value;
        } else {
            res = curValue - value;
        }
        reg.put(modified, res);

    }
}




