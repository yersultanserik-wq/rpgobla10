package com.narxoz.rpg.command;

import java.util.ArrayList;
import java.util.List;

public class ActionQueue {
    private final List<ActionCommand> queued = new ArrayList<>();

    public void enqueue(ActionCommand command) {
        queued.add(command);
        System.out.println("Queued: " + command.description());
    }

    public void undoLast() {
        if (queued.isEmpty()) {
            System.out.println("ActionQueue: nothing to undo.");
            return;
        }
        ActionCommand removed = queued.remove(queued.size() - 1);
        System.out.println("Undo before execution: removed " + removed.description());
    }

    public void executeAll() {
        System.out.println("ActionQueue: executing " + queued.size() + " command(s).");
        for (ActionCommand command : queued) {
            command.execute();
        }
        queued.clear();
    }

    public boolean isEmpty() {
        return queued.isEmpty();
    }
}
