package aoc.day7;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day7";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            List<Hand> hands = new ArrayList<>();
//            System.out.println(Arrays.toString(higherCard));
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                String[] round = line.split(" ");
                if (round.length != 2) {
                    throw new Exception("invalid");
                }
                String cards = round[0];
                int bid = Integer.parseInt(round[1]);
                int typeRating = getTypeRating(cards);
                int cardsRating = getCardsRating(cards);
                hands.add(new Hand(cards, typeRating, cardsRating, bid));

//                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(hands, "hands");
            List<Hand> sortedHands = hands.stream()
                    .sorted((hand1, hand2) ->
                            hand1.getTypeRating() != hand2.getTypeRating()
                                    ? Integer.compare(hand1.getTypeRating(), hand2.getTypeRating())
                                    : Integer.compare(hand1.getCardsRating(), hand2.getCardsRating()))
                    .toList();
            Helper.prettyPrintList(sortedHands, "sortedHands");
            System.out.println();
            int result = IntStream.range(0, sortedHands.size())
                    .map(i -> sortedHands.get(i).getBid() * (i + 1)).sum();
            System.out.println("result: " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int getTypeRating(String cards) {
        final List<String> types = HandType.getAllTypes();
        String type = getType(cards);
        return Helper.findIndexList(type, types);
    }

    private static String getType(String cards) {
        Map<String, Long> groupedString = Arrays.stream(cards.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        System.out.println(groupedString);
        Set<String> keySet = groupedString.keySet();
        //1 == fiveKind
        if (keySet.size() == 1) {
            return HandType.FIVE_KIND.getValue();
        }
        //4 == one pair
        else if (keySet.size() == 4) {
            return HandType.ONE_PAIR.getValue();
        }
        //two pair,threeKind all of size 3
        else if (keySet.size() == 3) {
            Optional<Long> threeKind = groupedString.values().stream().filter(e -> e == 3).findAny();
            if (threeKind.isPresent()) {
                return HandType.THREE_KIND.getValue();
            } else {
                return HandType.TWO_PAIR.getValue();
            }
        }
        //left fourKind, fullHouse all a size 2
        else if (keySet.size() == 2) {
            List<Long> values = groupedString.values().stream().toList();
            Long first = values.get(0);
            Long last = values.get(1);
            if (first == 4 || last == 4) {
                return HandType.FOUR_KIND.getValue();
            } else {
                return HandType.FULL_HOUSE.getValue();
            }

        }// size must be 5 so HighCard
        else {
            return HandType.HIGH_CARD.getValue();
        }
    }

    private static int getCardsRating(String cards) {
        final List<String> higherCard = Arrays.asList("0", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        return Arrays.stream(cards.split(""))
                .map(e -> Helper.findIndexList(e, higherCard))
                .reduce(0, (acc, cur) -> {
                    acc *= 100;
                    acc += cur;
                    return acc;
                });
    }
}
