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

}
