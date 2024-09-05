package aoc.day12;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day12";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            Map<String, List<String>> adjList = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                String[] split = line.split("-");
                String l = split[0];
                String r = split[1];

                if (!adjList.containsKey(l)) {
                    adjList.put(l, new ArrayList<>());
                }
                adjList.get(l).add(r);
                if (!adjList.containsKey(r)) {
                    adjList.put(r, new ArrayList<>());
                }
                adjList.get(r).add(l);
                System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintMap(adjList);
            Map<String, Integer> visited = new HashMap<>();
            int ways = dfs(adjList, visited, "start", 0);
            System.out.println("ways = " + ways);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int dfs(Map<String, List<String>> adjList, Map<String, Integer> visited, String cave, int ways) {
        if (cave.equals("end")) {
            return 1;
        }
        if (cave.toLowerCase().equals(cave)) {
            if (!visited.containsKey(cave)) {
                visited.put(cave, 0);
            }
            visited.put(cave, visited.get(cave) + 1);

            int moreThanOnce = 0;
            for (Integer visits : visited.values()) {
                if (visits > 1) {
                    moreThanOnce++;
                }
                if (visits > 2) {
                    visited.put(cave, visited.get(cave) - 1);
                    return 0;
                }
            }
            if (moreThanOnce > 1) {
                visited.put(cave, visited.get(cave) - 1);
                return 0;
            }

        }
        List<String> neighbourList = adjList.get(cave);
        for (String neighbour : neighbourList) {
            if (neighbour.equals("start")) {
                continue;
            }
            ways += dfs(adjList, visited, neighbour, 0);
        }
        if (visited.containsKey(cave)) {
            visited.put(cave, visited.get(cave) - 1);
        }
        return ways;
    }
}
