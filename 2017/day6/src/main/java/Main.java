import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static String getCurArrString(ArrayList<Integer> arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer element : arr) {
            stringBuilder.append(element).append("-");
        }
        return stringBuilder.toString();
    }

    public static int findMaxIndex(ArrayList<Integer> arr) {
        int max = -1;
        int index = -1;
        for (int i = 0; i < arr.size(); ++i) {
            int cur = arr.get(i);
            if (cur > max) {
                max = cur;
                index = i;
            }
        }
        return index;
    }

    public static int getNextIndex(int index, int len) {
        int result = index + 1;
        if (result >= len) result = 0;
        return result;
    }

    public static ArrayList<Integer> distributeArr(int maxIndex, ArrayList<Integer> arr) {
        ArrayList<Integer> newArr = new ArrayList<>(arr);
        int len = newArr.size();
        int max = newArr.get(maxIndex);
        newArr.set(maxIndex, 0);
        int pointer = getNextIndex(maxIndex, len);
        while (max > 0) {
            newArr.set(pointer, newArr.get(pointer) + 1);
            pointer = getNextIndex(pointer, len);
            max--;
        }
        return newArr;
    }

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            final ArrayList<Integer> arr = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                Arrays.stream(line.split("\t")).forEach(e -> arr.add(Integer.valueOf(e)));
            }

            Set<String> seen = new HashSet<>();
            ArrayList<Integer> resultArr = new ArrayList<>(arr);
            int counter = 0;
            while (counter < 100000) {
                String cur = getCurArrString(resultArr);
                if (seen.contains(cur)) {
                    System.out.println("cur: " + cur);
                    System.out.println("counter: " + counter);
                    seen.clear();
                    seen.add(cur);
                    break;
                } else {
                    seen.add(cur);
                }
                int maxIndex = findMaxIndex(resultArr);
                resultArr = distributeArr(maxIndex, resultArr);
                counter += 1;
            }

            System.out.println();

            counter = 0;
            while (counter < 100000) {
                int maxIndex = findMaxIndex(resultArr);
                resultArr = distributeArr(maxIndex, resultArr);
                String cur = getCurArrString(resultArr);
                counter += 1;
                if (seen.contains(cur)) {
                    System.out.println("cur: " + cur);
                    System.out.println("counter: " + counter);
                    break;
                }
            }

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




