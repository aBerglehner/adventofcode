package aoc.day13;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    private record Pos(int row, int col) {
    }

    public static void main(String[] args) throws Exception {
        String day = "day13";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<String> instructions = new ArrayList<>();
            List<Pos> positions = new ArrayList<>();
            int maxRows = 0;
            int maxCols = 0;
            String curPhase = "Pos";
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                if (line.isBlank()) {
                    curPhase = "fasfdr";
                } else if (curPhase.equals("Pos")) {
                    String[] split = line.split(",");
                    String col = split[0];
                    maxCols = Math.max(maxCols, Integer.parseInt(col));
                    String row = split[1];
                    maxRows = Math.max(maxRows, Integer.parseInt(row));
                    positions.add(new Pos(Integer.parseInt(row), Integer.parseInt(col)));

                } else {
                    instructions.add(line.split(" ")[2]);
                }

//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("positions = " + positions);
            System.out.println("instructions = " + instructions);
            System.out.println("maxRows = " + maxRows);
            System.out.println("maxCols = " + maxCols);

            maxRows = maxRows + 1;
            maxCols = maxCols + 1;
            String[][] matrix = new String[maxRows][maxCols];

            for (int row = 0; row < maxRows; row++) {
                for (int col = 0; col < maxCols; col++) {
                    matrix[row][col] = ".";
                }
            }

            for (Pos position : positions) {
                matrix[position.row][position.col] = "#";
            }
            Helper.printPrimitiveMatrix(matrix);

            for (String instruction : instructions) {
                String[] split = instruction.split("=");
                String horizontalSplit = split[0];
                int value = Integer.parseInt(split[1]);
                if (horizontalSplit.equals("y")) {
                    if (maxRows / 2 == value) {
                        int topRow = 0;
                        for (int row = maxRows - 1; row > value; row--) {
                            for (int col = 0; col < maxCols; col++) {
                                String cur = matrix[row][col];
                                if (cur.equals("#")) {
                                    matrix[topRow][col] = "#";
                                }
                            }
                            topRow++;
                        }
                        String[][] newMatrix = new String[value][maxCols];
                        for (int row = 0; row < value; row++) {
                            for (int col = 0; col < maxCols; col++) {
                                newMatrix[row][col] = matrix[row][col];
                            }
                        }
                        matrix = newMatrix;
                        maxRows = value;

                    } else {
                        System.out.println("horizontal collapse was not in the middle");
                    }
                } else {
                    if (maxCols / 2 == value) {
                        int leftCol = 0;
                        for (int col = maxCols - 1; col > value; col--) {
                            for (int row = 0; row < maxRows; row++) {
                                String cur = matrix[row][col];
                                if (cur.equals("#")) {
                                    matrix[row][leftCol] = "#";
                                }
                            }
                            leftCol++;
                        }
                        String[][] newMatrix = new String[maxRows][value];
                        for (int row = 0; row < maxRows; row++) {
                            for (int col = 0; col < value; col++) {
                                newMatrix[row][col] = matrix[row][col];
                            }
                        }
                        matrix = newMatrix;
                        maxCols = value;
                    } else {
                        System.out.println("vertical collapse was not in the middle");
                    }
                }
            }

            Helper.printPrimitiveMatrix(matrix);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
