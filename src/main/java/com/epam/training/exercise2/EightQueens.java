package com.epam.training.exercise2;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EightQueens {

    private static final int ARRAYLIST_SIZE = 8;
    private static final char QUEEN_CHAR = 'q';
    private static final int COL = 0;

    private EightQueens() {
    }

    public static void main(String[] args) {
        ArrayList<char[][]> solutions = new ArrayList<>();

        char[][] result = createCharArrayWithDots();

        solveAllNQueens(result, COL, solutions);
        Logger logger = LoggerFactory.getLogger(EightQueens.class);
        logger.info(String.valueOf(solutions.size()));
        printSolutions(solutions);
    }

    private static void printSolutions(ArrayList<char[][]> solutions) {
        Logger logger = LoggerFactory.getLogger(EightQueens.class);
        for (int i = 0; i < solutions.size(); i++) {
            logger.info("");
            logger.info("Solution " + (i + 1));
            char[][] board = solutions.get(i);


            for (char[] chars : board) {
                StringBuilder line = new StringBuilder();
                for (char aChar : chars) {
                    line.append(aChar);
                }
                logger.info(String.valueOf(line));
            }
        }
    }

    private static char[][] createCharArrayWithDots() {
        char[][] result = new char[ARRAYLIST_SIZE][ARRAYLIST_SIZE];
        for (int i = 0; i < ARRAYLIST_SIZE; i++) {
            Arrays.fill(result[i], '.');
        }
        return result;
    }

    private static void solveAllNQueens(char[][] board, int col, ArrayList<char[][]> solutions) {
        if (col == board.length) {
            char[][] copy = createCopy(board);
            solutions.add(copy);
        } else {
            for (int row = 0; row < board.length; row++) {
                board[row][col] = QUEEN_CHAR;
                boolean canBeSafe = isCanBeSafe(board);

                if (canBeSafe) {
                    solveAllNQueens(board, col + 1, solutions);
                }
                board[row][col] = '.';
            }
        }
    }

    private static boolean isCanBeSafe(char[][] board) {
        boolean result = isCanBeSafe1(board);
        result = isCanBeSafe2(board, result);
        result = isCanBeSafe3(board, result);
        result = isCanBeSafe4(board, result);
        return result;
    }

    private static boolean isCanBeSafe4(char[][] board, boolean canBeSafe) {
        boolean result = canBeSafe;
        for (int offset = -board.length; offset < board.length; offset++) {
            boolean found = false;
            for (int i = 0; i < board.length; i++) {
                if (inBounds(i, board.length - offset - i - 1, board)) {
                    if (board[i][board.length - offset - i - 1] == QUEEN_CHAR) {
                        result = isFound(result, found);
                        found = true;
                    }
                }
            }
        }
        return result;
    }

    private static boolean isCanBeSafe3(char[][] board, boolean canBeSafe) {
        boolean result = canBeSafe;
        for (int offset = -board.length; offset < board.length; offset++) {
            boolean found = false;
            for (int i = 0; i < board.length; i++) {
                if (inBounds(i, i + offset, board)) {
                    if (board[i][i + offset] == QUEEN_CHAR) {
                        result = isFound(result, found);
                        found = true;
                    }
                }
            }
        }
        return result;
    }

    private static boolean isCanBeSafe2(char[][] board, boolean canBeSafe) {
        boolean result = canBeSafe;
        for (int i = 0; i < board.length; i++) {
            boolean found = false;
            for (char[] chars : board) {
                if (chars[i] == QUEEN_CHAR) {
                    result = isFound(result, found);
                    found = true;
                }
            }
        }
        return result;
    }

    private static boolean isCanBeSafe1(char[][] board) {
        boolean result = true;
        for (char[] chars : board) {
            boolean found = false;
            for (int j = 0; j < board.length; j++) {
                if (chars[j] == QUEEN_CHAR) {
                    result = isFound(result, found);
                    found = true;
                }
            }
        }
        return result;
    }

    private static boolean isFound(boolean canBeSafe, boolean found) {
        boolean multiTreadingShit = canBeSafe;
        if (found) {
            multiTreadingShit = false;
        }
        return multiTreadingShit;
    }

    private static char[][] createCopy(char[][] board) {
        char[][] copy = new char[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            System.arraycopy(board[row], 0, copy[row], 0, board[0].length);
        }
        return copy;
    }

    private static boolean inBounds(int row, int col, char[][] mat) {
        return row >= 0 && row < mat.length && col >= 0 && col < mat[0].length;
    }

}