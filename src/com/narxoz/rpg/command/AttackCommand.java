package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;

public class AttackCommand implements ActionCommand {
    private final ArenaFighter fighter;
    private final ArenaOpponent opponent;
    private int previousOpponentHp;

    public AttackCommand(ArenaFighter fighter, ArenaOpponent opponent) {
        this.fighter = fighter;
        this.opponent = opponent;
    }

    @Override
    public void execute() {
        previousOpponentHp = opponent.getHp();
        int damage = fighter.attack();
        opponent.takeDamage(damage);
        System.out.println("Command: " + fighter.getName() + " attacks " + opponent.getTitle()
                + " for " + damage + " damage.");
    }

    @Override
    public void undo() {
        opponent.restoreHp(previousOpponentHp);
    }

    @Override
    public String description() {
        return "AttackCommand";
    }
}
