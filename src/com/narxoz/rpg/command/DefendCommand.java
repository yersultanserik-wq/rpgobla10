package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class DefendCommand implements ActionCommand {
    private final ArenaFighter fighter;

    public DefendCommand(ArenaFighter fighter) {
        this.fighter = fighter;
    }

    @Override
    public void execute() {
        fighter.prepareDefense();
        System.out.println("Command: " + fighter.getName() + " prepares a defense stance.");
    }

    @Override
    public void undo() {
        fighter.clearPreparedDefense();
    }

    @Override
    public String description() {
        return "DefendCommand";
    }
}
