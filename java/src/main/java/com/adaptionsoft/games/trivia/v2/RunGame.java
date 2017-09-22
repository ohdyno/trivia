package com.adaptionsoft.games.trivia.v2;

import com.adaptionsoft.games.trivia.v2.run.*;
import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.GameEvent;
import com.adaptionsoft.games.trivia.v2.value.Player;

import java.util.Optional;

public class RunGame {

    private CreateGame createGame;
    private RecordGameEvents recordGameEvents;
    private AdvanceGame advanceGame;
    private DetermineWinner determineWinner;

    public RunGame(CreateGame createGame, RecordGameEvents recordGameEvents, AdvanceGame advanceGame, DetermineWinner determineWinner) {
        this.createGame = createGame;
        this.recordGameEvents = recordGameEvents;
        this.advanceGame = advanceGame;
        this.determineWinner = determineWinner;
    }

    public void run() {
        Game2 game = createGame.create();
        Optional<Player> winner;
        do {
            advanceGame.byOneTurn(game);
            winner = determineWinner.doesExistIn(game);
        } while (!winner.isPresent());
        recordGameEvents.record(GameEvent.WinnerDetermined, winner.get());
    }
}
