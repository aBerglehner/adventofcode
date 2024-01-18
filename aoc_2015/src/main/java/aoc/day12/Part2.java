package aoc.day12;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day12";
        InputStream inputStream = Helper.iStream(day, "te");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                List<String> list = new ArrayList<>(List.of(line.split("")));

                int counter = 5;
                while (counter > 0) {
                    if (!removeNestedRedObjects(list)) {
                        counter--;
                    }
                }
//                System.out.println("list = " + list);
                String join = String.join("", list);
                System.out.println("join = " + join);

                String removedWhiteSpace = getFormatedString(join);
                int sum = Arrays.stream(removedWhiteSpace.split(" ")).filter(e -> !e.isBlank()).mapToInt(Integer::parseInt).sum();
                System.out.println("sum = " + sum);
                System.out.println("---------------------");
            }
//            System.out.println();


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static boolean removeNestedRedObjects(List<String> list) {
        int leftStart = -1;
        int rightEnd = 0;
        for (int i = 0; i < list.size(); i++) {
            List<String> strings = list.subList(i, list.size());
            String join = String.join("", strings);
//                    System.out.println("join = " + join);
            if (join.startsWith("red")) {
                leftStart = getLeftstart(i, list);
                rightEnd = getRightEnd(i, list);
                if (leftStart == -1 || rightEnd == 0) {
                    //return list
                    return false;
                }

//                        System.out.println("leftstart = " + leftStart);
//                        System.out.println("rightEnd = " + rightEnd);
                list.subList(leftStart, rightEnd + 1).clear();
                return true;
            }
        }
        return false;
    }

    private static int getRightEnd(int i, List<String> list) {
        Stack<String> stack = new Stack<>();
        stack.push("{");
        for (int j = i; j < list.size(); j++) {
            String cur = list.get(j);
            if (cur.equals("{")) {
                stack.push("{");
            }
            if (cur.equals("}")) {
                stack.pop();
                if (stack.empty()) {
                    return j;
                }
            }
        }
        return 0;
    }

    private static int getLeftstart(int start, List<String> list) {
        int counter = 1;
        for (int i = start; i >= 0; i--) {
            if (list.get(i).equals("{")) {
                counter--;
                if (counter == 0) {
                    return i;
                }
            }
            if (list.get(i).equals("}")) {
                counter++;
            }

        }
        return -1;
    }

    private static String getFormatedString(String line) {
        String removedBrackets = line.replaceAll("\\[|\\]|:|\\}|\\{|,", " ");
        System.out.println("removedBrackets = " + removedBrackets);
        String replacedString = removedBrackets.replaceAll("\"[^\"]*\"", " ");
        System.out.println("replacedString = " + replacedString);
        String removedWhiteSpace = replacedString.replaceAll("\\s+", " ");
        System.out.println("removedWhiteSpace = " + removedWhiteSpace);
        return removedWhiteSpace;
    }
}
//[1,{"c":"red","b":2},3]

//155750 too high
//113715 too high
//113086 too high