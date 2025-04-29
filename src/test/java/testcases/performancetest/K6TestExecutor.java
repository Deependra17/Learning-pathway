package testcases.performancetest;

import java.io.IOException;

public class K6TestExecutor {
    public static void main(String[] args) {
        try {
            // Replace the path with the absolute path to your k6 test script
            String command = "k6 run /home/deependra17/PathwayTest/src/main/loadtest/load-test.js"; // Update this path

            // Execute the command to run k6
            Process process = Runtime.getRuntime().exec(command);

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Print the exit code
            if (exitCode != 0) {
                System.err.println("Error occurred while running k6 test. Exit code: " + exitCode);
            } else {
                System.out.println("k6 test finished successfully with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
