package com.narxoz.rpg.guild;

/**
 * Mediator interface — the only channel through which guild members talk.
 * Colleagues never reference each other directly; they call send() and
 * the GuildHall decides who gets notified.
 */
public interface GuildMediator {
    /**
     * Register a guild member so the mediator knows about it.
     * Called once during setup for each colleague.
     */
    void register(GuildMember member);

    /**
     * Route a message from {@code sender} to all members subscribed to
     * {@code topic}, excluding the sender itself.
     *
     * @param sender  the colleague initiating the message
     * @param topic   routing key (e.g. "supplies", "scouting", "healing", "orders")
     * @param message the payload
     */
    void send(GuildMember sender, String topic, String message);
}
