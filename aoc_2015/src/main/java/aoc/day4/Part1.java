package aoc.day4;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day4";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                MD5WithLeadingZeros(line, 0);
//                    System.out.println("---------------------");
            }
            System.out.println();

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    public static void MD5WithLeadingZeros(String input, int start) throws NoSuchAlgorithmException {
        String prefix = "00000";

        MessageDigest md = MessageDigest.getInstance("MD5");
        while (true) {
            String curHash = input + ++start;
            byte[] hashBytes = md.digest(curHash.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            String md5Hash = sb.toString();
            if (md5Hash.startsWith(prefix)) {
                System.out.println("start = " + start);
                System.out.println("curHash = " + curHash);
                System.out.println("MD5 Hash: " + md5Hash);
                break;
            }
        }

    }
}
