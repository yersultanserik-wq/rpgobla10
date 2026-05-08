package com.narxoz.rpg.guild;

/**
 * Healer — manages medical supplies and responds to casualty reports.
 * Subscribes to: "healing", "scouting", "orders"
 */
public class Healer extends GuildMember {
    private int healingPotions;
    private int heroesHealed;

    public Healer(String name, GuildMediator mediator, int startingPotions) {
        super("Healer", name, mediator, "healing", "scouting", "orders");
        this.healingPotions = startingPotions;
        this.heroesHealed   = 0;
    }

    /** Broadcast current medical readiness. */
    public void reportReadiness() {
        send("healing", "Medical readiness: " + healingPotions + " potions ready. Standing by.");
    }

    /** Request urgent healing supplies. */
    public void callForCasualties(int count) {
        send("healing", count + " heroes need immediate healing — send reinforcements!");
    }

    @Override
    public void onMessage(String fromRole, String topic, String message) {
        switch (topic) {
            case "orders" ->  {
                System.out.println("    → [Healer " + getName() + "] Orders from " + fromRole
                                   + ": setting up field hospital.");
            }
            case "scouting" -> {
                // If scout reports a threat, healer pre-stages potions
                if (message.contains("THREAT")) {
                    int used = Math.min(5, healingPotions);
                    healingPotions -= used;
                    heroesHealed   += used;
                    System.out.println("    → [Healer " + getName() + "] Threat reported by " + fromRole
                                       + ": pre-staged " + used + " potions. Remaining: " + healingPotions);
                } else {
                    System.out.println("    → [Healer " + getName() + "] Scout report noted from " + fromRole + ".");
                }
            }
            case "healing" -> {
                System.out.println("    → [Healer " + getName() + "] Healing request from " + fromRole
                                   + ": \"" + message + "\"");
            }
        }
    }

    public int getHealingPotions() { return healingPotions; }
    public int getHeroesHealed()   { return heroesHealed; }
}
