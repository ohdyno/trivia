package com.adaptionsoft.games.trivia;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class GameRunnerTest {

    @Test
    public void shouldExecuteGame() throws Exception {
        GameRunner subject = new GameRunner();
        Random rand = new Random(1);
        ByteArrayOutputStream content = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(content);
        subject.run(rand, output);
        assertArrayEquals(expectedOutput(),content.toByteArray());
    }

    private byte[] expectedOutput() throws IOException {
        Path path = Paths.get("expected.txt");
        return Files.readAllBytes(path);
    }
}