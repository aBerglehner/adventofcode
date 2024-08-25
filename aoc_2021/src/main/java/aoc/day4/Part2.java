package aoc.day4;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day4";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            int count = 0;
            String line;
            List<Integer> bingoNumbers = new ArrayList<>();
            List<List<List<Integer>>> matrixContainer = new ArrayList<>();
            List<List<Integer>> matrix = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                ++count;
//                System.out.println(line);
                if (count >= 3) {//matrix case
                    if (line.isBlank()) {
                        matrixContainer.add(matrix);
                        matrix = new ArrayList<>();
                    } else {
                        List<Integer> list = Arrays.stream(line.split(" ")).filter(e -> !e.isBlank()).map(Integer::parseInt).toList();
                        matrix.add(list);
                    }
                } else if (count == 1) {//bingo numbers
                    List<Integer> list = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
                    bingoNumbers.addAll(list);
                }
            }
            System.out.println("---------------------");
            matrixContainer.add(matrix);
            System.out.println();
//            System.out.println("bingoNumbers = " + bingoNumbers);
            System.out.println();
//            Helper.prettyPrintDoubleDeepList(matrixContainer);


            long startTime = System.nanoTime();


            List<BingoResult> allBingoResults = getAllBingoResults(bingoNumbers, matrixContainer);
//            Helper.prettyPrintList(allBingoResults);
            int lastBingoElement = allBingoResults.size() - 1;
            int sum = getSum(allBingoResults.get(lastBingoElement));
            System.out.println("sum = " + sum);
            Integer lastBingoNumber = allBingoResults.get(lastBingoElement).bingoNumbers.get(allBingoResults.get(lastBingoElement).bingoNumbers.size() - 1);
            int res = sum * lastBingoNumber;
            System.out.println("res = " + res);


            System.out.println();
            // Record the end time
            long endTime = System.nanoTime();
            // Calculate the elapsed time
            long elapsedTime = endTime - startTime;
            // Output the elapsed time
            System.out.println("Elapsed time in nanoseconds: " + elapsedTime);
            System.out.println("Elapsed time in milliseconds: " + elapsedTime / 1_000_000);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int getSum(BingoResult firstBingoMatrix) {
        int sum = 0;
        for (int row = 0; row < firstBingoMatrix.matrix.size(); row++) {
            for (int col = 0; col < firstBingoMatrix.matrix.get(0).size(); col++) {
                Integer cur = firstBingoMatrix.matrix.get(row).get(col);
                if (!firstBingoMatrix.bingoNumbers.contains(cur)) {
                    sum += cur;
                }
            }
        }
        return sum;
    }

    private static List<BingoResult> getAllBingoResults(List<Integer> bingoNumbers, List<List<List<Integer>>> matrixContainer) {
        List<BingoResult> result = new ArrayList<>();
        int[] markedMatrix = new int[matrixContainer.size()];
        int start = 5;
        int end = bingoNumbers.size() - 1;
        while (start <= end) {
            List<Integer> curBingoNumbers = bingoNumbers.stream().limit(start).toList();
            for (int i = 0; i < matrixContainer.size(); i++) {

                if (markedMatrix[i] == 0) {
                    List<List<Integer>> curMatrix = matrixContainer.get(i);
                    BingoResult isRow = checkRows(curBingoNumbers, curMatrix);
                    if (isRow.result != -1) {
                        markedMatrix[i] = 1;
                        result.add(isRow);
                    }

                    BingoResult isCol = checkCols(curBingoNumbers, curMatrix);
                    if (isCol.result != -1) {
                        markedMatrix[i] = 1;
                        result.add(isCol);
                    }
                }
            }
            start++;
        }
        return result;
    }

    private static BingoResult checkRows(List<Integer> bingoNumbers, List<List<Integer>> matrix) {
//        System.out.println("checkRows");
        for (int i = 0; i < matrix.size(); i++) {
            List<Integer> row = matrix.get(i);
            if (bingoNumbers.containsAll(row)) {
                return new BingoResult(matrix, bingoNumbers, Cases.ROW, i);
            }
        }
        return new BingoResult(new ArrayList<>(), new ArrayList<>(), Cases.NOTHING, -1);
    }

    private static BingoResult checkCols(List<Integer> bingoNumbers, List<List<Integer>> matrix) {
//        System.out.println("checkRows");
        for (int col = 0; col < matrix.get(0).size(); col++) {
            List<Integer> wholeCol = new ArrayList<>();
            for (List<Integer> row : matrix) {
                wholeCol.add(row.get(col));
            }
            if (bingoNumbers.containsAll(wholeCol)) {
                return new BingoResult(matrix, bingoNumbers, Cases.COL, col);
//                return col;
            }
        }
        return new BingoResult(new ArrayList<>(), new ArrayList<>(), Cases.NOTHING, -1);
    }

    private enum Cases {
        ROW,
        COL,
        NOTHING;
    }

    private record BingoResult(List<List<Integer>> matrix, List<Integer> bingoNumbers, Cases c, int result) {
    }
}
