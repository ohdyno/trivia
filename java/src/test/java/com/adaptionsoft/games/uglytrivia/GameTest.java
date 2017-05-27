package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertEquals;
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
  public void init_creates50RockQuestions() {
    assertEquals(50, game.rockQuestions.size());
    for (int i = 0; i < 50; i++) {
      assertEquals("Rock Question " + i, game.rockQuestions.get(i));
    }
  }
}