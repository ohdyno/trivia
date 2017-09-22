package com.adaptionsoft.games.trivia.v2.run;

import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.Player;

import java.util.Optional;

public class DetermineWinner {
    public Optional<Player> doesExistIn(Game2 game) {
        if (game.currentPlayer.getCoins() == 6) {
            return Optional.of(game.currentPlayer);
        }

        return Optional.empty();
    }
}
