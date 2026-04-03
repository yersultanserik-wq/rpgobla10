package com.narxoz.rpg.decorator;

public abstract class ActionDecorator extends AttackAction {
    protected final AttackAction wrapped;

    protected ActionDecorator(AttackAction wrapped) {
        this.wrapped = wrapped;
    }
}
