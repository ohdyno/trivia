package com.adaptionsoft.games.trivia.v2.runner;

import com.adaptionsoft.games.trivia.v2.*;
import com.adaptionsoft.games.trivia.v2.run.*;
import com.adaptionsoft.games.trivia.v2.value.Game2;
import com.adaptionsoft.games.trivia.v2.value.GameEvent;
import com.adaptionsoft.games.trivia.v2.value.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RunGameTest {
    @InjectMocks
    private RunGame subject;

    @Mock
    private CreateGame createGame;
    @Mock
    private RecordGameEvents recordGameEvents;
    @Mock
    private AdvanceGame advanceGame;
    @Mock
    private DetermineWinner determineWinner;

    @Test
    public void oneTurnGameWithWinner() {
        Player player = new Player("player name");
        Game2 game = new Game2(player);
        when(createGame.create()).thenReturn(game);
        when(determineWinner.doesExistIn(game)).thenReturn(Optional.of(player));

        subject.run();

        verify(createGame).create();
        verify(advanceGame).byOneTurn(game);
        verify(determineWinner, atLeastOnce()).doesExistIn(game);
        GameEvent winnerDetermined = GameEvent.WinnerDetermined;
        verify(recordGameEvents).record(winnerDetermined, player);
    }

}