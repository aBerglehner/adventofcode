package aoc.day4;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day4";
        InputStream inputStream = Helper.iStream(day, "i");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Set<Integer>> result = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                String[] split = line.split(":");
                String[] cards = split[1].split("\\|");
                Set<Integer> winningCards = getCards(cards[0]);
                Set<Integer> ownCards = getCards(cards[1]);
                Set<Integer> rightCards = ownCards.stream().filter(winningCards::contains).collect(Collectors.toSet());
                result.add(rightCards);
//                System.out.println("rightCards: " + rightCards);
//                System.out.println("---------------------");

            }
            System.out.println();
            System.out.println("result: " + result);
            System.out.println();
            int[] scratchCards = new int[result.size()];
            for (int i = 0; i < result.size(); ++i) {
                scratchCards[i] += 1;
                int copy = scratchCards[i];
                while (copy > 0) {
                    for (int j = i + 1; j <= i + result.get(i).size(); ++j) {
                        scratchCards[j] += 1;
                    }
                    copy--;
                }
            }
            System.out.println();
            System.out.println("cards copys: " + Arrays.toString(scratchCards));
            System.out.println();
            System.out.println("sum: " + Arrays.stream(scratchCards).sum());


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static Set<Integer> getCards(String cards) {
        return Arrays.stream(cards.split(" ")).filter(e -> !e.isBlank()).map(Integer::parseInt).collect(Collectors.toSet());
    }
}
