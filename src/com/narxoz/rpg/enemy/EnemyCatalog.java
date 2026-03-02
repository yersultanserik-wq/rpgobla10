package com.narxoz.rpg.enemy;

import java.util.HashMap;
import java.util.Map;

public class EnemyCatalog {
    private final Map<String, Enemy> prototypes = new HashMap<>();

    public void register(String key, Enemy prototype) {
        prototypes.put(key, prototype);
    }

    public Enemy createFromPrototype(String key) {
        Enemy p = prototypes.get(key);
        if (p == null) throw new IllegalArgumentException("No prototype: " + key);
        return p.clone();
    }
}