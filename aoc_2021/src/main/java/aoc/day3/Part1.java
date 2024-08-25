package aoc.day3;

import aoc.helper.Helper;
import lombok.Value;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
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
            int rows = matrix.size();
            int cols = matrix.get(0).size();
            List<Integer> gammaRate = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                List<Integer> verticalRow = new ArrayList<>();
                for (int row = 0; row < rows; row++) {
                    verticalRow.add(matrix.get(row).get(col));
                }
//                System.out.println("verticalRow = " + verticalRow);
                long countBits = verticalRow.stream().filter(e -> e == 1).count();
                int res = countBits > (verticalRow.size() / 2) ? 1 : 0;
                gammaRate.add(res);
            }

            System.out.println();
            System.out.println("gammaRate = " + gammaRate);

            List<Integer> epsilonRate = gammaRate.stream().map(e -> e ^ 1).toList();
            System.out.println("epsilonRate = " + epsilonRate);
            System.out.println();
            int gammaDecimal = Integer.parseInt(getJoinIntList(gammaRate), 2);
            System.out.println("gammaDecimal = " + gammaDecimal);
            int epsilonDecimal = Integer.parseInt(getJoinIntList(epsilonRate), 2);
            System.out.println("epsilonDecimal = " + epsilonDecimal);
            System.out.println();
            int res = gammaDecimal * epsilonDecimal;
            System.out.println("res = " + res);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static String getJoinIntList(List<Integer> integerList) {
        return integerList.stream().map(String::valueOf).collect(Collectors.joining(""));
    }
}
