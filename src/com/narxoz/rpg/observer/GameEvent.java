package com.narxoz.rpg.observer;

public class GameEvent {
    private final GameEventType type;
    private final String source;
    private final String target;
    private final int value;
    private final String message;

    public GameEvent(GameEventType type, String source, String target, int value, String message) {
        this.type = type;
        this.source = source;
        this.target = target;
        this.value = value;
        this.message = message;
    }

    public GameEventType getType() { return type; }
    public String getSource() { return source; }
    public String getTarget() { return target; }
    public int getValue() { return value; }
    public String getMessage() { return message; }

    @Override public String toString() {
        return "[" + type + "] " + source + " -> " + target + " (" + value + "): " + message;
    }
}
