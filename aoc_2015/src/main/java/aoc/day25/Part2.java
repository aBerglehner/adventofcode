package aoc.day25;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day25";
        InputStream inputStream = Helper.iStream(day, "test");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
//                    System.out.println("---------------------");
            }
//            System.out.println();


            System.out.println();
            System.out.println("---------------------------------------");
            System.out.println("test start");
            System.out.println("matrix:");
            int[][] matrix2 = getMatrix(6, 6);
            for (int[] row : matrix2) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            long codeNumber = getCodeNumber(11);
            System.out.println("codeNumber = " + codeNumber);
            System.out.println("test end");
            System.out.println();
            System.out.println();

            //2947, column 3029
            int[][] matrix = getMatrix(2948, 3030);
            int value = matrix[2946][3028];
            System.out.println("value = " + value);

//            System.out.println(20151125L * 252533L % 33554393);
            long result = getCodeNumber(value);
            System.out.println("result = " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int[][] getMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 && col == 0) {
                    matrix[row][col] = 1;
                } else if (row != 0 && col == 0) {
                    matrix[row][col] = matrix[row - 1][col + 1] - 1;
                } else {
                    //normal case
                    matrix[row][col] = matrix[row][col - 1] + (row + 1 + col);
                }
            }
        }
        return matrix;
    }

    private static long getCodeNumber(int end) {
        // how to calculate
        // take previous one * 252533
        // than keep remainder from dividing that by value 33554393
        long[] arr = new long[end];
        arr[0] = 20151125L;
        for (int i = 1; i < end; i++) {
            arr[i] = arr[i - 1] * 252533L % 33554393;
        }
        return arr[end - 1];
    }
}
//search 2947, column 3029
//19749359 too low