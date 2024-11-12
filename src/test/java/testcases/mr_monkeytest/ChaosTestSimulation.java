package testcases.mr_monkeytest;

import org.testng.annotations.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChaosTestSimulation {
    @Test
    public void chaosTest() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable introduceLatency = () -> {
            System.out.println("Simulating latency...");
            try {
                Thread.sleep(5000);  // Delay to simulate network latency
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Resuming normal operations.");
        };

        // Schedule the fault simulation every 30 seconds
        executor.scheduleAtFixedRate(introduceLatency, 0, 30, TimeUnit.SECONDS);
    }
}
