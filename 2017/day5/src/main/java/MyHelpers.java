import java.util.ArrayList;

public class MyHelpers {
    public static int stepsToReachExitPart1(ArrayList<Integer> instructionList) {
        ArrayList<Integer> arr = new ArrayList<>(instructionList);
        int result = 0;
        int pointer = 0;
        while (pointer >= 0 && pointer < arr.size()) {
            int moves = arr.get(pointer);
            arr.set(pointer, moves + 1);
            pointer += moves;
            result += 1;
        }
        return result;
    }

    public static int stepsToReachExitPart2(ArrayList<Integer> instructionList) {
        ArrayList<Integer> arr = new ArrayList<>(instructionList);
        int result = 0;
        int pointer = 0;
        while (pointer >= 0 && pointer < arr.size()) {
            int moves = arr.get(pointer);
            if (moves >= 3) {
                arr.set(pointer, moves - 1);
            } else {
                arr.set(pointer, moves + 1);
            }
            pointer += moves;
            result += 1;
        }
        return result;
    }

}

