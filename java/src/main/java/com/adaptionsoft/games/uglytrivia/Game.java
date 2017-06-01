package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

  private final PrintStream log;
  ArrayList players = new ArrayList();
  int[] places = new int[6];
  int[] purses = new int[6];
  boolean[] inPenaltyBox = new boolean[6];

  LinkedList popQuestions = new LinkedList();
  LinkedList scienceQuestions = new LinkedList();
  LinkedList sportsQuestions = new LinkedList();
  LinkedList rockQuestions = new LinkedList();

  int currentPlayer = 0;
  boolean isGettingOutOfPenaltyBox;

  public Game() {
    this(System.out);
  }

  public Game(PrintStream log) {
    this.log = log;
    for (int i = 0; i < 50; i++) {
      popQuestions.addLast("Pop Question " + i);
      scienceQuestions.addLast("Science Question " + i);
      sportsQuestions.addLast("Sports Question " + i);
      rockQuestions.addLast("Rock Question " + i);
    }
  }

  public boolean isPlayable() {
    return (howManyPlayers() >= 2);
  }

  public boolean add(String playerName) {

    players.add(playerName);
    places[howManyPlayers()] = 0;
    purses[howManyPlayers()] = 0;
    inPenaltyBox[howManyPlayers()] = false;

    this.log.println(playerName + " was added");
    this.log.println("They are player number " + players.size());
    return true;
  }

  public int howManyPlayers() {
    return players.size();
  }

  public void roll(int roll) {
    this.log.println(players.get(currentPlayer) + " is the current player");
    this.log.println("They have rolled a " + roll);

    if (inPenaltyBox[currentPlayer]) {
      isGettingOutOfPenaltyBox = roll % 2 != 0;

      if (!isGettingOutOfPenaltyBox) {
        this.log.println(players.get(currentPlayer) + " is not getting out of the penalty box");
        return;
      } else {
        this.log.println(players.get(currentPlayer) + " is getting out of the penalty box");
      }
    }
    advanceCurrentPlayerBy(roll);
    logNewLocation();
    logCurrentCategory();
    askQuestion();
  }

  private void logCurrentCategory() {
    this.log.println("The category is " + currentCategory());
  }

  private void logNewLocation() {
    this.log.println(players.get(currentPlayer)
        + "'s new location is "
        + places[currentPlayer]);
  }

  private void advanceCurrentPlayerBy(int count) {
    places[currentPlayer] = places[currentPlayer] + count;
    if (places[currentPlayer] > 11) {
      places[currentPlayer] = places[currentPlayer] - 12;
    }
  }

  private void askQuestion() {
    if (currentCategory() == "Pop") {
      this.log.println(popQuestions.removeFirst());
    }
    if (currentCategory() == "Science") {
      this.log.println(scienceQuestions.removeFirst());
    }
    if (currentCategory() == "Sports") {
      this.log.println(sportsQuestions.removeFirst());
    }
    if (currentCategory() == "Rock") {
      this.log.println(rockQuestions.removeFirst());
    }
  }

  private String currentCategory() {
    if (places[currentPlayer] == 0) {
      return "Pop";
    }
    if (places[currentPlayer] == 4) {
      return "Pop";
    }
    if (places[currentPlayer] == 8) {
      return "Pop";
    }
    if (places[currentPlayer] == 1) {
      return "Science";
    }
    if (places[currentPlayer] == 5) {
      return "Science";
    }
    if (places[currentPlayer] == 9) {
      return "Science";
    }
    if (places[currentPlayer] == 2) {
      return "Sports";
    }
    if (places[currentPlayer] == 6) {
      return "Sports";
    }
    if (places[currentPlayer] == 10) {
      return "Sports";
    }
    return "Rock";
  }

  public boolean wasCorrectlyAnswered() {
    if (inPenaltyBox[currentPlayer]) {
      if (!isGettingOutOfPenaltyBox) {
        advanceToNextPlayer();
        return true;
      }
    }
    this.log.println("Answer was correct!!!!");
    purses[currentPlayer]++;
    logCurrentPlayerPurseContent();
    boolean winner = didPlayerWin();
    advanceToNextPlayer();
    return winner;
  }

  private void logCurrentPlayerPurseContent() {
    this.log.println(players.get(currentPlayer)
        + " now has "
        + purses[currentPlayer]
        + " Gold Coins.");
  }

  public boolean wrongAnswer() {
    this.log.println("Question was incorrectly answered");
    this.log.println(players.get(currentPlayer) + " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;

    advanceToNextPlayer();
    return true;
  }

  private boolean didPlayerWin() {
    return !(purses[currentPlayer] == 6);
  }

  public String currentPlayer() {
    return (String) this.players.get(currentPlayer);
  }

  private void advanceToNextPlayer() {
    currentPlayer++;
    if (currentPlayer == players.size()) {
      currentPlayer = 0;
    }
  }
}
