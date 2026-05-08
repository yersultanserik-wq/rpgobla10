package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.List;

/**
 * GuildHall — concrete Mediator.
 *
 * Topic-based routing: when a member sends on topic T, every other registered
 * member that subscribes to T is notified. The sender is excluded.
 *
 * Adding a new routing rule means editing only GuildHall — not any colleague.
 */
public class GuildHall implements GuildMediator {
    private final String        hallName;
    private final List<GuildMember> members = new ArrayList<>();

    public GuildHall(String hallName) {
        this.hallName = hallName;
        System.out.println("[GuildHall] \"" + hallName + "\" opened.");
    }

    @Override
    public void register(GuildMember member) {
        members.add(member);
        System.out.println("[GuildHall] Registered: " + member);
    }

    @Override
    public void send(GuildMember sender, String topic, String message) {
        int delivered = 0;
        for (GuildMember m : members) {
            if (m == sender) continue;                              // never echo back
            if (m.getSubscribedTopics().contains(topic)) {
                m.onMessage(sender.getRole(), topic, message);
                delivered++;
            }
        }
        if (delivered == 0) {
            System.out.println("  [GuildHall] No subscribers for topic \"" + topic + "\" — message dropped.");
        }
    }

    public String getHallName() { return hallName; }
    public int getMemberCount() { return members.size(); }
}
