package aoc.day14;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class Part2 {
    private record Pair(int row, int col) {
    }

    private enum SandOptions {
        START,
        REST,
        ABYSS;
    }

    public static void main(String[] args) throws Exception {
        String day = "day14";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Integer> colWidth = new ArrayList<>();
            List<Integer> rowHeight = new ArrayList<>();
            List<List<Pair>> pairList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                List<Integer> colWidths = Arrays.stream(line.split("->"))
                        .map(String::trim)
                        .map(e -> e.split(",")[0])
                        .map(Integer::parseInt)
                        .toList();
                colWidth.addAll(colWidths);

                List<Integer> rowHeights = Arrays.stream(line.split("->"))
                        .map(String::trim)
                        .map(e -> e.split(",")[1])
                        .map(Integer::parseInt)
                        .toList();
                rowHeight.addAll(rowHeights);

                List<Pair> pairs = Arrays.stream(line.split("->"))
                        .map(String::trim)
                        .map(e -> e.split(","))
                        .map(e -> new Pair(Integer.parseInt(e[1]), Integer.parseInt(e[0]))).toList();
                pairList.add(pairs);

//                    System.out.println("---------------------");
            }
//            System.out.println();
            IntSummaryStatistics colSummeryStatistics = colWidth.stream().mapToInt(e -> e).summaryStatistics();
//            System.out.println("colSummeryStatistics = " + colSummeryStatistics);

            IntSummaryStatistics rowSummaryStatistics = rowHeight.stream().mapToInt(Integer::intValue).summaryStatistics();
//            System.out.println("rowSummaryStatistics = " + rowSummaryStatistics);
            int minCol = colSummeryStatistics.getMin() - 300;
            System.out.println("minCol = " + minCol);
            int maxCol = colSummeryStatistics.getMax() + 300;
            System.out.println("maxCol = " + maxCol);

            System.out.println();
            int rows = rowSummaryStatistics.getMax() + 3;
            System.out.println("rows = " + rows);
            int cols = maxCol - minCol;
            System.out.println("cols = " + cols);

            int col500 = 500 - minCol;
            System.out.println("col500 = " + col500);

            Helper.prettyPrintList(pairList);

            char[][] matrix = new char[rows][cols];
            Helper.printPrimitiveMatrix(matrix);
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (row == rows - 1) {
                        matrix[row][col] = '#';
                    } else {
                        matrix[row][col] = '.';
                    }
                }
            }
            Helper.printPrimitiveMatrix(matrix);
            System.out.println();

            for (List<Pair> pairs : pairList) {
                Pair curPair = pairs.get(0);
                int curRow = curPair.row;
                int curCol = curPair.col - minCol;
                for (int i = 1; i < pairs.size(); i++) {
                    Pair nextPair = pairs.get(i);
                    int nextRow = nextPair.row;
                    int nextCol = nextPair.col - minCol;
                    if (curCol == nextCol) {
                        drawRow(matrix, curRow, nextRow, curCol);
                    } else {
                        drawCol(matrix, curCol, nextCol, curRow);
                    }
                    curRow = nextRow;
                    curCol = nextCol;

                }
            }
            Helper.printPrimitiveMatrix(matrix);
            System.out.println();

            SandOptions curMode = SandOptions.START;
            int counter = 0;
            while (!curMode.equals(SandOptions.ABYSS)) {
                curMode = fallingSand(matrix, 0, col500);
                counter++;
            }
            Helper.printPrimitiveMatrix(matrix);
            System.out.println("counter = " + (counter - 1));


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static SandOptions fallingSand(char[][] matrix, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (row == 0 && matrix[row][col] == 'o') {
            return SandOptions.ABYSS;
        }
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            return SandOptions.ABYSS;
        }
        char cur = matrix[row][col];
        if (cur == '.') {
            return fallingSand(matrix, row + 1, col);
        } else {
            if (col - 1 >= 0 && matrix[row][col - 1] == '.') {//go left
                return fallingSand(matrix, row, col - 1);
            } else if (col + 1 < cols && matrix[row][col + 1] == '.') {//go right
                return fallingSand(matrix, row, col + 1);
            } else {//it came to an hold
                if (row == 0) {
                    matrix[row][col] = 'o';
                } else {
                    matrix[row - 1][col] = 'o';
                }
                return SandOptions.REST;
            }
        }
    }

    private static void drawCol(char[][] matrix, int curCol, int nextCol, int row) {
        int minCol = Math.min(curCol, nextCol);
        int maxCol = Math.max(curCol, nextCol);
        for (int col = minCol; col <= maxCol; col++) {
            matrix[row][col] = '#';
        }
    }

    private static void drawRow(char[][] matrix, int curRow, int nextRow, int col) {
        int minRow = Math.min(curRow, nextRow);
        int maxRow = Math.max(curRow, nextRow);
        for (int row = minRow; row <= maxRow; row++) {
            matrix[row][col] = '#';
        }
    }
}
