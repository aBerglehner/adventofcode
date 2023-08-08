import java.util.ArrayList;
import java.util.List;

public class MyHelpers {

    public static int getPolymerSize(List<String> arrList) {
        List<String> arr = new ArrayList<>(arrList);
        boolean noPairFound;
        while (true) {
            noPairFound = true;
            for (int i = 1; i < arr.size(); ++i) {
                String prev = arr.get(i - 1);
                String cur = arr.get(i);
                if (prev.toLowerCase().equals(cur.toLowerCase())) {
                    if (!prev.equals(cur)) {
                        noPairFound = false;
                        arr.remove(i);
                        arr.remove(i - 1);
                    }
                }
            }
            if (noPairFound) break;
        }
        return arr.size();
    }

}

