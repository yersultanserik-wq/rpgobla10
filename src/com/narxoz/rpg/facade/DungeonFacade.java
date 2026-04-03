package com.narxoz.rpg.facade;

import com.narxoz.rpg.adapter.Combatant;
import com.narxoz.rpg.decorator.AttackAction;

public class DungeonFacade {
    private final PreparationService preparationService;
    private final BattleService battleService;
    private final RewardService rewardService;

    public DungeonFacade() {
        this.preparationService = new PreparationService();
        this.battleService = new BattleService();
        this.rewardService = new RewardService();
    }

    public AdventureResult runAdventure(String dungeonName, Combatant hero, Combatant boss, AttackAction action) {
        PreparationSnapshot preparation = preparationService.prepare(dungeonName, hero, action);
        BattleSnapshot battle = battleService.fight(hero, boss, action);
        RewardBundle rewards = rewardService.grantRewards(boss.name(), battle.heroWon(), battle.rounds());

        return new AdventureResult(
                preparation.dungeonName(),
                preparation.heroName(),
                boss.name(),
                battle.heroWon(),
                battle.rounds(),
                battle.heroHp(),
                battle.bossHp(),
                rewards
        );
    }
}
