package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract colleague — every guild officer extends this.
 *
 * Rules:
 *  - Only stores a reference to the {@link GuildMediator}, never to peers.
 *  - Sends messages exclusively through the mediator.
 *  - Reacts to incoming messages via {@link #onMessage(String, String, String)}.
 */
public abstract class GuildMember {
    private final String        role;   // "Quartermaster", "Scout", "Healer", "Captain"
    private final String        name;
    protected final GuildMediator mediator;
    private final List<String>  subscribedTopics;

    protected GuildMember(String role, String name, GuildMediator mediator, String... topics) {
        this.role             = role;
        this.name             = name;
        this.mediator         = mediator;
        this.subscribedTopics = new ArrayList<>(Arrays.asList(topics));
        mediator.register(this);
    }

    public String getRole() { return role; }
    public String getName() { return name; }

    /** Returns all topics this member listens to. */
    public List<String> getSubscribedTopics() { return subscribedTopics; }

    /**
     * Send a message through the mediator — the ONLY way to communicate.
     * Colleagues never call peer methods directly.
     */
    protected void send(String topic, String message) {
        System.out.println("  [" + role + " " + name + " → " + topic.toUpperCase() + "] " + message);
        mediator.send(this, topic, message);
    }

    /**
     * Invoked by the mediator when a message arrives on a subscribed topic.
     *
     * @param fromRole sender's role label
     * @param topic    the topic this arrived on
     * @param message  the payload
     */
    public abstract void onMessage(String fromRole, String topic, String message);

    @Override
    public String toString() { return role + " " + name; }
}
