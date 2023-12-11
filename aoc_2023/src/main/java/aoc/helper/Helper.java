package aoc.helper;

import lombok.Getter;

import java.io.InputStream;
import java.util.List;

@Getter
public class Helper {

    public static InputStream iStream(String day, String file) {
        if (file.equals("test") || file.equals("tes") || file.equals("te") || file.equals("t")) {
            return aoc.helper.Helper.class.getResourceAsStream("../../" + day + "/test.txt");
        } else {
            return aoc.helper.Helper.class.getResourceAsStream("../../" + day + "/input.txt");
        }
    }

    public static <T> int findIndexList(T search, List<T> arr) {
        for (int i = 0; i < arr.size(); ++i) {
            if (arr.get(i).equals(search)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> void printMatrix(List<List<T>> matrix) {
        System.out.println();
        System.out.println("Matrix:");
        for (List<T> row : matrix) {
            System.out.println(row);
        }
        System.out.println();
    }

    public static <T> void prettyPrintList(List<T> arr, String... name) {
        System.out.println("-------------------------------------------------------------------");
        if (name.length > 0) {
            System.out.println(String.join(" ", name) + ":");
        }
        arr.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");
    }
}
