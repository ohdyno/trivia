package com.adaptionsoft.games.trivia.v2.run;

import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.Player;
import com.adaptionsoft.games.trivia.v2.value.events.NewPlayerEvent;

public class CreateGame {
    private final RecordGameEvents recordGameEvents;

    public CreateGame(RecordGameEvents recordGameEvents) {
        this.recordGameEvents = recordGameEvents;
    }

    public Game2 create() {
        Player player = Player.builder().build();
        recordGameEvents.record(new NewPlayerEvent(player));
        return new Game2(player);
    }
}
