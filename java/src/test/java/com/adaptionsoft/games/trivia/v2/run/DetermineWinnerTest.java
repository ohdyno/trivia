package com.adaptionsoft.games.trivia.v2.run;

import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.Player;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DetermineWinnerTest {
    private DetermineWinner subject = new DetermineWinner();

    @Test
    public void aPlayerIsAWinnerOnlyIfHeHasSixCoins() {
        Player player = Player.builder()
                .coins(6).build();
        Game2 game = new Game2(player);

        Optional<Player> result = subject.doesExistIn(game);

        assertThat(result.isPresent(), equalTo(true));
        assertThat(result.get(), equalTo(player));
    }

    @Test
    public void otherwiseAPlayerIsNotAWinner() {
        Player player = Player.builder()
                .coins(1).build();
        Game2 game = new Game2(player);

        Optional<Player> result = subject.doesExistIn(game);

        assertThat(result.isPresent(), equalTo(false));
    }
}