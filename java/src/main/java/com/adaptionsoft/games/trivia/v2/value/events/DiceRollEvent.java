package com.adaptionsoft.games.trivia.v2.value.events;

import lombok.Value;

@Value
public class DiceRollEvent implements GameEvent {
    private final int roll;

    public DiceRollEvent(int roll) {
        this.roll = roll;
    }
}
