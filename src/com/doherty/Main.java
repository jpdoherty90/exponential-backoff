package com.doherty;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        RandomFailer failer = new RandomFailer();
        int randomInt = getIntWithExponentialBackoff(failer, 1, 4, 0.5);
        System.out.println(randomInt);
    }

    private static int getIntWithExponentialBackoff(RandomFailer failer, int backoffPeriod, int maxBackoff, double jitter) throws InterruptedException {
        try {
            System.out.println("Trying...");
            return failer.getRandomInt();
        } catch (RuntimeException e) {
            System.out.println("Failed");
            if (backoffPeriod > maxBackoff) {
                System.out.println("Too many attempts, throwing error");
                throw e;
            }
            double backoffWithJitter = backoffPeriod - jitter + (2 * jitter * new Random().nextDouble());
            System.out.println("Sleeping " + backoffWithJitter + " seconds");
            Thread.sleep((long) (backoffWithJitter * 1000));
            return getIntWithExponentialBackoff(failer, backoffPeriod * 2, maxBackoff, jitter);
        }
    }


}


