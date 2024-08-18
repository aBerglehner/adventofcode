package aoc.day8;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day8";
        InputStream inputStream = Helper.iStream(day, "inpu");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<Integer>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                List<Integer> row = Arrays.stream(line.split("")).map(e -> Integer.parseInt(e)).toList();
                System.out.println("row = " + row);
                matrix.add(row);

//                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(matrix, "matrix");

            int rows = matrix.size();
            int cols = matrix.get(0).size();
            int[][] visible = new int[rows][cols];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (walk(matrix, row, col, Walkdirs.TOP)
                            || walk(matrix, row, col, Walkdirs.BOTTOM)
                            || walk(matrix, row, col, Walkdirs.LEFT)
                            || walk(matrix, row, col, Walkdirs.RIGHT)) {
                        visible[row][col] = 1;
                    }
                }
            }
            System.out.println("visible after walk = " + Arrays.deepToString(visible));
            System.out.println();
            int sum = Arrays.stream(visible).flatMapToInt(Arrays::stream).sum();
            System.out.println("sum = " + sum);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    enum Walkdirs {
        TOP,
        BOTTOM,
        RIGHT,
        LEFT;
    }

    private static boolean walk(List<List<Integer>> matrix, int curRow, int curCol, Walkdirs walkdirs) {
        Integer val = matrix.get(curRow).get(curCol);
        if (walkdirs.equals(Walkdirs.TOP)) {
            for (int row = curRow - 1; row >= 0; row--) {
                Integer compare = matrix.get(row).get(curCol);
                if (compare >= val) {
                    return false;
                }
            }
        } else if (walkdirs.equals(Walkdirs.BOTTOM)) {
            for (int row = curRow + 1; row < matrix.size(); row++) {
                Integer compare = matrix.get(row).get(curCol);
                if (compare >= val) {
                    return false;
                }
            }
        } else if (walkdirs.equals(Walkdirs.LEFT)) {
            for (int col = curCol - 1; col >= 0; col--) {
                Integer compare = matrix.get(curRow).get(col);
                if (compare >= val) {
                    return false;
                }
            }
        } else {//right
            for (int col = curCol + 1; col < matrix.get(0).size(); col++) {
                Integer compare = matrix.get(curRow).get(col);
                if (compare >= val) {
                    return false;
                }
            }
        }

        return true;
    }
}
