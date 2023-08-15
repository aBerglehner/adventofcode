import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int sum = 0;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                    System.out.println(line);
                String regex = "([a-z-]+)-(\\d+)\\[([a-z]+)\\]";
                Pattern pattern = Pattern.compile(regex);

                String name = null;
                int sectorId = 0;
                String checksum = null;
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    name = matcher.group(1).replace("-", "");
                    sectorId = Integer.parseInt(matcher.group(2));
                    checksum = matcher.group(3);
                }
                assert name != null;
                HashMap<String, Integer> occMap = new HashMap<>();
                Arrays.stream(name.split("")).forEach(e -> {
                    if (!occMap.containsKey(e)) {
                        occMap.put(e, 0);
                    }
                    occMap.put(e, occMap.get(e) + 1);
                });

                LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
                occMap.entrySet()
                        .stream()
                        .sorted((entry1, entry2) -> {
                            int valueComparison = entry2.getValue().compareTo(entry1.getValue());
                            return valueComparison != 0 ? valueComparison : entry1.getKey().compareTo(entry2.getKey());
                        })
                        .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
                StringBuilder result = new StringBuilder();
                for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                    result.append(entry.getKey());
                }

//                System.out.println("name: " + name);
//                System.out.println("occMap: " + occMap);
//                System.out.println("Sector ID: " + sectorId);
//                System.out.println("result: " + result.toString());
//                System.out.println("Checksum: " + checksum);
//                System.out.println("check: " + result.toString().startsWith(checksum));
//                System.out.println();
                if (result.toString().startsWith(checksum)) sum += sectorId;


//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("sum: " + sum);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




