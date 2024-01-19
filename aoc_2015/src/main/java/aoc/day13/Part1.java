package aoc.day13;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day13";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            Map<String, List<Neighbour>> personMap = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                String[] split = line.split(" ");
                String keyName = split[0];
                String plusOrMinus = split[2];
                int value = Integer.parseInt(split[3]);
                String valueName = split[10].substring(0, split[10].length() - 1);
                if (plusOrMinus.equals("lose")) {
                    value *= -1;
                }
                System.out.println("plusOrMinus = " + plusOrMinus);
                System.out.println("valueName = " + valueName);
                System.out.println("value = " + value);
                Neighbour neighbour = new Neighbour(valueName, value);
                if (!personMap.containsKey(keyName)) {
                    personMap.put(keyName, new ArrayList<>());
                }
                personMap.get(keyName).add(neighbour);
                personMap.get(keyName).sort(Comparator.comparingInt(Neighbour::getValue).reversed());

                System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintMap(personMap, "personMap");

            Set<String> persons = personMap.keySet();
            System.out.println("persons = " + persons);
            System.out.println();

            List<Path> resultPaths = new ArrayList<>();

            for (String curPerson : persons) {
                Set<String> visited = new LinkedHashSet<>();
                Path path = new Path(new ArrayList<>(), 0);
                travel(resultPaths, personMap, visited, persons.size(), curPerson, path);
            }
            Helper.prettyPrintList(resultPaths, "resultPaths");

            Integer maxPath = resultPaths.stream().map(Path::getSum).reduce(0, Integer::max);
            System.out.println("maxPath = " + maxPath);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static void travel(List<Path> result, Map<String, List<Neighbour>> personMap, Set<String> visited, int maxSize, String curPerson, Path path) {
        Set<String> newVisited = new LinkedHashSet<>(visited);
        if (newVisited.contains(curPerson)) {
            return;
        }
        newVisited.add(curPerson);
        path.addRoute(curPerson);

        if (newVisited.size() == maxSize) {
            List<String> list = newVisited.stream().toList();
            String startPerson = list.get(0);
            String lastPerson = list.get(list.size() - 1);

            List<Neighbour> startNeighbours = personMap.get(startPerson);
            Neighbour lastNeighbour = startNeighbours.stream().filter(e -> e.getName().equals(lastPerson)).findFirst().get();
            List<Neighbour> lastNeighbours = personMap.get(lastPerson);
            Neighbour startNeighbour = lastNeighbours.stream().filter(e -> e.getName().equals(startPerson)).findFirst().get();
            path.addSum(lastNeighbour.getValue());
            path.addSum(startNeighbour.getValue());
            //
            result.add(path);
            return;
        }
        List<Neighbour> neighbours = personMap.get(curPerson);
        for (Neighbour neighbour : neighbours) {
            travelNextNeighbour(result, personMap, newVisited, maxSize, neighbour.getName(), path, neighbour.getValue(), curPerson);
        }


    }

    private static void travelNextNeighbour(List<Path> result, Map<String, List<Neighbour>> personMap, Set<String> visited, int maxSize, String name, Path path, int value, String prevPerson) {
        if (visited.contains(name)) {
            return;
        }
        ArrayList<String> copyRoute = path.getCopyRoute();
        Path newPath = new Path(copyRoute, path.getNewSum(value));
        //
        List<Neighbour> neighbours = personMap.get(name);
        Neighbour prevNeighbour = neighbours.stream().filter(e -> e.getName().equals(prevPerson)).findFirst().get();
        newPath.addSum(prevNeighbour.getValue());
        //
        travel(result, personMap, visited, maxSize, name, newPath);
    }
}

// map key alice: name: Bob, value: 54 -> value is class Neighbour -> done
// Neighbour name, value -> done
// persons set -> done
// make a visited person set -> done
// and a started that the last person need to sit next to started person -> done
// loop through person set -> done
// loop through each possible route from person map
// sort person map for each person -> done
// take next best option

//573 too low