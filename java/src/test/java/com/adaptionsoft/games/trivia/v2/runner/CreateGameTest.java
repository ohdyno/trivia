package com.adaptionsoft.games.trivia.v2.runner;

import com.adaptionsoft.games.trivia.v2.run.CreateGame;
import com.adaptionsoft.games.trivia.v2.run.RecordGameEvents;
import com.adaptionsoft.games.trivia.v2.value.events.NewPlayerEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CreateGameTest {
    @InjectMocks
    private CreateGame subject;

    @Mock
    private RecordGameEvents recordGameEvents;

    @Test
    public void whenAGameIsCreated_announceThatANewPlayerHasJoinedTheGame() {
        subject.create();

        Mockito.verify(recordGameEvents).record(any(NewPlayerEvent.class));
    }
}