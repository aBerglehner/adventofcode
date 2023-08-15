import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


public class Main {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            ArrayList<Integer> triangleList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                Arrays.stream(line.split(" "))
                        .map(String::trim)
                        .filter(e -> !e.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .forEach(triangleList::add);

//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("triangleList: " + triangleList);

            ArrayList<ArrayList<Integer>> sorted2dTriangleList = new ArrayList<>();
            HashSet<Integer> seen = new HashSet<>();
            for (int i = 0; i < triangleList.size() - 6; ++i) {
                int first = i;
                int second = i + 3;
                int third = i + 6;
                if (seen.contains(first) || seen.contains(second) || seen.contains(third)) continue;
                seen.add(first);
                seen.add(second);
                seen.add(third);
                ArrayList<Integer> groupedTriangle = new ArrayList<>();
                groupedTriangle.add(triangleList.get(first));
                groupedTriangle.add(triangleList.get(second));
                groupedTriangle.add(triangleList.get(third));
                Collections.sort(groupedTriangle);
                sorted2dTriangleList.add(groupedTriangle);
            }

            System.out.println();
            System.out.println("sorted2dTriangleList: " + sorted2dTriangleList);
            System.out.println();

            int result = 0;
            for (ArrayList<Integer> triangle : sorted2dTriangleList) {
                int smallestSide = triangle.get(0);
                int middleSide = triangle.get(1);
                int largestSide = triangle.get(2);
                if (smallestSide + middleSide > largestSide) result++;
            }

            System.out.println("result: " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




