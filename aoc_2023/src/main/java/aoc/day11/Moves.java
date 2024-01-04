package aoc.day11;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Moves {
    String galaxie;
    int steps;
}
