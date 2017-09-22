package com.adaptionsoft.games.trivia.v2.value;

import lombok.Value;

@Value
public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }
}
