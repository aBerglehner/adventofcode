package aoc.day6;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day6";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<List<Integer>> matrix = createMatrix(1000, 1000);

            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                //first is col than row
//                System.out.println(line);
                String[] split = line.split(" ");
                String behaviour;
                String start;
                String end;
                if (split.length == 5) {
                    behaviour = split[1];
                    start = split[2];
                    end = split[4];
                } else {
//                    it is 4 and toggle
                    behaviour = split[0];
                    start = split[1];
                    end = split[3];
                }
//                System.out.println("behaviour = " + behaviour);
//                System.out.println("start = " + start);
//                System.out.println("end = " + end);
                String[] startArr = start.split(",");
                int startCol = Integer.parseInt(startArr[0]);
                int startRow = Integer.parseInt(startArr[1]);
                String[] endArr = end.split(",");
                int endCol = Integer.parseInt(endArr[0]);
                int endRow = Integer.parseInt(endArr[1]);
                mutateMatrixOnBehaviour(matrix, behaviour, startCol, endCol, startRow, endRow);

//                    System.out.println("---------------------");
            }
            System.out.println();
//            Helper.prettyPrintList(matrix, "matrix");
            int result = countOnesOfMatrix(matrix);
            System.out.println("result = " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static List<List<Integer>> createMatrix(int rows, int cols) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            List<Integer> rowList = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                rowList.add(0);
            }
            matrix.add(rowList);
        }
        return matrix;
    }

    private static void mutateMatrixOnBehaviour(List<List<Integer>> matrix,
                                                String behaviour, int startCol, int endCol, int startRow, int endRow) {
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                //behaviour = on = 1
                if (behaviour.equals("on")) {
                    matrix.get(row).set(col, 1);
                }
                //behaviour = off = 0
                if (behaviour.equals("off")) {
                    matrix.get(row).set(col, 0);
                }
                //behaviour = toggle = !
                if (behaviour.equals("toggle")) {
                    Integer cur = matrix.get(row).get(col) ^ 1;
                    matrix.get(row).set(col, cur);
                }
            }
        }
    }

    private static int countOnesOfMatrix(List<List<Integer>> matrix) {
        return matrix.stream().flatMap(List::stream).reduce(0, Integer::sum);
    }
}
