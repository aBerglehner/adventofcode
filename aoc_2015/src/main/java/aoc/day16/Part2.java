package aoc.day16;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day16";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Map<Aunt, Integer>> aunts = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                int keySplit = 0;
                String[] split = line.split("");
                for (int i = 0; i < split.length; i++) {
                    String s = split[i] + split[i + 1];
                    System.out.println("s = " + s);
                    if (s.equals(": ")) {
                        keySplit = i;
                        break;
                    }
                }
//                System.out.println("keySplit = " + keySplit);
//                String key = line.substring(0, keySplit).replace(" ", "-");
//                System.out.println("key = " + key);
                String valueString = line.substring(keySplit + 1);
//                System.out.println("valueString = " + valueString);
                List<List<String>> keyValueList = Arrays.stream(valueString.split(",")).map(e -> Arrays.stream(e.trim().split(":")).map(f -> f.trim()).toList()).toList();
//                System.out.println("list1 = " + keyValueList);
                Map<Aunt, Integer> auntMap = keyValueList.stream().collect(Collectors.toMap(e -> Aunt.valueOf(e.get(0).toUpperCase()), e -> Integer.valueOf(e.get(1))));
                System.out.println("map = " + auntMap);
                aunts.add(auntMap);

//                System.out.println("---------------------");
//                break;
            }
            System.out.println();
            Helper.prettyPrintList(aunts, "aunts");
            System.out.println();

            Map<Aunt, Integer> auntSue = new HashMap<>();
            auntSue.put(Aunt.CHILDREN, 3);
            auntSue.put(Aunt.CATS, 7);
            auntSue.put(Aunt.SAMOYEDS, 2);
            auntSue.put(Aunt.POMERANIANS, 3);
            auntSue.put(Aunt.AKITAS, 0);
            auntSue.put(Aunt.VIZSLAS, 0);
            auntSue.put(Aunt.GOLDFISH, 5);
            auntSue.put(Aunt.TREES, 3);
            auntSue.put(Aunt.CARS, 2);
            auntSue.put(Aunt.PERFUMES, 1);

            Helper.prettyPrintMap(auntSue, "auntSue");

            System.out.println();
            int result = IntStream.range(0, aunts.size()).filter(i -> {
                Map<Aunt, Integer> aunt = aunts.get(i);
                for (Aunt key : auntSue.keySet()) {
                    if (aunt.containsKey(key)) {
                        // auntSue values must be greater than
                        if (key.equals(Aunt.CATS) || key.equals(Aunt.TREES)) {
                            if (aunt.get(key) <= auntSue.get(key)) {
                                return false;
                            }
                            // auntSue values must be fewer than
                        } else if (key.equals(Aunt.POMERANIANS) || key.equals(Aunt.GOLDFISH)) {
                            if (aunt.get(key) >= auntSue.get(key)) {
                                return false;
                            }
                        } else {
                            if (!auntSue.get(key).equals(aunt.get(key))) {
                                return false;
                            }
                        }
                    }
                }
                System.out.println("result aunt = " + aunt);
                return true;
            }).map(e -> e + 1).findFirst().orElse(-1);
            System.out.println("result = " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
