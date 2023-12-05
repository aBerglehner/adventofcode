package aoc.day3;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day3";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<List<String>> matrix = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                matrix.add(List.of(line.split("")));
//                    System.out.println("---------------------");
            }
            System.out.println();
//            System.out.println("matrix: " + matrix);
            Helper.printMatrix(matrix);

            int rows = matrix.size();
            int cols = matrix.get(0).size();
            Map<String, List<Integer>> lookup = new HashMap<>();

            for (int row = 0; row < rows; row++) {
                boolean isAdjacend = false;
                int curNumber = 0;
                String adjacendKeyName = "";
                for (int col = 0; col < cols; col++) {
                    String cur = matrix.get(row).get(col);
                    if (cur.matches("\\d")) {
//                        System.out.println("cur: " + cur);
                        curNumber *= 10;
                        curNumber += Integer.parseInt(cur);
                        if (!isAdjacend) {
                            adjacendKeyName = checkIsAdjacend(row, col, matrix);
                        }
                        if (!adjacendKeyName.isEmpty()) {
                            isAdjacend = true;
                        }
                        if (col + 1 == cols || !matrix.get(row).get(col + 1).matches("\\d")) {
                            if (isAdjacend) {
                                if (!lookup.containsKey(adjacendKeyName)) {
                                    lookup.put(adjacendKeyName, new ArrayList<>());
                                }
                                lookup.get(adjacendKeyName).add(curNumber);
//                                System.out.println("curNumber: " + curNumber);
//                                System.out.println("adjacendKeyName: " + adjacendKeyName);
//                                System.out.println();
                            }
                            curNumber = 0;
                            isAdjacend = false;
                            adjacendKeyName = "";
                        }
                    }
                }
            }
            System.out.println();
            System.out.println(lookup);
            Integer sum = lookup.values().stream()
                    .filter(arr -> arr.size() == 2)
                    .map(arr -> arr.get(0) * arr.get(1))
                    .reduce(0, Integer::sum);
            System.out.println("sum: " + sum);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static String checkIsAdjacend(int row, int col, List<List<String>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        //left
        if (col - 1 >= 0 && matchesSymbol(row, col - 1, matrix)) {
            return extractKey(row, col - 1);
        }
        //right
        if (col + 1 < cols && matchesSymbol(row, col + 1, matrix)) {
            return extractKey(row, col + 1);
        }
        //top
        if (row - 1 >= 0 && matchesSymbol(row - 1, col, matrix)) {
            return extractKey(row - 1, col);
        }
        //bottom
        if (row + 1 < rows && matchesSymbol(row + 1, col, matrix)) {
            return extractKey(row + 1, col);
        }
        //left top
        if (col - 1 >= 0 && row - 1 >= 0 && matchesSymbol(row - 1, col - 1, matrix)) {
            return extractKey(row - 1, col - 1);
        }
        //left bottom
        if (col - 1 >= 0 && row + 1 < rows && matchesSymbol(row + 1, col - 1, matrix)) {
            return extractKey(row + 1, col - 1);
        }
        //right top
        if (col + 1 < cols && row - 1 >= 0 && matchesSymbol(row - 1, col + 1, matrix)) {
            return extractKey(row - 1, col + 1);
        }
        //right bottom
        if (col + 1 < cols && row + 1 < rows && matchesSymbol(row + 1, col + 1, matrix)) {
            return extractKey(row + 1, col + 1);
        }
        return "";
    }

    private static String extractKey(int row, int col) {
        return "row:" + row + "col:" + col;
    }

    private static boolean matchesSymbol(int row, int col, List<List<String>> matrix) {
        return matrix.get(row).get(col).matches("[*]");
    }
}
