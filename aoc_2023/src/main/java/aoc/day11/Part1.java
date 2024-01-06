package aoc.day11;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {
    final static int CAP_VISITED = 50000;

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

            Map<String, List<Moves>> galaxiesSteps = findGalaxiesSteps(matrix);
            Helper.prettyPrintMap(galaxiesSteps, "galaxiesSteps");
            System.out.println("galaxiesSteps.size() = " + galaxiesSteps.size());


            List<List<Integer>> galaxies = findGalaxies(matrix);
            Helper.prettyPrintList(galaxies, "galaxies");
//            System.out.println("galaxies.size() = " + galaxies.size());

            for (List<Integer> galaxy : galaxies) {
                Integer row = galaxy.get(0);
                Integer col = galaxy.get(1);
//            Integer row = 0;
//            Integer col = 4;


                Set<String> visited = new HashSet<>();
                String start = "r" + row + "c" + col;
                int steps = 0;
                bfsDownLeft(row, col, steps, start, matrix, visited, galaxiesSteps, "downLeft");
                bfsDownRight(row, col, steps, start, matrix, visited, galaxiesSteps, "downRight");
                bfsUpLeft(row, col, steps, start, matrix, visited, galaxiesSteps, "upLeft");
                bfsUpRight(row, col, steps, start, matrix, visited, galaxiesSteps, "upRight");
//                System.out.println();
//                System.out.println("steps = " + steps);
//                System.out.println("start row = " + row);
//                System.out.println("start col = " + col);
                long endTime3 = System.currentTimeMillis();
                long duration3 = endTime3 - startTime;
                long seconds3 = duration3 / 1000;
                long milliseconds3 = duration3 % 1000;
                System.out.println("Duration after start -> " + start + ": " + seconds3 + " sec, " + milliseconds3 + " mill");
            }

            System.out.println("------------------------------------------------------------------");
            long endTime2 = System.currentTimeMillis();
            long duration2 = endTime2 - startTime;
            long seconds2 = duration2 / 1000;
            long milliseconds2 = duration2 % 1000;
            System.out.println("Duration after all visits: " + seconds2 + " sec, " + milliseconds2 + " mill");

            Map<String, Moves> minStepsMoves = galaxiesSteps.entrySet().stream()
                    .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue()
                                    .stream()
                                    .min(Comparator.comparingInt(Moves::getSteps))
                                    .orElse(null)
                    ));
            Helper.prettyPrintMap(minStepsMoves, "minStepsMoves");
            System.out.println();
            Integer sum = minStepsMoves.values().stream().map(Moves::getSteps).reduce(0, Integer::sum);
            System.out.println("sum = " + sum);

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


    private static void bfsDownLeft(int row, int col, int steps, String start, List<List<String>> matrix, Set<String> visited, Map<String, List<Moves>> galaxiesSteps, String path) {
        if (isOutOfBounce(row, col, matrix)) return;
        String cur = "r" + row + "c" + col;
        if (visited.size() < CAP_VISITED) {
            path += cur;
            if (visited.contains(path)) {
                return;
            }
            visited.add(path);
        }
        if (isValid(row, col, steps, matrix)) {
            addGalaxy(steps, start, galaxiesSteps, cur);
        }
        bfsDownLeft(row + 1, col, steps + 1, start, matrix, visited, galaxiesSteps, path);
        bfsDownLeft(row, col - 1, steps + 1, start, matrix, visited, galaxiesSteps, path);
    }

    private static void bfsDownRight(int row, int col, int steps, String start, List<List<String>> matrix, Set<String> visited, Map<String, List<Moves>> galaxiesSteps, String path) {
        if (isOutOfBounce(row, col, matrix)) return;
        String cur = "r" + row + "c" + col;
        if (visited.size() < CAP_VISITED) {
            path += cur;
            if (visited.contains(path)) {
                return;
            }
            visited.add(path);
        }
        if (isValid(row, col, steps, matrix)) {
            addGalaxy(steps, start, galaxiesSteps, cur);
        }
        bfsDownRight(row + 1, col, steps + 1, start, matrix, visited, galaxiesSteps, path);
        bfsDownRight(row, col + 1, steps + 1, start, matrix, visited, galaxiesSteps, path);
    }

    private static void bfsUpLeft(int row, int col, int steps, String start, List<List<String>> matrix, Set<String> visited, Map<String, List<Moves>> galaxiesSteps, String path) {
        if (isOutOfBounce(row, col, matrix)) return;
        String cur = "r" + row + "c" + col;
        if (visited.size() < CAP_VISITED) {
            path += cur;
            if (visited.contains(path)) {
                return;
            }
            visited.add(path);
        }
        if (isValid(row, col, steps, matrix)) {
            addGalaxy(steps, start, galaxiesSteps, cur);
        }
        bfsUpLeft(row - 1, col, steps + 1, start, matrix, visited, galaxiesSteps, path);
        bfsUpLeft(row, col - 1, steps + 1, start, matrix, visited, galaxiesSteps, path);
    }

    private static void bfsUpRight(int row, int col, int steps, String start, List<List<String>> matrix, Set<String> visited, Map<String, List<Moves>> galaxiesSteps, String path) {
        if (isOutOfBounce(row, col, matrix)) return;
        String cur = "r" + row + "c" + col;
        if (visited.size() < CAP_VISITED) {
            path += cur;
            if (visited.contains(path)) {
                return;
            }
            visited.add(path);
        }
        if (isValid(row, col, steps, matrix)) {
            addGalaxy(steps, start, galaxiesSteps, cur);
        }
        bfsUpRight(row - 1, col, steps + 1, start, matrix, visited, galaxiesSteps, path);
        bfsUpRight(row, col + 1, steps + 1, start, matrix, visited, galaxiesSteps, path);
    }

    private static void addGalaxy(int steps, String start, Map<String, List<Moves>> galaxiesSteps, String cur) {
        String galaxy = cur + "-" + start;
        if (galaxiesSteps.containsKey(galaxy)) {
            Moves moves = Moves.builder().steps(steps).galaxie(start).build();
            galaxiesSteps.get(galaxy).add(moves);
        }
    }

    private static boolean isOutOfBounce(int row, int col, List<List<String>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        if (row < 0 || row >= rows) {
            return true;
        }
        if (col < 0 || col >= cols) {
            return true;
        }
        return false;
    }

    private static boolean isValid(int row, int col, int steps, List<List<String>> matrix) {
        return matrix.get(row).get(col).equals("#") && steps > 1;
    }

    private static List<List<Integer>> findGalaxies(List<List<String>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (matrix.get(row).get(col).equals("#")) {
                    List<Integer> moves = new ArrayList<>();
                    moves.add(row);
                    moves.add(col);
                    result.add(moves);
                }
            }
        }
        return result;
    }

    private static Map<String, List<Moves>> findGalaxiesSteps(List<List<String>> matrix) {
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

    private static Map<String, List<Moves>> getAllPairs(Map<String, List<Moves>> galaxiesSteps) {
        Map<String, List<Moves>> pairsMap = new HashMap<>();
        Set<String> keys = galaxiesSteps.keySet();
        List<String> keysList = new ArrayList<>(keys);
        for (int i = 0; i < keysList.size(); i++) {
            for (int j = i + 1; j < keysList.size(); j++) {
                String pair = keysList.get(i) + "-" + keysList.get(j);
                List<Moves> moves = new ArrayList<>();
                pairsMap.put(pair, moves);
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
