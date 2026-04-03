package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class HealCommand implements ActionCommand {
    private final ArenaFighter fighter;
    private final int amount;
    private int previousHp;

    public HealCommand(ArenaFighter fighter, int amount) {
        this.fighter = fighter;
        this.amount = amount;
    }

    @Override
    public void execute() {
        previousHp = fighter.getHp();
        fighter.heal(amount);
        System.out.println("Command: " + fighter.getName() + " heals for up to " + amount + " HP.");
    }

    @Override
    public void undo() {
        fighter.restoreHealth(previousHp);
    }

    @Override
    public String description() {
        return "HealCommand";
    }
}
