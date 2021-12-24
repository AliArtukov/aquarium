package io.aquarium;
// Created by: Ali Artukov
// Created time: 12/5/2021 12:12 PM
// Telegram: https://t.me/Ali_Artukov

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("We started filling the Doc with fish");
        int count = new Random().nextInt(10) + 5;
        System.out.println("We threw " + count + " fish into the Doc\n");

        for (int i = 0; i < count; i++) {
            newFishLife();
        }
    }

    public static void newFishLife() {
        new FishThread().start();
    }
}
