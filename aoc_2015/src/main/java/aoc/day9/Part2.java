package aoc.day9;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day9";
        InputStream inputStream = Helper.iStream(day, "te");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            Set<String> cities = new HashSet<>();
            Map<String, List<Travel>> adjacencyList = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                String[] split = line.split(" ");
                String cityA = split[0];
                String cityB = split[2];
                int distance = Integer.parseInt(split[4]);
                Travel travelA = Travel.builder().city(cityA).distance(distance).build();
                Travel travelB = Travel.builder().city(cityB).distance(distance).build();

                if (!adjacencyList.containsKey(cityA)) {
                    adjacencyList.put(cityA, new ArrayList<>());
                }
                adjacencyList.get(cityA).add(travelB);

                if (!adjacencyList.containsKey(cityB)) {
                    adjacencyList.put(cityB, new ArrayList<>());
                }
                adjacencyList.get(cityB).add(travelA);

                cities.add(cityA);
                cities.add(cityB);
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("cities = " + cities);
            Helper.prettyPrintMap(adjacencyList, "adjacencyList");

            List<TravelWay> result = new ArrayList<>();
            int maxCities = cities.size();
            for (String city : cities) {
                Set<String> visited = new HashSet<>();
                TravelWay travelWay = new TravelWay(new ArrayList<>(), 0);
                travel(result, adjacencyList, visited, maxCities, city, travelWay);
            }

            System.out.println();
            Helper.prettyPrintList(result, "result");

            Optional<Integer> maxDistance = result.stream().map(TravelWay::getDistance).reduce(Integer::max);
            if (maxDistance.isPresent()) {
                Integer max = maxDistance.get();
                System.out.println("max = " + max);
            } else {
                System.out.println("no full path found");
            }


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static void travel(List<TravelWay> result, Map<String, List<Travel>> adjacencyList, Set<String> visited, int maxCities, String curCity, TravelWay travelWay) {
        Set<String> newVisited = new HashSet<>(visited);
        if (newVisited.contains(curCity)) {
            return;
        }
        newVisited.add(curCity);
        travelWay.addPath(curCity);
        if (newVisited.size() == maxCities) {
            result.add(travelWay);
        }
        List<Travel> travels = adjacencyList.get(curCity);
        for (Travel travel : travels) {
            travelNextCity(result, adjacencyList, newVisited, maxCities, travel.getCity(), travelWay, travel.getDistance());
        }
    }

    private static void travelNextCity(List<TravelWay> result, Map<String, List<Travel>> adjacencyList, Set<String> visited, int maxCities, String city, TravelWay travelWay, int distance) {
        if (visited.contains(city)) {
            return;
        }
        ArrayList<String> copyPath = travelWay.getCopyPath();
        TravelWay newTravelWay = new TravelWay(copyPath, travelWay.getNewDistance(distance));
        travel(result, adjacencyList, visited, maxCities, city, newTravelWay);
    }
}

// create class Travel attr city and distance -> done
// get max number of cities -> done
// get adjacency list key city value List<Travel> -> done
// travel through adjacency list till visited == max cities -> done