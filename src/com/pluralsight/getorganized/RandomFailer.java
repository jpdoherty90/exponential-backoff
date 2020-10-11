package com.pluralsight.getorganized;

import java.util.Random;

public class RandomFailer {

    int getRandomInt() {
        Random rand = new Random();
        if (rand.nextInt(2) == 1) {
            return rand.nextInt(100);
        } else {
            throw new RuntimeException();
        }
    }

}
