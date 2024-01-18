package aoc.day11;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day11";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                List<String> pw = List.of(line.split(""));

                int counter = 50;
//                while (counter-- > 0 && !(hasTwoPairs(pw) && hasThreeFollowingLetters(pw) && hasOnlyValidLetters(pw))) {
                while (!(hasTwoPairs(pw) && hasThreeFollowingLetters(pw) && hasOnlyValidLetters(pw))) {
                    pw = generateNextPw(pw);
                }
                System.out.println("pw = " + String.join("", pw));
//               abcdefgh -> abcdffaa
//               ghijklmn -> ghjaabcc

//                    System.out.println("---------------------");
            }
            System.out.println();

//            String te = "ghjaabcc";
//            List<String> t = List.of(te.split(""));
//            boolean twoPairs = hasTwoPairs(t);
//            System.out.println("twoPairs = " + twoPairs);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    static final String abc = "abcdefghijklmnopqrstuvwxyz";
    static final List<String> abcList = List.of(abc.split(""));

    private static List<String> generateNextPw(List<String> pwList) {
        int counter = 1;
        List<String> pwCopy = new ArrayList<>(pwList);
        for (int i = pwCopy.size() - 1; i >= 0; i--) {
            String cur = pwCopy.get(i);
            if (counter == 0) {
                break;
            }
            if (cur.equals("z")) {
                pwCopy.set(i, "a");
            } else {
                int indexList = abcList.indexOf(cur);
                String next = abcList.get(indexList + 1);
                pwCopy.set(i, next);
                counter = 0;
            }
        }
//        System.out.println("pwCopy = " + pwCopy);
        return pwCopy;
    }

    private static boolean hasTwoPairs(List<String> pwList) {
        int counter = 0;
        for (int i = 1; i < pwList.size(); i++) {
            String prev = pwList.get(i - 1);
            String cur = pwList.get(i);
            if (prev.equals(cur)) {
                counter++;
                i++;
            }
        }
        return counter >= 2;
    }

    private static boolean hasThreeFollowingLetters(List<String> pwList) {
        List<Integer> lookupSequence = new ArrayList<>();
        for (String s : pwList) {
            int i = abcList.indexOf(s);
            lookupSequence.add(i);
        }

        int counter = 2;
        for (int i = 1; i < lookupSequence.size(); i++) {
            Integer cur = lookupSequence.get(i);
            Integer prev = lookupSequence.get(i - 1);
            if (cur.equals(prev + 1)) {
                counter--;
            } else {
                counter = 2;
            }
            if (counter == 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasOnlyValidLetters(List<String> pwList) {
        List<String> invalid = List.of("i", "o", "l");
        return pwList.stream().noneMatch(invalid::contains);
    }

}
// increase the rightmost letter xx, xy, xz, ya, yb
// don't include letters i,o,l -> should be done with not including them in the abcList
// 3 following letters ->  done
// must have at least 2 pairs aa, bb or zz, aa -> done