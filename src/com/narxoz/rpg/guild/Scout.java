package com.narxoz.rpg.guild;

/**
 * Scout — gathers intelligence and responds to scouting orders.
 * Subscribes to: "scouting", "orders"
 */
public class Scout extends GuildMember {
    private int regionsExplored;

    public Scout(String name, GuildMediator mediator) {
        super("Scout", name, mediator, "scouting", "orders");
        this.regionsExplored = 0;
    }

    /** Share a scouting report with the guild. */
    public void reportIntelligence(String region, String finding) {
        regionsExplored++;
        send("scouting", "Region [" + region + "]: " + finding);
    }

    /** Warn the guild of a detected threat. */
    public void warnThreat(String threat) {
        send("scouting", "THREAT DETECTED — " + threat);
    }

    @Override
    public void onMessage(String fromRole, String topic, String message) {
        if (topic.equals("orders")) {
            System.out.println("    → [Scout " + getName() + "] Orders from " + fromRole
                               + ": deploying to scout the advance route.");
            regionsExplored++;
        } else if (topic.equals("scouting")) {
            System.out.println("    → [Scout " + getName() + "] Intel from " + fromRole
                               + ": logging report — \"" + message + "\"");
        }
    }

    public int getRegionsExplored() { return regionsExplored; }
}
