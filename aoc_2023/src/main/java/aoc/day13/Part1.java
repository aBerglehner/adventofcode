package aoc.day13;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day13";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<List<String>>> matrixes = new ArrayList<>();
            List<List<String>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                if (line.isBlank()) {
                    matrixes.add(matrix);
                    matrix = new ArrayList<>();
                } else {
                    matrix.add(Arrays.stream(line.split("")).toList());
                }
//                    System.out.println("---------------------");
            }
            matrixes.add(matrix);

            System.out.println();
            Helper.prettyPrintDoubleDeepList(matrixes, "matrixes");
            System.out.println();

            List<Integer> verticalData = getVerticalData(matrixes);
            System.out.println("verticalData = " + verticalData);
            System.out.println();

            List<Integer> horizontalData = getHorizontalData(matrixes);
            System.out.println("horizontalData = " + horizontalData);

            Integer columnsSum = verticalData.stream().reduce(Integer::sum).orElse(0);
            Integer rowsSum = horizontalData.stream().reduce(Integer::sum).orElse(0);

            System.out.println();
            int sum = 100 * rowsSum + columnsSum;
            System.out.println("sum = " + sum);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static List<Integer> getHorizontalData(List<List<List<String>>> matrixes) {
        List<Integer> result = new ArrayList<>();
        for (List<List<String>> matrix : matrixes) {
            int len = matrix.size();
            for (int i = 1; i < len; i++) {
                int bottomLeft = len - i;
                String minSide = i >= bottomLeft ? "upper" : "bottom";
                int upperBound;
                int bottomBound;
                if (minSide.equals("upper")) {
                    upperBound = i - (len - i);
                    bottomBound = len - 1;
                } else {
                    upperBound = 0;
                    bottomBound = i + i - 1;
                }
//                System.out.println("i = " + i);
//                System.out.println("upperBound = " + upperBound);
//                System.out.println("bottomBound = " + bottomBound);
//                System.out.println("--------------");
                if (isHorizontalReflection(matrix, upperBound, bottomBound)) {
//                    System.out.println("horizontal result: " + i);
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    private static List<Integer> getVerticalData(List<List<List<String>>> matrixes) {
        List<Integer> result = new ArrayList<>();
        for (List<List<String>> matrix : matrixes) {
            int len = matrix.get(0).size();
            for (int i = 1; i < len; i++) {
//                System.out.println("i = " + i);
                int rightLeft = len - i;
                String minSide = i >= rightLeft ? "left" : "right";
                int leftBound;
                int rightBound;
                if (minSide.equals("left")) {
                    leftBound = i - (len - i);
                    rightBound = len - 1;
                } else {
                    leftBound = 0;
                    rightBound = i + i - 1;
                }
//                System.out.println("leftBound = " + leftBound);
//                System.out.println("rightBound = " + rightBound);
                if (isVerticalReflection(matrix, leftBound, rightBound)) {
//                    System.out.println("vertical result: " + i);
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    private static boolean isVerticalReflection(List<List<String>> matrix, int left, int right) {
        while (left < right) {
            for (List<String> row : matrix) {
                String l = row.get(left);
                String r = row.get(right);
                if (!l.equals(r)) {
                    return false;
                }
            }
            ++left;
            --right;
        }
        return true;
    }

    private static boolean isHorizontalReflection(List<List<String>> matrix, int top, int bottom) {
        while (top < bottom) {
            int cols = matrix.get(0).size();
            for (int col = 0; col < cols; col++) {
                String t = matrix.get(top).get(col);
                String b = matrix.get(bottom).get(col);
                if (!t.equals(b)) {
                    return false;
                }
            }

            ++top;
            --bottom;
        }
        return true;
    }
}
