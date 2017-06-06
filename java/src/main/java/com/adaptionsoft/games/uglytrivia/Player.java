package com.adaptionsoft.games.uglytrivia;

import lombok.Value;

@Value
class Player {

  private String name;
  private int number;
  private int places;
  private int purses;
  private boolean inPenaltyBox;

  public Player(String name, int number) {
    this(name, number, 0, 0, false);
  }

  public Player(String name, int number, int places, int purses, boolean inPenaltyBox) {
    this.name = name;
    this.number = number;
    this.places = places;
    this.purses = purses;
    this.inPenaltyBox = inPenaltyBox;
  }
}