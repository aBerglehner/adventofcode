package aoc.day11;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day11";
//        InputStream inputStream = Helper.iStream(day, "test");
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<String>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                List<String> row = new ArrayList<>(Arrays.asList(line.split("")));
                matrix.add(row);

//                    System.out.println("---------------------");
            }
            long startTime = System.currentTimeMillis();
            System.out.println();
            Helper.prettyPrintList(matrix, "matrix");
            System.out.println();

            List<Integer> emptyRows = getEmptyRows(matrix);
            List<Integer> emptyCols = getEmptyCols(matrix);
            System.out.println("emptyRows = " + emptyRows);
            System.out.println("emptyCols = " + emptyCols);
            System.out.println();
            duplicateEmptyCols(emptyCols, matrix);
            Helper.prettyPrintList(matrix, "matrix after cols duplicate");
            System.out.println();
            duplicateEmptyRows(emptyRows, matrix);
            Helper.prettyPrintList(matrix, "matrix after rows duplicate");

            Map<String, Integer> galaxiesSteps = findGalaxiesSteps(matrix);
            Helper.prettyPrintMap(galaxiesSteps, "galaxiesSteps");
            System.out.println("galaxiesSteps.size() = " + galaxiesSteps.size());

            System.out.println("------------------------------------------------------------------");
            Integer sum = galaxiesSteps.values().stream().reduce(Integer::sum).orElse(0);
            System.out.println("sum = " + sum);
            System.out.println();

            long endTime = System.currentTimeMillis();
            // Calculate duration in seconds and milliseconds
            long duration = endTime - startTime;
            long seconds = duration / 1000; // Convert milliseconds to seconds
            long milliseconds = duration % 1000; // Remaining milliseconds
            System.out.println("Duration at the end: " + seconds + " sec, " + milliseconds + " mill");


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static Map<String, Integer> findGalaxiesSteps(List<List<String>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        Map<String, List<Moves>> result = new HashMap<>();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (matrix.get(row).get(col).equals("#")) {
                    List<Moves> moves = new ArrayList<>();
                    String cur = "r" + row + "c" + col;
                    result.put(cur, moves);
                }
            }
        }
        return getAllPairs(result);
    }

    private static Map<String, Integer> getAllPairs(Map<String, List<Moves>> galaxiesSteps) {
        Map<String, Integer> pairsMap = new HashMap<>();
        Set<String> keys = galaxiesSteps.keySet();
        List<String> keysList = new ArrayList<>(keys);
        for (int i = 0; i < keysList.size(); i++) {
            for (int j = i + 1; j < keysList.size(); j++) {
                String[] parts = keysList.get(i).substring(1).split("c");
                int startRow = Integer.parseInt(parts[0]); // Convert the first part to an integer (the row number)
                int startCol = Integer.parseInt(parts[1]);
                String[] parts2 = keysList.get(j).substring(1).split("c");
                int endRow = Integer.parseInt(parts2[0]); // Convert the first part to an integer (the row number)
                int endCol = Integer.parseInt(parts2[1]);
                Integer result = Math.abs(endRow - startRow) + Math.abs(endCol - startCol);
                String pair = keysList.get(i) + "-" + keysList.get(j);
                pairsMap.put(pair, result);
            }
        }
        return pairsMap;
    }

    private static List<Integer> getEmptyRows(List<List<String>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        List<Integer> result = new ArrayList<>();
        for (int row = 0; row < rows; ++row) {
            boolean empty = true;
            for (int col = 0; col < cols; ++col) {
                if (matrix.get(row).get(col).equals("#")) {
                    empty = false;
                    break;
                }
            }
            if (empty) {
                result.add(row);
            }
        }
        return result;
    }

    private static List<Integer> getEmptyCols(List<List<String>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        List<Integer> result = new ArrayList<>();
        for (int col = 0; col < cols; ++col) {
            boolean empty = true;
            for (int row = 0; row < rows; ++row) {
                if (matrix.get(row).get(col).equals("#")) {
                    empty = false;
                    break;
                }
            }
            if (empty) {
                result.add(col);
            }
        }
        return result;
    }

    private static void duplicateEmptyCols(List<Integer> emptyCols, List<List<String>> matrix) {
        int rows = matrix.size();
        for (int i = emptyCols.size() - 1; i >= 0; i--) {
            Integer emptyCol = emptyCols.get(i);
            for (int row = 0; row < rows; ++row) {
                matrix.get(row).add(emptyCol + 1, ".");
            }
        }
    }

    private static void duplicateEmptyRows(List<Integer> emptyRows, List<List<String>> matrix) {
        for (int i = emptyRows.size() - 1; i >= 0; i--) {
            Integer emptyRow = emptyRows.get(i);
            List<String> copy = List.copyOf(matrix.get(emptyRow));
            matrix.add(emptyRow, copy);
        }
    }

}
