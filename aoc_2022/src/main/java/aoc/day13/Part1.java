package aoc.day13;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    private record Pairs(List<Integer> first, List<Integer> firstLength, List<Integer> second,
                         List<Integer> secondLength) {

    }

    public static void main(String[] args) throws Exception {
        String day = "day13";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Pairs> pairList = new ArrayList<>();
            int counter = 0;
            Pairs pairs = new Pairs(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                if (line.isBlank()) {
                    continue;
                }
                if (counter == 2) {
                    pairList.add(pairs);
                    counter = 0;
                    pairs = new Pairs(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                    System.out.println("---------------------");
                }

                System.out.println(line);
                String s = line.replaceAll("[\\[\\]]", "");
                System.out.println("s = " + s);
                List<Integer> list = s.isBlank() ? List.of() : Arrays.stream(s.split(","))
                        .filter(e -> !e.isBlank())
                        .filter(e -> !e.equals(","))
                        .mapToInt(e -> Integer.parseInt(e))
                        .boxed()
                        .toList();
                System.out.println("list = " + list);
                if (counter == 0) {
                    pairs.first.addAll(list);
                    pairs.firstLength.add(0, line.length());
                    System.out.println();
                } else if (counter == 1) {
                    pairs.second.addAll(list);
                    pairs.secondLength.add(0, line.length());
                }
                counter++;
            }
            if (counter == 2) {
                pairList.add(pairs);
            }
            System.out.println();
            Helper.prettyPrintList(pairList);
            System.out.println();

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < pairList.size(); i++) {
                if (getResult(pairList.get(i))) {
                    result.add(i + 1);
                }
            }
            System.out.println("result = " + result);
            Integer sum = result.stream().reduce(0, Integer::sum);
            System.out.println("sum = " + sum);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static boolean getResult(Pairs p) {
        int sizeFirst = p.first.size();
        int sizeSecond = p.second.size();
        if (sizeFirst == 0 && sizeSecond == 0) {
            if (p.firstLength.get(0) < p.secondLength.get(0)) {
                return true;
            }
        }
        for (int i = 0; i < Math.min(sizeFirst, sizeSecond); i++) {
            Integer firstInt = p.first.get(i);
            Integer secondInt = p.second.get(i);
            if (firstInt < secondInt) {
                return true;
            } else if (firstInt > secondInt) {
                return false;
            }
        }
        if (sizeFirst < sizeSecond) {
            return true;
        }
        return false;
    }
}

//6248 too high