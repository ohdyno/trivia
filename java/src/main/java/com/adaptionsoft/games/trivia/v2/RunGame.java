package com.adaptionsoft.games.trivia.v2;

import com.adaptionsoft.games.trivia.v2.run.AdvanceGame;
import com.adaptionsoft.games.trivia.v2.run.CreateGame;
import com.adaptionsoft.games.trivia.v2.run.DetermineWinner;
import com.adaptionsoft.games.trivia.v2.run.RecordGameEvents;
import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.Player;
import com.adaptionsoft.games.trivia.v2.value.events.WinnerDeterminedEvent;

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
        recordGameEvents.record(new WinnerDeterminedEvent(winner.get()));
    }
}
