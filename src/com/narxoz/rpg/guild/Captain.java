package com.narxoz.rpg.guild;

/**
 * Captain — issues orders and listens to all guild channels.
 * Subscribes to: "supplies", "scouting", "healing", "orders"
 */
public class Captain extends GuildMember {
    private int ordersIssued;

    public Captain(String name, GuildMediator mediator) {
        super("Captain", name, mediator, "supplies", "scouting", "healing", "orders");
        this.ordersIssued = 0;
    }

    /** Issue a campaign order to all guild members. */
    public void issueOrder(String order) {
        ordersIssued++;
        send("orders", "ORDER #" + ordersIssued + ": " + order);
    }

    /** Broadcast campaign briefing. */
    public void briefCampaign(String questTitle) {
        send("orders", "Campaign briefing — we march on: \"" + questTitle + "\". Prepare accordingly.");
    }

    @Override
    public void onMessage(String fromRole, String topic, String message) {
        switch (topic) {
            case "supplies" -> System.out.println("    → [Captain " + getName() + "] Supply report from "
                               + fromRole + ": noted for campaign logistics.");
            case "scouting" -> {
                System.out.println("    → [Captain " + getName() + "] Intel from " + fromRole
                                   + ": adjusting strategy.");
                if (message.contains("THREAT")) {
                    // Captain responds to threats by issuing a new order
                    ordersIssued++;
                    System.out.println("    → [Captain " + getName() + "] COUNTER-ORDER #" + ordersIssued
                                       + ": all units — defensive formation!");
                }
            }
            case "healing" -> System.out.println("    → [Captain " + getName() + "] Medical report from "
                               + fromRole + ": assessing combat readiness.");
            case "orders"  -> System.out.println("    → [Captain " + getName() + "] Received order from "
                               + fromRole + ": acknowledged.");
        }
    }

    public int getOrdersIssued() { return ordersIssued; }
}
