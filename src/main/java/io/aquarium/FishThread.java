package io.aquarium;
// Created by: Ali Artukov
// Created time: 12/7/2021 12:19 AM
// Telegram: https://t.me/Ali_Artukov

public class FishThread extends Thread {
    @Override
    public void run() {
        FishService.fishLife();
    }

}
