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

            Map<String, List<String>> myMap = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                List<String> parts = new ArrayList<>(List.of(line.split("\\)")));
//                System.out.println("parts: " + parts);
                String part1 = parts.get(0);
                String part2 = parts.get(1);
                if (!myMap.containsKey(part1)) {
                    myMap.put(part1, new ArrayList<>());
                }
                if (!myMap.containsKey(part2)) {
                    myMap.put(part2, new ArrayList<>());
                }
                myMap.get(part1).add(part2);
                myMap.get(part2).add(part1);

            }

            System.out.println();
            System.out.println("myMap: " + myMap);

            Map<String, Integer> distanceMap = new HashMap<>();
            Deque<StringIntegerPair> deque = new ArrayDeque<>();
            deque.add(new StringIntegerPair("YOU", 0));
            while (!deque.isEmpty()) {
                StringIntegerPair polledPair = deque.poll();
                String cur = polledPair.getStringValue();
                Integer counter = polledPair.getIntValue();
                if (distanceMap.containsKey(cur)) continue;
                distanceMap.put(cur, counter);

                for (String e : myMap.get(cur)) {
                    deque.add(new StringIntegerPair(e, counter + 1));
                }
            }
            System.out.println();
            System.out.println(distanceMap.get("SAN") - 2);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

}




