package com.adaptionsoft.games.trivia.v2.run;

import com.adaptionsoft.games.trivia.v2.run.advance.PlayTrivia;
import com.adaptionsoft.games.trivia.v2.run.advance.UpdatePlayer;
import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.Player;
import com.adaptionsoft.games.trivia.v2.value.events.DiceRollEvent;
import com.adaptionsoft.games.uglytrivia.Dice;

public class AdvanceGame {
    private Dice dice;
    private UpdatePlayer updatePlayer;
    private PlayTrivia playTrivia;
    private final RecordGameEvents recordGameEvents;

    public AdvanceGame(Dice dice, UpdatePlayer updatePlayer, PlayTrivia playTrivia , RecordGameEvents recordGameEvents) {
        this.dice = dice;
        this.updatePlayer = updatePlayer;
        this.playTrivia = playTrivia;
        this.recordGameEvents = recordGameEvents;
    }

    public void byOneTurn(Game2 game) {
        int roll = dice.roll();
        Player player = game.currentPlayer;
        recordGameEvents.record(new DiceRollEvent(roll));
        updatePlayer.basedOnRoll(roll, player);
        playTrivia.with(player);
    }
}
