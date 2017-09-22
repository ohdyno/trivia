package com.adaptionsoft.games.trivia.v2.value.events;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class DiceRollEvent implements GameEvent {
    private int roll;
}
