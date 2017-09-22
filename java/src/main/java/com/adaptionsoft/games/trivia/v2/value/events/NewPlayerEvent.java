package com.adaptionsoft.games.trivia.v2.value.events;

import com.adaptionsoft.games.trivia.v2.value.Player;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class NewPlayerEvent implements GameEvent {
    private Player player;
}
