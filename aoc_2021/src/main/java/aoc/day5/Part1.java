package aoc.day5;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Part1 {
    private record Point(int col, int row) {
    }

    private record Line(Point start, Point end) {
    }

    public static void main(String[] args) throws Exception {
        String day = "day5";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            int rows = 0;
            int cols = 0;
            List<Line> lineList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                List<String> points = Arrays.stream(line.split("->")).map(String::trim).toList();
                String[] firstPoint = points.get(0).split(",");
                //x = cols | y = rows
                int x1 = Integer.parseInt(firstPoint[0]);
                int y1 = Integer.parseInt(firstPoint[1]);
                Point point1 = new Point(x1, y1);

                String[] secondPoint = points.get(1).split(",");
                int x2 = Integer.parseInt(secondPoint[0]);
                int y2 = Integer.parseInt(secondPoint[1]);
                Point point2 = new Point(x2, y2);
                lineList.add(new Line(point1, point2));

                cols = Stream.of(cols, x1, x2).max(Comparator.naturalOrder()).orElse(cols);
                rows = Stream.of(rows, y1, y2).max(Comparator.naturalOrder()).orElse(rows);

                System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(lineList);
            System.out.println("rows = " + rows);
            System.out.println("cols = " + cols);

            int[][] matrix = new int[rows + 1][cols + 1];
            Helper.printPrimitiveMatrix(matrix);

            for (Line l : lineList) {
                Point start = l.start;
                int startRow = start.row;
                int startCol = start.col;

                Point end = l.end;
                int endRow = end.row;
                int endCol = end.col;
                if (startRow == endRow) {//draw col
                    int minCol = Math.min(startCol, endCol);
                    int maxCol = Math.max(startCol, endCol);
                    for (int col = minCol; col <= maxCol; col++) {
                        matrix[startRow][col]++;
                    }
                } else if (startCol == endCol) {//draw row
                    int minRow = Math.min(startRow, endRow);
                    int maxRow = Math.max(startRow, endRow);
                    for (int row = minRow; row <= maxRow; row++) {
                        matrix[row][startCol]++;
                    }
                }
            }
            Helper.printPrimitiveMatrix(matrix);

            int counter = 0;
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < cols; col++) {
                    if (matrix[row][col] > 1) {
                        ++counter;
                    }
                }
            }
            System.out.println("counter = " + counter);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
//17604 too high
