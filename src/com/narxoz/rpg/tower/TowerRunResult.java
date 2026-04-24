package com.narxoz.rpg.tower;

public class TowerRunResult {
    private final boolean completed;
    private final int floorsCleared;
    private final String summary;

    public TowerRunResult(boolean completed, int floorsCleared, String summary) {
        this.completed = completed;
        this.floorsCleared = floorsCleared;
        this.summary = summary;
    }

    public boolean completed() { return completed; }
    public int floorsCleared() { return floorsCleared; }
    public String summary() { return summary; }
}
