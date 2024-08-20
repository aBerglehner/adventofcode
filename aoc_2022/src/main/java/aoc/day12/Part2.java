package aoc.day12;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {
    private record MyQueue(int row, int col, int steps, Set<String> visited) {
    }

    private record Dirs(int row, int col) {
    }

    private record StartPoint(int row, int col) {
    }

    public static void main(String[] args) throws Exception {
        String day = "day12";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<String>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);

                matrix.add(Arrays.stream(line.split("")).collect(Collectors.toList()));
//                    System.out.println("---------------------");
            }
            System.out.println();
            int rows = matrix.size();
            int cols = matrix.get(0).size();
            List<StartPoint> startPoints = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    //
                    String s = matrix.get(row).get(col);
                    if (s.equals("S") || s.equals("a")) {
                        matrix.get(row).set(col, "a");
                        StartPoint startPoint = new StartPoint(row, col);
                        startPoints.add(startPoint);
                    }
                    char c = s.charAt(0);
//                    System.out.println("c = " + c);
//                    System.out.println("c = " + (c - 'a'));
//                    System.out.println();
                }
            }
            System.out.println();

            Helper.prettyPrintList(matrix, "matrix");

            Dirs up = new Dirs(1, 0);
            Dirs down = new Dirs(-1, 0);
            Dirs right = new Dirs(0, 1);
            Dirs left = new Dirs(0, -1);
            List<Dirs> dirs = List.of(up, down, right, left);

            int minResult = Integer.MAX_VALUE;
            for (StartPoint startPoint : startPoints) {
                int startRow = startPoint.row;
                int startCol = startPoint.col;
                Queue<MyQueue> queueList = new ArrayDeque<>();
                Set<String> set = new HashSet<>();
                set.add(startRow + "-" + startCol);
                MyQueue myQueue = new MyQueue(startRow, startCol, 0, set);
                queueList.add(myQueue);

                while (queueList.size() > 0) {
                    MyQueue poll = queueList.poll();
                    int row = poll.row;
                    int col = poll.col;
                    int steps = poll.steps;
                    if (steps + 1 > minResult) {
                        continue;
                    }
                    String oldStr = matrix.get(row).get(col);
                    for (Dirs dir : dirs) {
                        int newRow = dir.row + row;
                        int newCol = dir.col + col;
                        if (newRow < 0 || newRow >= matrix.size() || newCol < 0 || newCol >= matrix.get(0).size()) {
                            continue;
                        }
                        String visit = newRow + "-" + newCol;
                        if (poll.visited.contains(visit)) {
                            continue;
                        }
                        String newStr = matrix.get(newRow).get(newCol);
                        if (oldStr.equals("z") && newStr.equals("E")) {
                            minResult = Math.min(minResult, steps + 1);
                            break;
                        }
                        if (!oldStr.equals("z") && newStr.equals("E")) {
                            continue;
                        }
                        int oldPos = oldStr.charAt(0) - 'a';
                        int newPos = newStr.charAt(0) - 'a';
                        boolean diff = (newPos - oldPos) <= 1;
                        if (diff) {
                            poll.visited.add(visit);
                            MyQueue newQueue = new MyQueue(newRow, newCol, steps + 1, poll.visited);
                            queueList.add(newQueue);
                        }
                    }
                }
            }
            System.out.println("minResult = " + minResult);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
