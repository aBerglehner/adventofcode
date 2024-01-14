package aoc.day4;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Part2 {
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
//                MD5WithLeadingZeros(line, 0);
                int threadCount = 12; // Adjust the number of threads as needed

                List<Integer> result = parallelMD5WithLeadingZeros(line, threadCount);
                System.out.println("result = " + result);
                Integer min = result.stream().reduce(Integer::min).orElse(-1);
                System.out.println("min = " + min);
//                    System.out.println("---------------------");
            }
            System.out.println();

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static List<Integer> parallelMD5WithLeadingZeros(String input, int threadCount) throws NoSuchAlgorithmException {
        List<Integer> result = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        int[] starts = {0, 1000000, 2000000, 3500000, 5000000, 7000000, 9000000, 12000000, 15000000, 20000000, 30000000, 50000000};


        for (int i = 0; i < threadCount; i++) {
            int threadStart = calculateThreadStart(i, threadCount);
            int finalI = i;
            executorService.submit(() -> {
                try {
                    result.add(MD5WithLeadingZeros(input, starts[finalI]));
//                    result.add(MD5WithLeadingZeros(input, threadStart));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static int calculateThreadStart(int threadIndex, int threadCount) {
        long threadStartLong = (long) threadIndex * Integer.MAX_VALUE / threadCount;
        return (int) threadStartLong;
    }

    public static int MD5WithLeadingZeros(String input, int start) throws NoSuchAlgorithmException {

        int count = start;
        String prefix = "000000";
        MessageDigest md = MessageDigest.getInstance("MD5");
        while (true) {
            String curString = input + ++count;
            byte[] hashBytes = md.digest(curString.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            String md5Hash = sb.toString();
            if (md5Hash.startsWith(prefix)) {
                System.out.println("start = " + start);
                System.out.println("answer = " + count);
                break;
            }
        }
        return count;
    }
}