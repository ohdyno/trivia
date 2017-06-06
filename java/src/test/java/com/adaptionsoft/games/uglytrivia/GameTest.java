package com.adaptionsoft.games.uglytrivia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

  private Game game;

  @BeforeEach
  void setUp() throws Exception {
    game = new Game();
  }

  @Test
  void add_alwaysReturnTrue() {
    assertTrue(game.add("foo"));
    assertTrue(game.add("foo"));
    assertTrue(game.add("bar"));
  }

  @Test
  void init_creates50PopQuestions() {
    assertEquals(50, game.popQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Pop Question " + i, game.popQuestions.get(i));
    }
  }

  @Test
  void init_creates50ScienceQuestions() {
    assertEquals(50, game.scienceQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Science Question " + i, game.scienceQuestions.get(i));
    }
  }

  @Test
  void init_creates50SportsQuestions() {
    assertEquals(50, game.sportsQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Sports Question " + i, game.sportsQuestions.get(i));
    }
  }

  @Test
  void howManyPlayers_returnsTheNumberOfPlayersAddedToTheGameIncludingDuplicates() {
    game.add("foo");
    game.add("bar");
    game.add("baz");
    game.add("foo");
    assertEquals(4, game.players.size());
  }

  @Test
  void wrongAnswer_alwaysReturnTrue() {
    game.add("foo");
    assertTrue(game.penalizeWrongAnswer());
    assertTrue(game.penalizeWrongAnswer());
  }

  @Test
  void wrongAnswer_putsCurrentPlayerInsideThePenaltyBox() {
    game.add("foo");
    assertEquals("foo", game.currentPlayer());
    assertFalse(game.inPenaltyBox[game.currentPlayer]);
    assertTrue(game.penalizeWrongAnswer());
    assertTrue(game.inPenaltyBox[game.currentPlayer]);
  }

  @Test
  void
      wrongAnswer_changesTheCurrentPlayerToTheNextPlayerAccordingToTheOrderTheyAreAddedWithWrapAround() {
    game.add("foo");
    game.add("bar");
    assertEquals("foo", game.currentPlayer());
    assertTrue(game.penalizeWrongAnswer());
    assertEquals("bar", game.currentPlayer());
    assertTrue(game.penalizeWrongAnswer());
    assertEquals("foo", game.currentPlayer());
  }

  @Test
  void init_creates50RockQuestions() {
    assertEquals(50, game.rockQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Rock Question " + i, game.rockQuestions.get(i));
    }
  }

  @Test
  void
      rewardCorrectAnswer_doesNotIncreaseTheGoldCoinsForTheCurrentPlayerIfThePlayerIsInThePenaltyBox() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = true;
    int goldCoinsBeforeCorrectAnswer = game.purses[game.currentPlayer];
    game.rewardCorrectAnswer();
    assertEquals(goldCoinsBeforeCorrectAnswer, game.purses[game.currentPlayer]);
  }

  @Test
  void rewardCorrectAnswer_increasesTheGoldCoinsForTheCurrentPlayerIfThePlayerIsNotInPenaltyBox() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = false;
    int goldCoinsBeforeCorrectAnswer = game.purses[game.currentPlayer];
    game.rewardCorrectAnswer();
    assertEquals(goldCoinsBeforeCorrectAnswer + 1, game.purses[game.currentPlayer]);
  }

  @Test
  void
      rewardCorrectAnswer_increasesTheGoldCoinsForTheCurrentPlayerIfThePLayerIsInThePenaltyBox_andIsGettingOutOfThePenaltyBox() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = true;
    game.isGettingOutOfPenaltyBox = true;
    int goldCoinsBeforeCorrectAnswer = game.purses[game.currentPlayer];
    game.rewardCorrectAnswer();
    assertEquals(goldCoinsBeforeCorrectAnswer + 1, game.purses[game.currentPlayer]);
  }

  @Test
  void rewardCorrectAnswer_advanceToTheNextPlayerRegardlessOfPriorPlayerOrGameConditions() {
    game.add("foo");
    game.add("bar");
    int currentPlayer = game.currentPlayer;
    game.rewardCorrectAnswer();
    assertTrue(currentPlayer != game.currentPlayer);
  }

  @Test
  void
      rewardCorrectAnswer_returnsTrueIfTheCurrentPlayerDoesNotHaveSixCoinsAfterIncreasingCoinCount() {
    game.add("foo");
    game.purses[game.currentPlayer] = 3;
    assertTrue(game.rewardCorrectAnswer());
  }

  @Test
  void
      rewardCorrectAnswer_returnsFalseIfTheCurrentPlayerDoesHaveSixCoinsAfterIncreasingCoinCount() {
    game.add("foo");
    game.purses[game.currentPlayer] = 5;
    assertFalse(game.rewardCorrectAnswer());
  }

  @Test
  void roll_advancesThePlayerToTheLocationAsDeterminedByTheRoll() {
    game.add("foo");
    int previousLocation = game.places[game.currentPlayer];
    int advanceLocationsBy = 2;
    game.roll(advanceLocationsBy);
    assertEquals(previousLocation + advanceLocationsBy, game.places[game.currentPlayer]);
  }

  @Test
  void
      roll_advancesThePlayerToTheLocationAsDeterminedByTheRollWithTheRollFromOneToSix_andLocationCannotBeMoreThanTwelve() {
    game.add("foo");
    for (int previousLocation = 0; previousLocation < 12; previousLocation++) {
      game.places[game.currentPlayer] = previousLocation;
      for (int advanceLocationsBy = 1; advanceLocationsBy <= 6; advanceLocationsBy++) {
        game.places[game.currentPlayer] = previousLocation;
        game.roll(advanceLocationsBy);
        assertEquals((previousLocation + advanceLocationsBy) % 12, game.places[game.currentPlayer]);
      }
    }
  }

  @Test
  void roll_advancesThePlayerIfPlayerIsInPenaltyBox_andTheRollIsOdd() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = true;
    int previousLocation = game.places[game.currentPlayer];
    int advanceLocationsBy = 3;
    game.roll(advanceLocationsBy);
    assertEquals((previousLocation + advanceLocationsBy) % 12, game.places[game.currentPlayer]);
  }

  @Test
  void roll_doesNotAdvanceThePlayerIfPlayerIsInPenaltyBox_andTheRollIsEven() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = true;
    int previousLocation = game.places[game.currentPlayer];
    int advanceLocationsBy = 2;
    game.roll(advanceLocationsBy);
    assertEquals(previousLocation, game.places[game.currentPlayer]);
  }

  @Test
  void roll_takesADiceAndUsesItToDoTheRoll() {
    game.add("foo");
    Dice diceShouldBeUsed = mock(Dice.class);
    given(diceShouldBeUsed.roll()).willReturn(1);
    game.roll(diceShouldBeUsed);
    then(diceShouldBeUsed).should(times(1)).roll();
    then(diceShouldBeUsed).shouldHaveNoMoreInteractions();
  }
}
