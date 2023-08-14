import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<List<String>> instructionsList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                instructionsList.add(new ArrayList<>(List.of(line.split(""))));

//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println(instructionsList);

//            int[][] keyPad = {
//                    {1, 2, 3},
//                    {4, 5, 6},
//                    {7, 8, 9}
//            };
            String[][] keyPad = {
                    {"-", "-", "1", "-", "-"},
                    {"-", "2", "3", "4", "-"},
                    {"5", "6", "7", "8", "9"},
                    {"-", "A", "B", "C", "-"},
                    {"-", "-", "D", "-", "-"},
            };
            int rows = keyPad.length;
            int cols = keyPad[0].length;

            int curRow = 2;
            int curCol = 0;
            List<String> result = new ArrayList<>();
            for (List<String> list : instructionsList) {
                for (String direction : list) {
                    if (direction.equals("U")) {
                        curRow = getNewRow(rows, curRow, direction, keyPad, curCol);
                    } else if (direction.equals("D")) {
                        curRow = getNewRow(rows, curRow, direction, keyPad, curCol);
                    } else if (direction.equals("L")) {
                        curCol = getNewCol(cols, curCol, direction, keyPad, curRow);
                    } else { /*direction must be R*/
                        curCol = getNewCol(cols, curCol, direction, keyPad, curRow);
                    }
                }
//                System.out.println("curRow: " + curRow);
//                System.out.println("curCol: " + curCol);
                result.add(keyPad[curRow][curCol]);
//                break;
            }

            System.out.println("result: " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int getNewRow(int rows, int curRow, String direction, String[][] keyPad, int curCol) {
        if (direction.equals("D")) {
            if (curRow + 1 < rows && !keyPad[curRow + 1][curCol].equals("-")) {
                return curRow + 1;
            } else { /*row is on last row*/
                return curRow;
            }
        } else { /*direction is U*/
            if (curRow - 1 >= 0 && !keyPad[curRow - 1][curCol].equals("-")) {
                return curRow - 1;
            } else { /*curRow is on first row already*/
                return curRow;
            }
        }
    }

    private static int getNewCol(int cols, int curCol, String direction, String[][] keyPad, int curRow) {
        if (direction.equals("R")) {
            if (curCol + 1 < cols && !keyPad[curRow][curCol + 1].equals("-")) {
                return curCol + 1;
            } else { /*col is already on last col*/
                return curCol;
            }
        } else { /*direction is L*/
            if (curCol - 1 >= 0 && !keyPad[curRow][curCol - 1].equals("-")) {
                return curCol - 1;
            } else { /*curCol is already on first col*/
                return curCol;
            }
        }
    }

}




