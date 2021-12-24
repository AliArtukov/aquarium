package io.aquarium;
// Created by: Ali Artukov
// Created time: 12/6/2021 10:52 PM
// Telegram: https://t.me/Ali_Artukov

import java.time.LocalDateTime;
import java.util.*;

public class FishService {

    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static Set<Fish> aquarium = new HashSet<>();

    public static void fishLife() {
        Fish fish = fishBirth();
        while (measuringLife(fish)) {
            fish = swimming(fish);
            findAMate(fish);

            // The first way to stop the fish
//            try {
//                Thread.sleep(3 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            // The second way to stop the fish
            LocalDateTime localDateTime = LocalDateTime.now();
            while (LocalDateTime.now().isBefore(localDateTime.plusSeconds(3))) {
                // The fish rests for a certain amount of time after each swim
            }
        }
    }

    public static Fish fishBirth() {
        Fish fish = new Fish(findAName());
        addToAquarium(fish);
        System.out.println(ANSI_GREEN_BACKGROUND + "Birth -> " + "\tName: " + fish.getName() + ", Gender: " + (fish.isMan() ? "Man" : "Woman") + ANSI_RESET);
        return fish;
    }

    public static String findAName() {
        return "F" + Thread.currentThread().getName().replace("Thread", "");
    }

    public static Fish swimming(Fish fish) {
        return swimSide(fish);
    }

    public static boolean measuringLife(Fish fish) {
        if (LocalDateTime.now().isAfter(fish.getDeathDate())) {
            removeFromAquarium(fish);
            int[] ints = new int[]{3, 4};
            System.out.println(ANSI_RED_BACKGROUND + "Died -> " + "\tName: " + fish.getName() + ", Gender: " + (fish.isMan() ? "Man" : "Woman") + ANSI_RESET);
            System.out.println(ANSI_YELLOW_BACKGROUND + "Fish in the Doc -> \t" + aquarium.size() + ANSI_RESET);
            return false;
        } else {
            return true;
        }
    }

    private static synchronized void removeFromAquarium(Fish fish) {
        aquarium.remove(fish);
    }

    public static synchronized void addToAquarium(Fish fish) {
        aquarium.add(fish);
        System.out.println(ANSI_YELLOW_BACKGROUND + "Fish in the Doc -> \t" + aquarium.size() + ANSI_RESET);
    }

    public static Fish swimSide(Fish fish) {
        int oldAddress = fish.getAddress();

        while (oldAddress == fish.getAddress()) {
            int side = new Random().nextInt(4) + 1;
            if ((side == 1) && ((fish.getAddress() - 10) > 0)) {
                fish.setAddress(fish.getAddress() - 10);
                System.out.println("Swam -> " + oldAddress + ":" + fish.getAddress() + "\t Name: " + fish.getName() + ", Gender: " + (fish.isMan() ? "Man" : "Woman"));
            } else if ((side == 2) && ((fish.getAddress() + 1) % 10 != 1)) {
                fish.setAddress(fish.getAddress() + 1);
                System.out.println("Swam -> " + oldAddress + ":" + fish.getAddress() + "\t Name: " + fish.getName() + ", Gender: " + (fish.isMan() ? "Man" : "Woman"));
            } else if ((side == 3) && ((fish.getAddress() + 10) <= 100)) {
                fish.setAddress(fish.getAddress() + 10);
                System.out.println("Swam -> " + oldAddress + ":" + fish.getAddress() + "\t Name: " + fish.getName() + ", Gender: " + (fish.isMan() ? "Man" : "Woman"));
            } else if ((side == 4) && ((fish.getAddress() - 1) % 10 != 0)) {
                fish.setAddress(fish.getAddress() - 1);
                System.out.println("Swam -> " + oldAddress + ":" + fish.getAddress() + "\t Name: " + fish.getName() + ", Gender: " + (fish.isMan() ? "Man" : "Woman"));
            }
        }
        changeFishAddress(fish);
        return fish;
    }

    public static synchronized void findAMate(Fish fish) {
        for (Fish oneFish : aquarium) {
            if (!(fish.getName().equals(oneFish.getName())) &&
                    (fish.getAddress() == oneFish.getAddress()) &&
                    /*(fish.isMan() != oneFish.isMan())*/
                    (fish.isMan() == true && oneFish.isMan() == false)) {
                System.out.println("The fish found a mate -> " + "\tName: " + fish.getName() + " : " + oneFish.getName() +
                                ", Gender: " + (fish.isMan() ? "Man" : "Woman") + " : " + (oneFish.isMan() ? "Man" : "Woman") +
                                ", Address: " + fish.getAddress() + " : " + oneFish.getAddress());
//                new FishThread().start();
                Main.newFishLife();
                break;
            }
        }
    }

    public static synchronized void changeFishAddress(Fish fish) {
        for (Fish fish1 : aquarium) {
            if (fish1.getName().equals(fish.getName())) {
                fish1.setAddress(fish.getAddress());
                break;
            }
        }
    }

}
