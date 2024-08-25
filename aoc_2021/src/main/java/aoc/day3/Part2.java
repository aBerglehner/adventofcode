package aoc.day3;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day3";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<Integer>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                List<Integer> list = Arrays.stream(line.split("")).map(Integer::parseInt).toList();
                matrix.add(list);
//                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(matrix);
            List<Integer> oxygen = getOxygen(matrix, 0, Part2::getResOxygen);
            System.out.println("oxygen = " + oxygen);

            List<Integer> scrubber = getOxygen(matrix, 0, Part2::getResScrubber);
            System.out.println("scrubber = " + scrubber);

            int oxygenDec = Integer.parseInt(getJoinIntList(oxygen), 2);
            System.out.println("oxygenDec = " + oxygenDec);
            int scrubberDec = Integer.parseInt(getJoinIntList(scrubber), 2);
            System.out.println("scrubberDec = " + scrubberDec);
            int res = oxygenDec * scrubberDec;
            System.out.println("res = " + res);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static List<Integer> getOxygen(List<List<Integer>> matrix, int col, Function<List<Integer>, Integer> function) {
        int cols = matrix.get(0).size();
        if (matrix.size() == 1 || col == cols) {
            return matrix.get(0);
        }
        int rows = matrix.size();
        List<List<Integer>> newMatrix = new ArrayList<>();
        List<Integer> verticalRow = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            verticalRow.add(matrix.get(row).get(col));
        }
//                System.out.println("verticalRow = " + verticalRow);
        int res = function.apply(verticalRow);

        for (int row = 0; row < rows; row++) {
            Integer cur = matrix.get(row).get(col);
            if (res == cur) {
                newMatrix.add(List.copyOf(matrix.get(row)));
            }
        }
        return getOxygen(newMatrix, col + 1, function);
    }

    private static int getResOxygen(List<Integer> verticalRow) {
        long countBits = verticalRow.stream().filter(e -> e == 1).count();
        return countBits >= (verticalRow.size() - countBits) ? 1 : 0;
    }

    private static int getResScrubber(List<Integer> verticalRow) {
        long countBits = verticalRow.stream().filter(e -> e == 0).count();
        return countBits <= (verticalRow.size() - countBits) ? 0 : 1;
    }

    private static String getJoinIntList(List<Integer> integerList) {
        return integerList.stream().map(String::valueOf).collect(Collectors.joining(""));
    }
}
