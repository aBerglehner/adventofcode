import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Define a custom Comparator
    private static final Comparator<List<String>> comparator = (list1, list2) -> {
        String date1 = list1.get(0);
        String time1 = list1.get(1);
        String date2 = list2.get(0);
        String time2 = list2.get(1);

        // Compare dates first
        int dateComparison = date1.compareTo(date2);
        if (dateComparison != 0) {
            return dateComparison;
        }
        // If dates are equal, compare times
        return time1.compareTo(time2);
    };

    private static int[] guardMostFrequentlyAsleep(HashMap<String, int[]> guardMap) {
        int maxFrequent = -1;
        int curGuard = -1;
        int minute = -1;
        for (String key : guardMap.keySet()) {
            int[] list = guardMap.get(key);
            int[] listResult = getListResults(list);
            if (listResult[0] > maxFrequent) {
                maxFrequent = listResult[0];
                curGuard = Integer.parseInt(key);
                minute = listResult[1];
            }
        }
        return new int[]{curGuard, minute};
    }

    private static int[] getListResults(int[] asleepCounter) {
        int minute = -1;
        int frequent = 0;
        for (int i = 0; i < asleepCounter.length; ++i) {
            if (asleepCounter[i] > frequent) {
                frequent = asleepCounter[i];
                minute = i;
            }
        }
        return new int[]{frequent, minute};
    }

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            ArrayList<List<String>> inputList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                String removedBrackets = Arrays.stream(line.split("]"))
                        .map(part -> part.replace("[", ""))
                        .map(part -> part.replace("#", ""))
                        .collect(Collectors.joining(" "));
                // System.out.println(removedBrackets);
                List<String> formattedList = Arrays.stream(removedBrackets.split(" "))
                        .filter(part -> !part.isEmpty())
                        .toList();
                inputList.add(formattedList);
//                System.out.println(formattedList);

                // System.out.println("---------------------");
            }

            Collections.sort(inputList, comparator);

            String guard = "";
            int fallAsleep = 0;
            HashMap<String, int[]> guardMap = new HashMap<>();
            for (List<String> entry : inputList) {
                if (entry.get(2).equals("Guard")) {
                    guard = entry.get(3);
//                    System.out.println("new guard: " + guard);
                }
                if (entry.get(3).equals("asleep")) {
                    fallAsleep = Integer.parseInt(entry.get(1).split(":")[1]);
//                    System.out.println("fallAsleep: " + fallAsleep);
                } else if (entry.get(3).equals("up")) {
                    if (!guardMap.containsKey(guard)) {
                        int[] asleepCounter = new int[60];
                        guardMap.put(guard, asleepCounter);
                    }
                    int wakeUp = Integer.parseInt(entry.get(1).split(":")[1]);
                    for (int i = fallAsleep; i < wakeUp; ++i) {
                        guardMap.get(guard)[i] += 1;
                    }
                }
//                System.out.println(entry);
            }

            System.out.println("guardMap: " + guardMap);
            System.out.println();
            int[] result = guardMostFrequentlyAsleep(guardMap);
            System.out.println("result List: Guard: " + result[0] + " | freq min: " + result[1]);
            System.out.println("result: " + result[0] * result[1]);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

}
