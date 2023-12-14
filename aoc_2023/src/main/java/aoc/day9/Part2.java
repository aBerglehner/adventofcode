package aoc.day9;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day9";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<List<List<Integer>>> result = new ArrayList<>();
            List<Integer> sumList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                List<Integer> filteredLine = Arrays.stream(line.split(" ")).filter(e -> !e.isBlank()).map(Integer::parseInt).collect(Collectors.toList());
                List<List<Integer>> rowResult = new ArrayList<>();
                rowResult.add(filteredLine);

                while (true) {
                    List<Integer> row = new ArrayList<>();
                    List<Integer> last = rowResult.get(rowResult.size() - 1);

                    for (int i = 1; i < last.size(); i++) {
                        int diff = last.get(i) - last.get(i - 1);
                        row.add(diff);
                    }

                    rowResult.add(row);
                    if (row.stream().allMatch(e -> e.equals(0))) {
                        break;
                    }
                }

                for (int i = rowResult.size() - 1; i >= 0; --i) {
                    List<Integer> curRow = rowResult.get(i);
                    if (i == rowResult.size() - 1) {
                        curRow.add(0, 0);
                    } else {
                        Integer lastOfCurRow = curRow.get(0);
                        List<Integer> prevRow = rowResult.get(i + 1);
                        Integer lastOfPrevRow = prevRow.get(0);
                        int sum = lastOfCurRow - lastOfPrevRow;
                        curRow.add(0, sum);
                        if (i == 0) {
                            sumList.add(sum);
                        }

                    }
                }

                System.out.println("rowResult = " + rowResult);
                result.add(rowResult);
                System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(result, "result");
            System.out.println();
            System.out.println("sumList = " + sumList);
            System.out.println();
            Integer sum = sumList.stream().reduce(0, Integer::sum);
            System.out.println("sum = " + sum);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
