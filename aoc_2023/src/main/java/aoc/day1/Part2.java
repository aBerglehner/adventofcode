package aoc.day1;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day1";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<List<String>> matchedDigits = new ArrayList<>();
            Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|1|2|3|4|5|6|7|8|9)");
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                List<String> matches = new ArrayList<>();

                Matcher matcher = pattern.matcher(line);
                int start = 0;
                while (matcher.find(start)) {
                    matches.add(matcher.group());
                    start = matcher.start() + 1;
                }
                matchedDigits.add(matches);
//                System.out.println("matches: " + matches);
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("matchedDigits: " + matchedDigits);

            List<List<String>> filteredDigits = matchedDigits.stream().map(matches -> List.of(matches.get(0), matches.get(matches.size() - 1))).collect(Collectors.toList());

            System.out.println();
            System.out.println("fd: " + filteredDigits);
            System.out.println();

            List<String> lookup = List.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
            List<String> resultList = filteredDigits.stream().map(digit -> {
                String first = digit.get(0);
                String last = digit.get(1);
                int firstIndex = Helper.findIndexList(first, lookup);
                int lastIndex = Helper.findIndexList(last, lookup);
                String firstDigit = firstIndex == -1 ? first : String.valueOf(firstIndex);
                String lastDigit = lastIndex == -1 ? last : String.valueOf(lastIndex);

                return firstDigit + lastDigit;
            }).collect(Collectors.toList());

            System.out.println("resultList: " + resultList);
            System.out.println();

            int result = resultList.stream().mapToInt(Integer::parseInt).sum();
            System.out.println("result: " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

}
