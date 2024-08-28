package aoc.day9;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day9";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<Integer>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                matrix.add(Arrays.stream(line.split("")).map(Integer::parseInt).toList());
//                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(matrix);
            List<List<Integer>> basinsMatrix = new ArrayList<>();
            List<Integer> lowPoints = new ArrayList<>();
            int rows = matrix.size();
            int cols = matrix.get(0).size();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (isLowest(matrix, row, col)) {
                        Integer cur = matrix.get(row).get(col);
                        lowPoints.add(cur);
                        List<Integer> basins = getBasins(matrix, row, col);
                        basinsMatrix.add(basins);
                    }

                }
            }
            System.out.println();
            System.out.println("lowPoints = " + lowPoints);
            Helper.prettyPrintList(basinsMatrix);
            List<Integer> list = basinsMatrix.stream().map(List::size).sorted(Comparator.reverseOrder()).limit(3).toList();
            System.out.println("list = " + list);
            Integer res = basinsMatrix.stream().map(List::size).sorted(Comparator.reverseOrder()).limit(3).reduce(1, (acc, cur) -> acc * cur);
            System.out.println("res = " + res);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private record Dir(int row, int col) {
    }

    private record Position(int row, int col) {
    }

    private static List<Integer> getBasins(List<List<Integer>> matrix, int row, int col) {
        List<Dir> dirs = List.of(new Dir(1, 0), new Dir(-1, 0), new Dir(0, 1), new Dir(0, -1));
        Set<String> visited = new HashSet<>();

        List<Integer> res = new ArrayList<>();
        List<Position> resPosition = new ArrayList<>();

        Queue<Position> queue = new ArrayDeque<>();
        queue.add(new Position(row, col));

        while (queue.size() > 0) {
            Position poll = queue.poll();
            int curRow = poll.row;
            int curCol = poll.col;
            Integer cur = matrix.get(curRow).get(curCol);

            String visitedString = curRow + "-" + curCol;
            if (visited.contains(visitedString)) {
                continue;
            }
            visited.add(visitedString);

            res.add(cur);
            resPosition.add(new Position(curRow, curCol));

            for (Dir dir : dirs) {
                int newRow = curRow + dir.row;
                int newCol = curCol + dir.col;
                if (newRow < 0 || newRow > matrix.size() - 1 || newCol < 0 || newCol > matrix.get(0).size() - 1) {
                    continue;
                }
                Integer newCur = matrix.get(newRow).get(newCol);
                String newVisitedString = newRow + "-" + newCol;
                if (newCur != 9 && newCur > cur && !visited.contains(newVisitedString)) {
                    queue.add(new Position(newRow, newCol));
                }
            }
        }
//        Helper.prettyPrintList(resPosition);
        return res;
    }

    private static boolean isLowest(List<List<Integer>> matrix, int row, int col) {
        Integer cur = matrix.get(row).get(col);
        //up
        if (row + 1 < matrix.size() && matrix.get(row + 1).get(col) <= cur) {
            return false;
        }
        //down
        if (row - 1 >= 0 && matrix.get(row - 1).get(col) <= cur) {
            return false;
        }
        //right
        if (col + 1 < matrix.get(0).size() && matrix.get(row).get(col + 1) <= cur) {
            return false;
        }
        //left
        if (col - 1 >= 0 && matrix.get(row).get(col - 1) <= cur) {
            return false;
        }
        return true;
    }
}
// 1568 too high