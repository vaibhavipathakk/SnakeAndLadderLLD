package org.example.modals;

import java.util.concurrent.ThreadLocalRandom;

public class SnakeAndLadderBoard {
    public Cell[][] cells;

    public SnakeAndLadderBoard(int boardSize, int totalSnakes, int totalLadder) {
        initializeCells(boardSize);
        addSnakesAndLadder(cells,totalSnakes, totalLadder);
    }

    void initializeCells(int boardSize) {
        cells = new Cell[boardSize][boardSize];

        for (int i = 0; i < boardSize; ++i ) {
            for (int j = 0; j < boardSize; ++j ) {
                Cell cellObj = new Cell();
                cells[i][j] = cellObj;
            }
        }

    }

    void addSnakesAndLadder(Cell[][] cells, int totalSnakes, int totalLadder) {

        while (totalSnakes > 0) {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length);

            if ( snakeTail > snakeHead) continue;

            Jump snakeJump = new Jump();
            snakeJump.start = snakeHead;
            snakeJump.end = snakeTail;

            Cell cell = getCell(snakeHead);
            cell.jump = snakeJump;

            totalSnakes--;
        }

        while (totalLadder > 0) {
            int ladderStart = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length);

            if ( ladderStart > ladderEnd) continue;

            Jump ladderJump = new Jump();
            ladderJump.start = ladderStart;
            ladderJump.end = ladderEnd;

            Cell cell = getCell(ladderStart);
            cell.jump = ladderJump;

            totalLadder--;
        }



    }

    public Cell getCell(int playerPos) {

        int boardRow = playerPos / cells.length;
        int boardCol = playerPos % cells.length;

        return cells[boardRow][boardCol];
    }
}
