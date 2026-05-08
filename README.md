# \# 🏰 Adventurers' Guild — RPG Design Patterns (HW10)

# 

# Java RPG project demonstrating the implementation of \*\*GoF Design Patterns\*\* in a fantasy-themed game engine.

# 

# This homework focuses on:

# 

# \* \*\*Iterator Pattern\*\*

# \* \*\*Mediator Pattern\*\*

# 

# The project simulates an \*\*Adventurers' Guild War Council\*\*, where quests are traversed using custom iterators and guild members communicate through a mediator.

# 

# \---

# 

# \# 🚀 Features

# 

# \## Iterator Pattern

# 

# Custom iterators for traversing quests in different ways:

# 

# \* Ordered traversal

# \* Reverse traversal

# \* Priority-based traversal

# 

# The internal collection remains fully encapsulated.

# 

# \## Mediator Pattern

# 

# Guild officers communicate through a central `GuildHall` mediator.

# 

# Characters never reference each other directly:

# 

# \* Captain

# \* Scout

# \* Quartermaster

# \* Healer

# 

# This reduces coupling and makes the system easier to extend.

# 

# \---

# 

# \# 📦 Technologies

# 

# \* Java 17+

# \* OOP

# \* GoF Design Patterns

# \* No external libraries

# 

# \---

# 

# \# 📁 Project Structure

# 

# ```text

# src/com/narxoz/rpg/

# │

# ├── quest/        -> Iterator pattern

# ├── guild/        -> Mediator pattern

# ├── council/      -> Demo engine

# └── Main.java

# ```

# 

# \---

# 

# \# ⚔️ Iterator Example

# 

# ```java

# QuestIterator iterator = log.priorityIterator(QuestPriority.HIGH);

# 

# while (iterator.hasNext()) {

# &#x20;   System.out.println(iterator.next());

# }

# ```

# 

# \---

# 

# \# 🏛️ Mediator Example

# 

# ```java

# Scout scout = new Scout("Yerasyl", hall);

# 

# scout.warnThreat("Troll patrol on the eastern road!");

# ```

# 

# Output is routed through the `GuildHall` mediator.

# 

# \---

# 

# \# ▶️ Run Project

# 

# \## Windows PowerShell

# 

# ```powershell

# javac -d out (Get-ChildItem -Recurse -Filter \*.java src | ForEach-Object { $\_.FullName })

# 

# java -cp out com.narxoz.rpg.Main

# ```

# 

# \---

# 

# \# 🎮 Demo Includes

# 

# \* Quest traversal system

# \* War Council simulation

# \* Topic-based communication

# \* Priority filtering

# \* Dynamic message routing

# 

# \---

# 

# \# ✅ Design Principles

# 

# \* Encapsulation

# \* Low coupling

# \* Open/Closed Principle

# \* Single Responsibility Principle

# 

# \---

# 

# \# 👤 Author

# 

# \*\*Yersultan Serik\*\*

# Narxoz University — Software Engineering



