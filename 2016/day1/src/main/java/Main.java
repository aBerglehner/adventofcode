import com.sun.security.jgss.GSSUtil;

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

            String line;
            List<String> directionList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                Arrays.stream(line.split(",")).map(String::trim).forEach(directionList::add);

//                    System.out.println("---------------------");
            }
            System.out.println();
//            System.out.println("directionList: " + directionList);
//            System.out.println();

            String curDirection = "North";
            int horizontalDistance = 0;
            int verticalDistance = 0;
            boolean vistedTwice = false;
            Set<String> seenCordinates = new HashSet<>();
            for (String direction : directionList) {
                if (vistedTwice) break;
                String turn = direction.substring(0, 1);
                int steps = Integer.parseInt(direction.substring(1));
//                System.out.println("steps: " + steps);
//                System.out.println("turn: " + turn);
                curDirection = getNextDirection(curDirection, turn);
                while (steps > 0) {
                    switch (curDirection) {
                        case "North" -> verticalDistance -= 1;
                        case "South" -> verticalDistance += 1;
                        case "West" -> horizontalDistance -= 1;
                        case "East" -> horizontalDistance += 1;
                    }
                    String seenString = horizontalDistance + "-" + verticalDistance;
                    if (seenCordinates.contains(seenString)) {
                        System.out.println("horizontalDistance: " + horizontalDistance);
                        System.out.println("verticalDistance: " + verticalDistance);
                        vistedTwice = true;
                        break;
                    }
                    seenCordinates.add(seenString);
                    steps--;
                }
            }
            int result = Math.abs(horizontalDistance) + Math.abs(verticalDistance);
            System.out.println("result: " + result);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static String getNextDirection(String curDirection, String turn) {
        if (turn.equals("R")) {
            return switch (curDirection) {
                case "North" -> "East";
                case "East" -> "South";
                case "South" -> "West";
                default -> "North";
            };
        } else { /*Left Turn*/
            return switch (curDirection) {
                case "North" -> "West";
                case "West" -> "South";
                case "South" -> "East";
                default -> "North";
            };
        }
    }

}




