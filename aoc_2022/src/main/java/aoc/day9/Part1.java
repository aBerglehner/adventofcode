package aoc.day9;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day9";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

//            x left - right
//            y up - down
            Cor head = new Cor(0, 0);
            Cor tail = new Cor(0, 0);
            Set<String> visited = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                String[] split = line.split(" ");
                String dir = split[0];
                int motions = Integer.parseInt(split[1]);
                if (dir.equals("R")) {
                    while (--motions >= 0) {
                        head = new Cor(head.x() + 1, head.y());
                        tail = getCor(head, tail);
                        visited.add(getVisitedTail(tail));
                    }
                } else if (dir.equals("L")) {
                    while (--motions >= 0) {
                        head = new Cor(head.x() - 1, head.y());
                        tail = getCor(head, tail);
                        visited.add(getVisitedTail(tail));
                    }
                } else if (dir.equals("U")) {
                    while (--motions >= 0) {
                        head = new Cor(head.x(), head.y() + 1);
                        tail = getCor(head, tail);
                        visited.add(getVisitedTail(tail));
                    }
                } else {//D down
                    while (--motions >= 0) {
                        head = new Cor(head.x(), head.y() - 1);
                        tail = getCor(head, tail);
                        visited.add(getVisitedTail(tail));
                    }
                }
//                    System.out.println("---------------------");
            }
//            System.out.println();
            System.out.println("head = " + head);
            System.out.println("tail = " + tail);
//            System.out.println("visited = " + visited);
            System.out.println("visited.size() = " + visited.size());


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static String getVisitedTail(Cor tail) {
        return String.join("-", String.valueOf(tail.x()), String.valueOf(tail.y()));
    }

    private static Cor getCor(Cor head, Cor tail) {
        int xTail = tail.x();
        int yTail = tail.y();
        int xHead = head.x();
        int yHead = head.y();
        int xDiff = Math.abs(xHead - xTail);
        int yDiff = Math.abs(yHead - yTail);
        if (xDiff == 2 && yDiff == 0) {//vertikal
            int newTailX = (xHead + xTail) / 2;
            return new Cor(newTailX, yTail);
        } else if (xDiff == 0 && yDiff == 2) {//vertikal
            int newTailY = (yHead + yTail) / 2;
            return new Cor(xTail, newTailY);
        } else if (xDiff == 2 && yDiff == 1) {//diagonal
            int newTailX = (xHead + xTail) / 2;
            return new Cor(newTailX, yHead);
        } else if (xDiff == 1 && yDiff == 2) {//diagonal
            int newTailY = (yHead + yTail) / 2;
            return new Cor(xHead, newTailY);
        }
        return tail;
    }
}
