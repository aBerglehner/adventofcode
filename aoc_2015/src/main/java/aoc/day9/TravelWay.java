package aoc.day9;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
public class TravelWay {
    private List<String> path;
    @Getter
    private int distance;

    public ArrayList<String> getCopyPath() {
        return new ArrayList<>(path);
    }

    public int getNewDistance(int distance) {
        return this.distance + distance;
    }

    public void addPath(String city) {
        path.add(city);
    }
}
