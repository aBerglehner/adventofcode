package aoc.day12;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part1 {
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
            Set<String> visited = new HashSet<>();
            int ways = dfs(adjList, visited, "start", 0);
            System.out.println("ways = " + ways);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int dfs(Map<String, List<String>> adjList, Set<String> visited, String cave, int ways) {
        if (cave.equals("end")) {
            return 1;
        }
        if (cave.toLowerCase().equals(cave) && visited.contains(cave)) {
            return 0;
        }
        if (cave.toLowerCase().equals(cave)) {
            visited.add(cave);
        }
        List<String> neighbourList = adjList.get(cave);
        for (String neighbour : neighbourList) {
            if (neighbour.equals("start")) {
                continue;
            }
            ways += dfs(adjList, visited, neighbour, 0);
        }
        visited.remove(cave);
        return ways;
    }
}
