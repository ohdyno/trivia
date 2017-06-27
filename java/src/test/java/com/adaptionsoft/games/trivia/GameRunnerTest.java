package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class GameRunnerTest {

    @Test
    public void shouldExecuteGame() throws Exception {
        GameRunner subject = new GameRunner();
        Random rand = new Random(1);
        ByteArrayOutputStream content = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(content);
        subject.run(rand, output);
        assertArrayEquals(expectedOutput(), content.toByteArray());
    }

    private byte[] expectedOutput() throws IOException {
        Path path = Paths.get("expected.txt");
        return Files.readAllBytes(path);
    }
}