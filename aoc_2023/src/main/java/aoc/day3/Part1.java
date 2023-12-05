package aoc.day3;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
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
                System.out.println(line);
                matrix.add(List.of(line.split("")));
//                    System.out.println("---------------------");
            }
            System.out.println();
//            System.out.println("matrix: " + matrix);
            Helper.printMatrix(matrix);

            int rows = matrix.size();
            int cols = matrix.get(0).size();
            List<Integer> result = new ArrayList<>();

            for (int row = 0; row < rows; row++) {
                boolean isAdjacend = false;
                int curNumber = 0;
                for (int col = 0; col < cols; col++) {
                    String cur = matrix.get(row).get(col);
                    if (cur.matches("\\d")) {
//                        System.out.println("cur: " + cur);
                        curNumber *= 10;
                        curNumber += Integer.parseInt(cur);

                        if (checkIsAdjacend(row, col, matrix)) {
                            isAdjacend = true;
                        }
                        if (col + 1 == cols || !matrix.get(row).get(col + 1).matches("\\d")) {
                            if (isAdjacend) {
                                result.add(curNumber);
                            }
//                            System.out.println("curNumber: " + curNumber);
//                            System.out.println();
                            curNumber = 0;
                            isAdjacend = false;
                        }
                    }
                }
            }
            System.out.println();
//            System.out.println("result: " + result);
            System.out.println("sum: " + result.stream().reduce(0, Integer::sum));

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static boolean checkIsAdjacend(int row, int col, List<List<String>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        //left
        if (col - 1 >= 0 && matchesSymbol(row, col - 1, matrix)) {
            return true;
        }
        //right
        if (col + 1 < cols && matchesSymbol(row, col + 1, matrix)) {
            return true;
        }
        //top
        if (row - 1 >= 0 && matchesSymbol(row - 1, col, matrix)) {
            return true;
        }
        //bottom
        if (row + 1 < rows && matchesSymbol(row + 1, col, matrix)) {
            return true;
        }
        //left top
        if (col - 1 >= 0 && row - 1 >= 0 && matchesSymbol(row - 1, col - 1, matrix)) {
            return true;
        }
        //left bottom
        if (col - 1 >= 0 && row + 1 < rows && matchesSymbol(row + 1, col - 1, matrix)) {
            return true;
        }
        //right top
        if (col + 1 < cols && row - 1 >= 0 && matchesSymbol(row - 1, col + 1, matrix)) {
            return true;
        }
        //right bottom
        if (col + 1 < cols && row + 1 < rows && matchesSymbol(row + 1, col + 1, matrix)) {
            return true;
        }
        return false;
    }

    private static boolean matchesSymbol(int row, int col, List<List<String>> matrix) {
        return matrix.get(row).get(col).matches("[^\\d.]");
    }
}
