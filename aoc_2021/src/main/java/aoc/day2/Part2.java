package aoc.day2;

import aoc.helper.Helper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    @AllArgsConstructor
    @Getter
    private static class Position {
        private int x;
        private int y;
        private int aim;

        public void move(String dir, int moves) {
            if (dir.equals("forward")) {
                this.x += moves;
                this.y += aim * moves;
            } else if (dir.equals("up")) {
                this.aim -= moves;
            } else {//down
                this.aim += moves;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String day = "day2";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            Position position = new Position(0, 0, 0);
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                String[] split = line.split(" ");
                String dir = split[0];
                int moves = Integer.parseInt(split[1]);
                position.move(dir, moves);

                int x = position.getX();
                System.out.println("x = " + x);
                int y = position.getY();
                System.out.println("y = " + y);
                int aim = position.getAim();
                System.out.println("aim = " + aim);
                System.out.println("---------------------");
            }
            System.out.println();
            int x = position.getX();
            System.out.println("x = " + x);
            int y = position.getY();
            System.out.println("y = " + y);
            int aim = position.getAim();
            System.out.println("aim = " + aim);
            int result = x * y;
            System.out.println("result = " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
