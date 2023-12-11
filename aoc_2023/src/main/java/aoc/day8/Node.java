package aoc.day8;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Node {
    @NonNull
    String L;
    @NonNull
    String R;
}
