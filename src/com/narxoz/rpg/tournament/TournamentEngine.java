package com.narxoz.rpg.tournament;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;
import com.narxoz.rpg.arena.TournamentResult;
import com.narxoz.rpg.chain.ArmorHandler;
import com.narxoz.rpg.chain.BlockHandler;
import com.narxoz.rpg.chain.DefenseHandler;
import com.narxoz.rpg.chain.DodgeHandler;
import com.narxoz.rpg.chain.HpHandler;
import com.narxoz.rpg.command.ActionQueue;
import com.narxoz.rpg.command.AttackCommand;
import com.narxoz.rpg.command.DefendCommand;
import com.narxoz.rpg.command.HealCommand;

public class TournamentEngine {
    private final DefenseHandler defenseChain;

    public TournamentEngine() {
        defenseChain = new DodgeHandler();
        defenseChain
                .setNext(new BlockHandler())
                .setNext(new ArmorHandler())
                .setNext(new HpHandler());
    }

    public TournamentResult runBattle(ArenaFighter fighter, ArenaOpponent opponent) {
        System.out.println("\n=== HW6 GRAND ARENA TOURNAMENT ===");
        System.out.println(fighter + " vs " + opponent);

        int round = 1;
        while (fighter.isAlive() && opponent.isAlive() && round <= 10) {
            System.out.println("\n-- Arena Round " + round + " --");
            ActionQueue queue = new ActionQueue();

            if (round == 1) {
                queue.enqueue(new DefendCommand(fighter));
                queue.enqueue(new HealCommand(fighter, 8));
                queue.undoLast();
                queue.enqueue(new AttackCommand(fighter, opponent));
            } else if (fighter.getHp() <= fighter.getMaxHp() / 2) {
                queue.enqueue(new HealCommand(fighter, 10));
                queue.enqueue(new DefendCommand(fighter));
            } else {
                queue.enqueue(new AttackCommand(fighter, opponent));
                queue.enqueue(new DefendCommand(fighter));
            }

            queue.executeAll();

            if (opponent.isAlive()) {
                int incoming = opponent.attack();
                System.out.println(opponent.getTitle() + " counterattacks for " + incoming + " damage.");
                defenseChain.handle(incoming, fighter);
            }

            System.out.println("Status: " + fighter.getName() + " HP=" + fighter.getHp()
                    + " | " + opponent.getTitle() + " HP=" + opponent.getHp());
            round++;
        }

        String winner = fighter.isAlive() ? fighter.getName() : opponent.getTitle();
        TournamentResult result = new TournamentResult(fighter.getName(), opponent.getTitle(), winner, round - 1);
        System.out.println(result.summary());
        return result;
    }
}
