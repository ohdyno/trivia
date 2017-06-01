package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import java.io.PrintStream;
import java.util.Random;

public class GameRunner {

  public static void main(String[] args) {
    new GameRunner().run(100, System.out);
  }

  public void run(int seed, PrintStream outputRecorder) {
    Game aGame = new Game(outputRecorder);

    aGame.add("Chet");
    aGame.add("Pat");
    aGame.add("Sue");

    Random rand = new Random(seed);

    boolean notAWinner;

    do {

      aGame.roll(rand);

      if (rand.nextInt(9) == 7) {
        notAWinner = aGame.wrongAnswer();
      } else {
        notAWinner = aGame.wasCorrectlyAnswered();
      }

    } while (notAWinner);
  }
}
