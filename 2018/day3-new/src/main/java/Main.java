import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws Exception {
//            InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
            InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

            if (inputStream != null) {
                // Wrap the InputStream in a BufferedReader to read the file line by line
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                int rows = 0;
                int cols = 0;
                String line;
                ArrayList<HashMap<String,Integer>> ArrayMap = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    // Process each line of the file
//                    System.out.println(line);

                    String pattern = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)";
                    Pattern regex = Pattern.compile(pattern);
                    Matcher matcher = regex.matcher(line);

                    if (matcher.matches()) {
                        int id = Integer.parseInt(matcher.group(1));
                        int startCol = Integer.parseInt(matcher.group(2));
                        int startRow = Integer.parseInt(matcher.group(3));
                        int colsWidth = Integer.parseInt(matcher.group(4));
                        int rowsHeight = Integer.parseInt(matcher.group(5));

                        int curMaxCol = startCol + colsWidth;
                        int curMaxRow = startRow + rowsHeight;
                        rows = Math.max(rows, curMaxRow);
                        cols = Math.max(cols, curMaxCol);

                        HashMap<String,Integer> map = new HashMap<>();
                        map.put("id", id);
                        map.put("startCol", startCol);
                        map.put("startRow", startRow);
                        map.put("colsWidth", colsWidth);
                        map.put("rowsHeight", rowsHeight);
                        ArrayMap.add(map);

//                        System.out.println("id: " + id);
//                        System.out.println("startCol: " + startCol);
//                        System.out.println("colsWidth: " + colsWidth);
//                        System.out.println("startRow: " + startRow);
//                        System.out.println("rowsHeight: " + rowsHeight);


                    } else {
                        System.out.println("No match found.");
                    }
//                    System.out.println("---------------------");
                }


//                System.out.println("max rows: " + rows);
//                System.out.println("max cols: " + cols);

                ArrayList<ArrayList<Integer>> dynamicArray = MyHelpers.createDynamicArray(rows, cols);
//                MyHelpers.printMatrix(dynamicArray);


                Set<Integer> idSet = new HashSet<>();
                for(HashMap<String,Integer> map : ArrayMap) {
                    int id = map.get("id");
                    int startCol = map.get("startCol");
                    int colsWidth = map.get("colsWidth");
                    int startRow = map.get("startRow");
                    int rowsHeight = map.get("rowsHeight");
                    MyHelpers.drawClaim(dynamicArray,idSet, id, startCol,colsWidth,startRow,rowsHeight);
//                    System.out.println(map);
                }

                  System.out.println("idSet: " + idSet);
//                MyHelpers.printMatrix(dynamicArray);
//                int result = MyHelpers.countClaimsOverOne(dynamicArray);
//                System.out.println("result: " + result);

                reader.close();
            } else {
                System.out.println("No input file not found.");
            }
    }
}




