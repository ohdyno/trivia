package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

  private Game game;

  @Before
  public void setUp() throws Exception {
    game = new Game();
  }

  @Test
  public void add_alwaysReturnTrue() {
    assertTrue(game.add("foo"));
    assertTrue(game.add("foo"));
    assertTrue(game.add("bar"));
  }

  @Test
  public void init_creates50PopQuestions() {
    assertEquals(50, game.popQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Pop Question " + i, game.popQuestions.get(i));
    }
  }

  @Test
  public void init_creates50ScienceQuestions() {
    assertEquals(50, game.scienceQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Science Question " + i, game.scienceQuestions.get(i));
    }
  }

  @Test
  public void init_creates50SportsQuestions() {
    assertEquals(50, game.sportsQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Sports Question " + i, game.sportsQuestions.get(i));
    }

  }

  @Test
  public void howManyPlayers_returnsTheNumberOfPlayersAddedToTheGameIncludingDuplicates() {
    game.add("foo");
    game.add("bar");
    game.add("baz");
    game.add("foo");
    assertEquals(4, game.howManyPlayers());
  }

  @Test
  public void wrongAnswer_alwaysReturnTrue() {
    game.add("foo");
    assertTrue(game.wrongAnswer());
    assertTrue(game.wrongAnswer());
  }

  @Test
  public void wrongAnswer_putsCurrentPlayerInsideThePenaltyBox() {
    game.add("foo");
    assertEquals("foo", game.currentPlayer());
    assertFalse(game.inPenaltyBox[game.currentPlayer]);
    assertTrue(game.wrongAnswer());
    assertTrue(game.inPenaltyBox[game.currentPlayer]);
  }

  @Test
  public void wrongAnswer_changesTheCurrentPlayerToTheNextPlayerAccordingToTheOrderTheyAreAddedWithWrapAround() {
    game.add("foo");
    game.add("bar");
    assertEquals("foo", game.currentPlayer());
    assertTrue(game.wrongAnswer());
    assertEquals("bar", game.currentPlayer());
    assertTrue(game.wrongAnswer());
    assertEquals("foo", game.currentPlayer());
  }

  @Test
  public void init_creates50RockQuestions() {
    assertEquals(50, game.rockQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Rock Question " + i, game.rockQuestions.get(i));
    }
  }

  @Test
  public void wasCorrectlyAnswered_doesNotIncreaseTheGoldCoinsForTheCurrentPlayerIfThePlayerIsInThePenaltyBox() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = true;
    int goldCoinsBeforeCorrectAnswer = game.purses[game.currentPlayer];
    game.wasCorrectlyAnswered();
    assertEquals(goldCoinsBeforeCorrectAnswer, game.purses[game.currentPlayer]);
  }

  @Test
  public void wasCorrectlyAnswered_increasesTheGoldCoinsForTheCurrentPlayerIfThePlayerIsNotInPenaltyBox() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = false;
    int goldCoinsBeforeCorrectAnswer = game.purses[game.currentPlayer];
    game.wasCorrectlyAnswered();
    assertEquals(goldCoinsBeforeCorrectAnswer + 1, game.purses[game.currentPlayer]);
  }

  @Test
  public void wasCorrectlyAnswered_increasesTheGoldCoinsForTheCurrentPlayerIfThePLayerIsInThePenaltyBox_andIsGettingOutOfThePenaltyBox() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = true;
    game.isGettingOutOfPenaltyBox = true;
    int goldCoinsBeforeCorrectAnswer = game.purses[game.currentPlayer];
    game.wasCorrectlyAnswered();
    assertEquals(goldCoinsBeforeCorrectAnswer + 1, game.purses[game.currentPlayer]);
  }

  @Test
  public void wasCorrectlyAnswered_advanceToTheNextPlayerRegardlessOfPriorPlayerOrGameConditions() {
    game.add("foo");
    game.add("bar");
    int currentPlayer = game.currentPlayer;
    game.wasCorrectlyAnswered();
    assertTrue(currentPlayer != game.currentPlayer);
  }

  @Test
  public void wasCorrectlyAnswered_returnsTrueIfTheCurrentPlayerDoesNotHaveSixCoinsAfterIncreasingCoinCount() {
    game.add("foo");
    game.purses[game.currentPlayer] = 3;
    assertTrue(game.wasCorrectlyAnswered());
  }

  @Test
  public void wasCorrectlyAnswered_returnsFalseIfTheCurrentPlayerDoesHaveSixCoinsAfterIncreasingCoinCount() {
    game.add("foo");
    game.purses[game.currentPlayer] = 5;
    assertFalse(game.wasCorrectlyAnswered());
  }

  @Test
  public void roll_advancesThePlayerToTheLocationAsDeterminedByTheRoll() {
    game.add("foo");
    int previousLocation = game.places[game.currentPlayer];
    int advanceLocationsBy = 2;
    game.roll(advanceLocationsBy);
    assertEquals(previousLocation + advanceLocationsBy, game.places[game.currentPlayer]);
  }

  @Test
  public void roll_advancesThePlayerToTheLocationAsDeterminedByTheRollWithTheRollFromOneToSix_andLocationCannotBeMoreThanTwelve() {
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
  public void roll_advancesThePlayerIfPlayerIsInPenaltyBox_andTheRollIsOdd() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = true;
    int previousLocation = game.places[game.currentPlayer];
    int advanceLocationsBy = 3;
    game.roll(advanceLocationsBy);
    assertEquals((previousLocation + advanceLocationsBy) % 12, game.places[game.currentPlayer]);
  }

  @Test
  public void roll_doesNotAdvanceThePlayerIfPlayerIsInPenaltyBox_andTheRollIsEven() {
    game.add("foo");
    game.inPenaltyBox[game.currentPlayer] = true;
    int previousLocation = game.places[game.currentPlayer];
    int advanceLocationsBy = 2;
    game.roll(advanceLocationsBy);
    assertEquals(previousLocation, game.places[game.currentPlayer]);
  }
}