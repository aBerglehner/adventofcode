import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            ArrayList<String> commandList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
//                System.out.println("line: " + line);
                commandList.add(line);
            }
//            System.out.println();
//            System.out.println("commandList: " + commandList);
            System.out.println();

            HashMap<String, Integer> lookup = new HashMap<>();
            lookup.put("a", 0);
            lookup.put("b", 0);
            lookup.put("c", 0);
            lookup.put("d", 0);
            for (int cIndex = 0; cIndex < commandList.size(); ++cIndex) {
                String command = commandList.get(cIndex);

                String firstThree = command.substring(0, 3);
                int len = firstThree.equals("inc") || firstThree.equals("dec") ? 2 : 3;
                String[] instruction = new String[len];
                int i = 0;
                for (String e : command.split(" ")) {
                    instruction[i] = e;
                    i++;
                }
                String first = instruction[0];

                //inc
                if (first.equals("inc")) {
                    String reg = instruction[1];
                    lookup.put(reg, lookup.get(reg) + 1);
                }
                //dec
                if (first.equals("dec")) {
                    String reg = instruction[1];
                    if (lookup.get(reg) > 0) {
                        lookup.put(reg, lookup.get(reg) - 1);
                    }

                }
                //cpy == copy
                if (first.equals("cpy")) {
                    String reg = instruction[2];
                    // copy value of a register to reg instruction[2]
                    if (lookup.containsKey(instruction[1])) {
                        Integer copyRegValue = lookup.get(instruction[1]);
                        lookup.put(reg, copyRegValue);
                    } else { /*copy value from instruction[1] to reg instruction[2]*/
                        lookup.put(reg, Integer.parseInt(instruction[1]));
                    }

                }
                //jnz == jump
                if (first.equals("jnz")) {
                    String reg = instruction[1];
                    if (lookup.containsKey(reg)) {
                        if (lookup.get(reg) != 0) {
                            cIndex += Integer.parseInt(instruction[2]) - 1;
                        }
                    } else {
                        System.out.println("else case");
                        if (!reg.equals("0")) {
                            cIndex += Integer.parseInt(instruction[2]) - 1;
                        }
                    }
                }

//                System.out.println(Arrays.toString(instruction));
//                System.out.println();

            }
            System.out.println();
            System.out.println("lookup: " + lookup);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




