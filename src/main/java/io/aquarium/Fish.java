package io.aquarium;
// Created by: Ali Artukov
// Created time: 12/5/2021 8:53 PM
// Telegram: https://t.me/Ali_Artukov

import java.time.LocalDateTime;
import java.util.Random;

public class Fish {

    private String name;

    private final boolean isMan = new Random().nextBoolean();

    private LocalDateTime deathDate = LocalDateTime.now().plusSeconds(new Random().nextInt(40) + 20);

    private int address = new Random().nextInt(100) + 1;

    public Fish() {
    }

    public Fish(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDeathDate() {
        return deathDate;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + name + '\'' +
                ", isMan=" + isMan +
                ", deathDate=" + deathDate +
                ", address=" + address +
                '}';
    }

}
