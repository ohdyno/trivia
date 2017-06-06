package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Dice;
import java.util.Random;

class RandomDice implements Dice {

  private final Random rand;

  public RandomDice(Random rand) {
    this.rand = rand;
  }

  @Override
  public int roll() {
    return rand.nextInt(5) + 1;
  }
}
