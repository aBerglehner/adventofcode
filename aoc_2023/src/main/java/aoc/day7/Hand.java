package aoc.day7;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Getter
public class Hand {
    @NonNull
    String cards;
    @NonNull
    int typeRating;
    @NonNull
    int cardsRating;
    @NonNull
    int bid;
}
