package aoc.day13;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@AllArgsConstructor
public class Path {
    private List<String> route;
    private int sum;

    public ArrayList<String> getCopyRoute() {
        return new ArrayList<>(route);
    }

    public void addNext(String name, int value) {
        addRoute(name);
        addSum(value);
    }

    public void addRoute(String name) {
        route.add(name);
    }

    public void addSum(int value) {
        sum += value;
    }


    public int getNewSum(int value) {
        return this.sum + value;
    }
}
