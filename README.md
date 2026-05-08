# 🏰 Chronomancer's Vault — RPG Design Patterns (HW1–HW9)

A progressive RPG engine built in pure Java demonstrating **9 homeworks worth of GoF design patterns**.  
Each homework adds a new pattern pair on top of the existing codebase — no rewrites, only extensions.

---

## 🚀 Quick Start

**Bash / macOS / Linux:**
```bash
javac -d out $(find src -name "*.java")
java -cp out com.narxoz.rpg.Main
```

**Windows PowerShell:**
```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
java -cp out com.narxoz.rpg.Main
```

> Requires **Java 17+**. No external dependencies.

---

## 📚 Pattern Overview

| HW | Patterns | Theme |
|----|----------|-------|
| HW1 | Factory Method + Abstract Factory | Hero & equipment creation |
| HW2 | Builder + Prototype | Enemy construction & cloning |
| HW3 | Singleton + Adapter | Battle engine & combatant unification |
| HW5 | Decorator + Facade | Attack upgrades & dungeon runs |
| HW6 | Command + Chain of Responsibility | Action queue & defense chain |
| HW7 | Strategy + Observer | Combat AI & event system |
| HW8 | State + Template Method | Hero states & Haunted Tower floors |
| **HW9** | **Visitor + Memento** | **Chronomancer's Vault** |

---

## 🔮 HW9 — Visitor + Memento

### The Scenario
A hero enters the **Chronomancer's Vault** — a time-warped dungeon full of magical artifacts and deadly traps.  
- **Visitor** appraises a mixed inventory of artifacts without a single `instanceof`.  
- **Memento** saves checkpoints before risky rooms so the Chronomancer can rewind failed attempts.

---

### 🗺️ Visitor Pattern

**Goal:** run multiple reports over a heterogeneous artifact inventory without touching artifact classes.

**Artifacts** (`com.narxoz.rpg.artifact`):

| Class | Key Fields |
|-------|-----------|
| `Weapon` | `attackBonus`, `damageType` |
| `Potion` | `healAmount`, `curesCurse` |
| `Scroll` | `spellName`, `manaCost` |
| `Ring` | `manaBonus`, `enchantment` |
| `Armor` | `defenseBonus`, `material` |

Each artifact implements:
```java
public void accept(ArtifactVisitor visitor) {
    visitor.visit(this); // double-dispatch
}
```

**Concrete Visitors** (new reports = new classes, zero artifact edits):

| Visitor | What it does |
|---------|-------------|
| `GoldAppraisalVisitor` | Calculates gold value per item type with rarity multipliers |
| `CurseDetectionVisitor` | Flags shadow/void weapons, time-warp rings, soul-crystal armor |
| `EncumbranceVisitor` | Calculates carry weight in kg for each item category |

**Usage:**
```java
Inventory inv = new Inventory();
inv.add(new Weapon("Chrono-Blade", 200, 8, "arcane"));
inv.add(new Ring("Ring of Time-Warp", 350, 15, "time-warp"));

GoldAppraisalVisitor appraiser = new GoldAppraisalVisitor();
inv.accept(appraiser);
System.out.println(appraiser.summary()); // → "total 820g"
```

---

### ⏪ Memento Pattern

**Goal:** snapshot a hero's full mutable state (HP, mana, gold, inventory) so the vault can rewind after a trap.

**Roles:**

| Role | Class | Responsibility |
|------|-------|---------------|
| Originator | `VaultHero` | Creates and restores mementos |
| Memento | `HeroMemento` | Immutable snapshot; fields are package-private |
| Caretaker | `Caretaker` | Manages a stack of checkpoints (max depth = 5) |

**Usage:**
```java
VaultHero hero = new VaultHero("Aibek", 100, 80, 500);
Caretaker caretaker = new Caretaker();

caretaker.push(hero.saveMemento("Before Trap Room"));

hero.takeDamage(50);
hero.spendGold(200);

caretaker.pop().ifPresent(hero::restoreMemento); // ⏪ rewind
```

---

## 📁 Package Structure

```
src/com/narxoz/rpg/
├── artifact/     ← HW9 Visitor (ArtifactVisitor, 5 artifact types, 3 visitors, Inventory)
├── memento/      ← HW9 Memento (Caretaker)
├── vault/        ← HW9 Engine (ChronomancerEngine, VaultRunResult)
├── combatant/    ← VaultHero + HeroMemento (Originator + Memento token)
├── floor/        ← HW8 Template Method floors
├── state/        ← HW8 State machine
├── observer/     ← HW7 Observer / event bus
├── strategy/     ← HW7 Combat strategy
├── command/      ← HW6 Command + ActionQueue
├── chain/        ← HW6 Chain of Responsibility
├── decorator/    ← HW5 Attack decorators
├── facade/       ← HW5 DungeonFacade
├── adapter/      ← HW3 Combatant adapters
├── battle/       ← HW3 Singleton BattleEngine
├── enemy/        ← HW2 Builder + Prototype
├── hero/         ← HW1 Factory Method + Abstract Factory
└── Main.java     ← Runs all HW demos in sequence
```

---

## 🎮 HW9 Demo Output (excerpt)

```
=== HW9 VISITOR + MEMENTO DEMO ===

╔══════════════════════════════════════╗
║    CHRONOMANCER'S VAULT — ENTER      ║
╚══════════════════════════════════════╝

  >> Running Gold Appraisal on vault inventory...
  [Appraise] Weapon  "Ancient Chrono-Blade" → 320g
  [Appraise] Ring    "Ring of Time-Warp"    → 650g
  >> Gold Appraisal: 5 items → total 1370g

  >> Running Curse Detection...
  [Curse] ⚠  Ring  "Ring of Time-Warp"  — CURSED! (enchantment: time-warp)
  [Curse] ⚠  Armor "Void-Crystal Plate" — CURSED! (material: soul-crystal)

--- Room 3: Trap Room ---
  [Caretaker] Saved checkpoint: "Before Trap Room"
  ⚡ TRAP TRIGGERED!
  ⏪ CHRONOMANCER'S REWIND activated!
  [Rewind] Aibek restored to checkpoint "Before Trap Room"
```

---

## ✅ Anti-Pattern Checklist

- ❌ No `instanceof` in visitor logic — pure double-dispatch
- ❌ No `Caretaker` reading memento internals — fields are package-private
- ❌ No artifact class modified to add a new visitor
- ✅ Open/Closed: new reports = new `ArtifactVisitor` implementation only
- ✅ Memento encapsulation: `HeroMemento` constructor is package-private

---

## 👤 Author

**Yersultan Serik** — Narxoz University, Software Engineering  
Homework series: RPG Design Patterns (HW1–HW9)
