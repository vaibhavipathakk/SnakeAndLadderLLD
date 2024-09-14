package org.example;

import org.example.service.SnakeAndLadderGame;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("---<<-- Snake And Ladder Game! -->>---");
        System.out.println("----<<<-- Lets get started -->>>----");

        SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame();
        snakeAndLadderGame.startGame();

    }
}