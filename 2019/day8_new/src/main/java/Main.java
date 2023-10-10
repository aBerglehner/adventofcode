import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws Exception {
//            InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 25 * 6
            // find fewest 0
            // number of number of 1 digits * number of 2 digits
            String line;
            List<Integer> numbersList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
//                     Process each line of the file
                System.out.println(line);
                Arrays.stream(line.split("")).mapToInt(Integer::parseInt).forEach(numbersList::add);
                System.out.println("---------------------");
            }
            System.out.println(numbersList);
            System.out.println("-----------------------------");
            System.out.println(numbersList.size() / 150);
            List<List<Integer>> layers = new ArrayList<>();
            for (int i = 0; i < numbersList.size(); i += 150) {
                List<Integer> layer = new ArrayList<>();
                for (int j = i; j < i + 150; j++) {
                    layer.add(numbersList.get(j));
                }
                layers.add(layer);
            }
            System.out.println(layers);
            System.out.println("------------------");

            List<Integer> result = new ArrayList<>();
            int size = layers.size();
            for (int outer = 0; outer < layers.get(0).size(); ++outer) {
                int inner = 0;
                while (inner < size) {
                    int cur = layers.get(inner).get(outer);
                    if (cur != 2 || inner - 1 == size) {
                        result.add(cur);
                        break;
                    }
                    ++inner;
                }
            }
            System.out.println("result: " + result.stream().map(Object::toString).collect(Collectors.joining()));
            List<List<Integer>> newResult = new ArrayList<>();
            for (int i = 0; i < result.size(); i += 25) {
                List<Integer> cur = new ArrayList<>();
                for (int j = i; j < i + 25; j++) {
                    cur.add(result.get(j));
                }
                newResult.add(cur);
            }
            System.out.println("-----------------");
            System.out.println();
            newResult.forEach(e -> System.out.println(e.stream().map(Object::toString).map(f -> f.equals("0") ? " " : "#").collect(Collectors.joining())));


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




