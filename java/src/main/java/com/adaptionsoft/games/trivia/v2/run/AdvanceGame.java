package com.adaptionsoft.games.trivia.v2.run;

import com.adaptionsoft.games.trivia.v2.run.advance.PlayTrivia;
import com.adaptionsoft.games.trivia.v2.run.advance.UpdatePlayer;
import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.Player;
import com.adaptionsoft.games.uglytrivia.Dice;

public class AdvanceGame {
    private Dice dice;
    private UpdatePlayer updatePlayer;
    private PlayTrivia playTrivia;

    public AdvanceGame(Dice dice, UpdatePlayer updatePlayer, PlayTrivia playTrivia) {
        this.dice = dice;
        this.updatePlayer = updatePlayer;
        this.playTrivia = playTrivia;
    }

    public void byOneTurn(Game2 game) {
        int roll = dice.roll();
        Player player = game.currentPlayer;
        updatePlayer.basedOnRoll(roll, player);
        playTrivia.with(player);
    }
}
