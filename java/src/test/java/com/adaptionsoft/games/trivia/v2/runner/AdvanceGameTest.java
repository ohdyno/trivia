package com.adaptionsoft.games.trivia.v2.runner;

import com.adaptionsoft.games.trivia.v2.run.*;
import com.adaptionsoft.games.trivia.v2.run.advance.PlayTrivia;
import com.adaptionsoft.games.trivia.v2.run.advance.UpdatePlayer;
import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.Player;
import com.adaptionsoft.games.trivia.v2.value.events.DiceRollEvent;
import com.adaptionsoft.games.uglytrivia.Dice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdvanceGameTest {
    @InjectMocks
    private AdvanceGame subject;

    @Mock
    private Dice dice;
    @Mock
    private UpdatePlayer updatePlayer;
    @Mock
    private PlayTrivia playTrivia;
    @Mock
    private RecordGameEvents recordGameEvents;

    @Test
    public void whenItAdvancesTheGameByOneTurn_itShouldRollTheDiceForTheCurrentPlayer_UpdatePlayerBasedOnTheRollResult_AskTheQuestion() {
        Player player = Player.builder().build();
        Game2 game = new Game2(player);
        int roll = 4;
        when(dice.roll()).thenReturn(roll);

        subject.byOneTurn(game);

        verify(dice).roll();
        verify(updatePlayer).basedOnRoll(roll, player);
        verify(playTrivia).with(player);
        verify(recordGameEvents).record(new DiceRollEvent(roll));
    }
}