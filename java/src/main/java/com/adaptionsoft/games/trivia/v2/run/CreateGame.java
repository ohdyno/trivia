package com.adaptionsoft.games.trivia.v2.run;

import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.GameEvent;
import com.adaptionsoft.games.trivia.v2.value.Player;

public class CreateGame {
    private final RecordGameEvents recordGameEvents;

    public CreateGame(RecordGameEvents recordGameEvents) {
        this.recordGameEvents = recordGameEvents;
    }

    public Game2 create() {
        Player player = new Player("Chet");
        recordGameEvents.record(GameEvent.NewPlayer, player);
        return new Game2(player);
    }
}
