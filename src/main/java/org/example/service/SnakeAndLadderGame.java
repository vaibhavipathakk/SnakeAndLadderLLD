package org.example.service;

import java.util.Deque;
import java.util.LinkedList;
import org.example.interfaces.Board;
import org.example.modals.Cell;
import org.example.modals.Dice;
import org.example.modals.Player;
import org.example.modals.SnakeAndLadderBoard;

public class SnakeAndLadderGame {

    SnakeAndLadderBoard snakeAndLadderBoard;
    Dice dice;
    Deque<Player> playersList = new LinkedList<>();

    Player winner;

    public SnakeAndLadderGame() {
        initializeSnakeAndLadderGame();
    }

    void initializeSnakeAndLadderGame() {
        snakeAndLadderBoard = new SnakeAndLadderBoard(10, 5, 4);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers() {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);

        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame() {

        while (winner == null) {
            Player playerTurn = findPlayerTurn();
            System.out.println("Its your turn : " + playerTurn.id + " , current position is : " + playerTurn.getCurrentPos());

            int diceNumbers = dice.rollDice();

            int playerNewPosition = playerTurn.getCurrentPos() + diceNumbers;
            playerNewPosition = jumpCheck(playerNewPosition);
            playerTurn.currentPos = playerNewPosition;

            System.out.println("Its your turn : " + playerTurn.id + " , new position is : " + playerTurn.getCurrentPos());

            if (playerNewPosition >= snakeAndLadderBoard.cells.length * snakeAndLadderBoard.cells.length - 1) {
                winner = playerTurn;
            }

        }

        System.out.println(">>>>>> !! Winner is : " + winner.id + " !! <<<<<<");
        System.out.println("<> <> <> !! GAME OVER !! <> <> <>");

    }

    private int jumpCheck(int playerNewPosition) {

        if ( playerNewPosition > snakeAndLadderBoard.cells.length * snakeAndLadderBoard.cells.length - 1) {
            return playerNewPosition;
        }

        Cell cell = snakeAndLadderBoard.getCell(playerNewPosition);
        if (cell.jump != null && cell.jump.start == playerNewPosition) {
            String jumpBy = (cell.jump.start < cell.jump.end) ? "Ladder" : "Snake";
            System.out.println("------ Jump done by " + jumpBy + " ------");
            playerNewPosition = cell.jump.end;
        }

        return playerNewPosition;
    }

    private Player findPlayerTurn() {
        Player playerTurns = playersList.removeFirst();
        playersList.addLast(playerTurns);
        return playerTurns;
    }


}
