import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;


public class Main {

    private static String[] getAlphabetArr() {
        String[] alphabet = new String[26];
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            alphabet[i] = String.valueOf(letter);
        }
        return alphabet;
    }

    private static int findIndex(char searched) {
        String[] alphabet = getAlphabetArr();
        int index = -1;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i].charAt(0) == searched) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static String getRotatedName(int sectorId, String name) {
        String[] alphabet = getAlphabetArr();
        int len = alphabet.length;
        StringBuilder result = new StringBuilder();
        for (char c : name.toCharArray()) {
            if (c == '-') {
                result.append(' ');
            } else {
                int charIndex = findIndex(c);
                int rotatedCharIndex = (charIndex + sectorId) % len;
                result.append(alphabet[rotatedCharIndex]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = Main.class.getResourceAsStream("inputs/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("inputs/input.txt");

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // Wrap the InputStream in a BufferedReader to read the file line by line

            String[] alphabet = getAlphabetArr();
            System.out.println("alphabet: " + Arrays.toString(alphabet));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                    System.out.println(line);
                String regex = "([a-z-]+)-(\\d+)\\[([a-z]+)\\]";
                Pattern pattern = Pattern.compile(regex);

                String name = null;
                int sectorId = 0;
                String checksum = null;
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    name = matcher.group(1);
                    sectorId = Integer.parseInt(matcher.group(2));
                    checksum = matcher.group(3);
                }
                assert name != null;
                String rotatedName = getRotatedName(sectorId, name);
                if (rotatedName.contains("north")) {
                    System.out.println();
                    System.out.println("searched sectorId: " + sectorId);
                    System.out.println();
                }

//                    System.out.println("---------------------");
            }


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}




