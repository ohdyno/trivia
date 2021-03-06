package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Dice;
import com.adaptionsoft.games.uglytrivia.Game;
import java.io.PrintStream;
import java.util.Random;

public class GameRunner {

  public static void main(String[] args) {
    new GameRunner().run(100, System.out);
  }

  public void run(final int seed, PrintStream outputRecorder) {
    Game aGame = new Game(outputRecorder);

    aGame.add("Chet");
    aGame.add("Pat");
    aGame.add("Sue");

    final Random rand = new Random(seed);

    boolean notAWinner;

    Dice randomDice = new RandomDice(rand);

    do {

      aGame.roll(randomDice);

      notAWinner = rand.nextInt(9) == 7 ? aGame.penalizeWrongAnswer() : aGame.rewardCorrectAnswer();

    } while (notAWinner);
  }
}
