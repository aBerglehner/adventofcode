import java.util.ArrayList;
import java.util.Set;

public class MyHelpers {
    public static void printMatrix(ArrayList<ArrayList<Integer>> arr){
        System.out.println();
        System.out.println("Preview Matrix:");
        for(int i = 0; i < arr.size(); ++i) {
            System.out.println(arr.get(i));
        }
        System.out.println();
    }

    public static ArrayList<ArrayList<Integer>> createDynamicArray(int rows, int cols) {
        ArrayList<ArrayList<Integer>> dynamicArray = new ArrayList<>();
        for(int row = 0; row <= rows; ++row) {
            ArrayList<Integer> rowArray = new ArrayList<>();
            for(int col = 0; col <= cols; ++col) {
                rowArray.add(0);
            }
            dynamicArray.add(rowArray);
        }
        return dynamicArray;
    }

    public static void drawClaim(ArrayList<ArrayList<Integer>> arr, Set<Integer> idSet, int id, int startCol, int colsWidth, int startRow, int rowsHeight) {
        boolean isOverlapping = false;
        for(int row = startRow; row < startRow + rowsHeight; ++ row) {
            for(int col = startCol; col < startCol + colsWidth; ++ col) {
                int curId = arr.get(row).get(col);
                if(curId != 0) {
                    isOverlapping = true;
                    idSet.remove(curId);
                }
                arr.get(row).set(col,id);
            }
        }
        if(!isOverlapping) idSet.add(id);
    }

    public static int countClaimsOverOne(ArrayList<ArrayList<Integer>> arr) {
        int result = 0;
        for (int row = 0; row < arr.size(); ++row) {
            for(int col = 0; col < arr.get(row).size(); ++col) {
                if (arr.get(row).get(col) > 1) {
                   ++result;
                }
            }
        }
        return result;
    }

}

