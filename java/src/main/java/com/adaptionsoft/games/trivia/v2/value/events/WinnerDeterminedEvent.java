package com.adaptionsoft.games.trivia.v2.value.events;

import com.adaptionsoft.games.trivia.v2.value.Player;
import lombok.Value;

@Value
public class WinnerDeterminedEvent implements GameEvent {
    public WinnerDeterminedEvent(Player player) {
    }
}
