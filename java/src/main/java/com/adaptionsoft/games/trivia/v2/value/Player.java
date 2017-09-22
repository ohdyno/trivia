package com.adaptionsoft.games.trivia.v2.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Player {
    private String name;
    private int coins;
}
