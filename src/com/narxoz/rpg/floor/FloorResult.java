package com.narxoz.rpg.floor;

public class FloorResult {
    private final boolean cleared;
    private final String message;

    public FloorResult(boolean cleared, String message) {
        this.cleared = cleared;
        this.message = message;
    }

    public boolean cleared() { return cleared; }
    public String message() { return message; }
}
