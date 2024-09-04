package aoc.day11;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day11";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<Byte>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                List<Byte> list = Arrays.stream(line.split("")).map(Byte::parseByte).collect(Collectors.toList());
                matrix.add(list);
                //                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(matrix);

            int rows = matrix.size();
            int cols = matrix.get(0).size();
            List<Pos> posList = List.of(
                    new Pos(1, 0),
                    new Pos(-1, 0),
                    new Pos(0, 1),
                    new Pos(0, -1),
                    new Pos(1, 1),
                    new Pos(-1, -1),
                    new Pos(-1, 1),
                    new Pos(1, -1));

            int flashes = 0;
            int count = 0;
            while (count++ <= 1000) {
                Set<String> visited = new HashSet<>();
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        String position = row + "-" + col;
                        if (visited.contains(position)) {
                            continue;
                        }
                        Byte cur = matrix.get(row).get(col);
                        if (cur == 9) {
                            matrix.get(row).set(col, (byte) 0);
                            visited.add(position);
                            flashes++;
                            for (Pos pos : posList) {
                                flashes += flash(matrix, visited, posList, row + pos.r, col + pos.c, 0);
                            }
                        } else {
                            matrix.get(row).set(col, ++cur);
                        }
                    }
                }
                Helper.prettyPrintList(matrix);
                if (visited.size() == 100) {
                    System.out.println("count = " + count);
                    break;
                }
            }
            System.out.println();
            System.out.println("flashes = " + flashes);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int flash(List<List<Byte>> matrix, Set<String> visited, List<Pos> posList, int row, int col, int flashes) {
        if (row < 0 || col < 0 || row >= matrix.size() || col >= matrix.get(0).size()) {
            return flashes;
        }
        String position = row + "-" + col;
        if (visited.contains(position)) {
            return flashes;
        }
        Byte cur = matrix.get(row).get(col);
        if (cur == 9) {
            matrix.get(row).set(col, (byte) 0);
            visited.add(position);
            flashes++;
            for (Pos pos : posList) {
                flashes += flash(matrix, visited, posList, row + pos.r, col + pos.c, 0);
            }
        } else {
            matrix.get(row).set(col, ++cur);
        }
        return flashes;
    }

    private record Pos(int r, int c) {
    }
}
