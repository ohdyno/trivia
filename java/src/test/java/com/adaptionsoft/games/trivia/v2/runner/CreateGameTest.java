package com.adaptionsoft.games.trivia.v2.runner;

import com.adaptionsoft.games.trivia.v2.run.CreateGame;
import com.adaptionsoft.games.trivia.v2.value.GameEvent;
import com.adaptionsoft.games.trivia.v2.value.Player;
import com.adaptionsoft.games.trivia.v2.run.RecordGameEvents;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class CreateGameTest {
    @InjectMocks
    private CreateGame subject;

    @Mock
    private RecordGameEvents recordGameEvents;

    @Test
    public void whenAGameIsCreated_announceThatANewPlayerHasJoinedTheGame() {
        subject.create();

        Mockito.verify(recordGameEvents).record(eq(GameEvent.NewPlayer), any(Player.class));
    }
}