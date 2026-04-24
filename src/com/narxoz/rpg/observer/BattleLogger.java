package com.narxoz.rpg.observer;

public class BattleLogger implements GameObserver {
    @Override public void onEvent(GameEvent event) {
        System.out.println("LOG: " + event);
    }
}
