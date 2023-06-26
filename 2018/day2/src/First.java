import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Day1 {
    public static void main(String[] args) throws Exception {
        String test = "./../inputs/test.txt";
        String file = "./../inputs/input.txt";
        BufferedReader reader = new BufferedReader(
            new FileReader(file)
        );
        System.out.println("---------------------");
        // TODO: second test todo
        List<Integer> allResults = new ArrayList<Integer>();

        String line;
        Integer twoCount = 0;
        Integer threeCount = 0;
        while ((line = reader.readLine()) != null) {
            // Process each line of the file
            // System.out.println(line);
            Map<String, Integer> myMap = new HashMap<>();
            for(String c : line.split("")) {
                // System.out.println(c);
                if(myMap.containsKey(c)) {
                    Integer last = myMap.get(c);
                    myMap.put(c, last + 1);
                } else {
                    myMap.put(c,1);
                }
            }
            // System.out.println(myMap);
            // System.out.println("$$$$$$$$$$$");
            //
            // System.out.println(myMap.containsValue(2) || myMap.containsValue(3));
            // System.out.println("---------------------");
            if(myMap.containsValue(2)) twoCount += 1; 
            if(myMap.containsValue(3)) threeCount += 1;

        }
        System.out.println("---------------------");
        System.out.println("twoCount: " + twoCount);
        System.out.println("threeCount: " + threeCount);
        System.out.println("result: " + twoCount * threeCount);

    }
}
