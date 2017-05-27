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
}