package aoc.day7;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum HandType {
    HIGH_CARD("highCard"),
    ONE_PAIR("onePair"),
    TWO_PAIR("twoPair"),
    THREE_KIND("threeKind"),
    FULL_HOUSE("FullHouse"),
    FOUR_KIND("fourKind"),
    FIVE_KIND("fiveKind");

    private final String value;

    HandType(String value) {
        this.value = value;
    }

    public static List<String> getAllTypes() {
        return Arrays.stream(HandType.values())
                .map(HandType::getValue)
                .collect(Collectors.toList());
    }
}
