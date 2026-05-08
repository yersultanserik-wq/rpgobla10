package com.narxoz.rpg.guild;

/**
 * Quartermaster — manages supplies and reacts to supply requests.
 * Subscribes to: "supplies", "orders"
 */
public class Quartermaster extends GuildMember {
    private int suppliesRemaining;

    public Quartermaster(String name, GuildMediator mediator, int initialSupplies) {
        super("Quartermaster", name, mediator, "supplies", "orders");
        this.suppliesRemaining = initialSupplies;
    }

    /** Announce supply levels to the guild. */
    public void reportSupplies() {
        send("supplies", "Current supplies: " + suppliesRemaining + " units available.");
    }

    /** Request more supplies from the guild. */
    public void requestResupply(int amount) {
        send("supplies", "URGENT: Need " + amount + " units resupplied before campaign!");
    }

    @Override
    public void onMessage(String fromRole, String topic, String message) {
        if (topic.equals("orders")) {
            System.out.println("    → [QM " + getName() + "] Orders received from " + fromRole
                               + ": preparing equipment packs.");
            suppliesRemaining = Math.max(0, suppliesRemaining - 10);
        } else if (topic.equals("supplies")) {
            System.out.println("    → [QM " + getName() + "] Supply update from " + fromRole
                               + ": acknowledged — \"" + message + "\"");
        }
    }

    public int getSuppliesRemaining() { return suppliesRemaining; }
}
