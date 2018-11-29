package hu.ivgraai.goldmansachs.chess;

import java.util.*;

/**
 * @author ivgraai
 * @since 27th November
 */
public class Solution {

    /*
    Instructions:

    Given an empty chessboard (8x8 grid), a knight is placed
    on one of the squares. The knight 'K' at position (3, 3)
    and it's possible movements 'X' are shown in the example
    below:

    * * * * * * * *
    * * X * X * * *
    * X * * * X * *
    * * * K * * * *
    * X * * * X * *
    * * X * X * * *
    * * * * * * * *
    * * * * * * * *


    Depending on the knight's position on the board, 0-6 of
    the 8 possible movements may cause the knight to leave
    the chess board.

    If the knight moves n times, each time choosing one of
    the 8 possible moves uniformly at random, determine the
    probability that the knight is still on the board after
    making n random moves. After the knight has left the
    board, it may not reenter.

    Please implement the method probability which given a
    start position x, y, and a number of moves n,
    returns the probability a knight remains on the board
    as described above.
     */

    static final int BOARD_SIZE = 8;
    // Possible moves
    static final int[][] MOVES = new int[][]{
        {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
        {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
    };

    /**
     * Implement Me!
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    private static double probability(int x, int y, int n) {
        /* Define an 8x8 grid
        double[][] board = new double[BOARD_SIZE][BOARD_SIZE];
        Set the starting position
        board[x][y] = 1.0; */
        if (0 == n) {
            return 1.0;
        }
        double retval = probability(x, y, n - 1);
        double sum = 0.0;
        for (int[] move : obtainNextPositions(x, y)) {
            sum += probability(move[0], move[1], n - 1);
        }
        return (retval * sum / (BOARD_SIZE * retval));
    }

    private static List<int[]> obtainNextPositions(int x, int y) {
        List<int[]> steps = new ArrayList<>();
        for (int i = 0; i < MOVES.length; ++i) {
            int[] current = new int[]{x + MOVES[i][0], y + MOVES[i][1]};
            if (isOnBoard(current[0], current[1])) {
                steps.add(current);
            }
        }
        return steps;
    }

    private static boolean isOnBoard(int x, int y) {
        return (0 <= x && 0 <= y && x < BOARD_SIZE && y < BOARD_SIZE);
    }

    private static boolean doTestsPass() {
        // Todo please feel free to make testing more elegant
        boolean result = true;
        // Start in a corner, no moves
        result = result && probability(0, 0, 0) == 1.0;
        // Start in the middle, one move
        result = result && probability(3, 3, 1) == 1.0;
        // Start in a corner, one move
        result = result && probability(0, 0, 1) == 0.25;
        // With mathematical induction
        result = result && probability(3, 1, 1) == 0.75;
        result = result && probability(1, 3, 1) == 0.75;
        result = result && probability(0, 0, 2) == 0.1875;

        // probability(0, 0, 1) * sum( probability(3, 1, 1), probability(1, 3, 1) ) / ( 8 * probability(0, 0, 1) )
        // 0.25 * 1.5 / 2 == 0.1875

//      K * * * * * * *
//      * * X * * * * *
//      * X * * * * * *
//      * * * * * * * *
//      * * * * * * * *
//      * * * * * * * *
//      * * * * * * * *
//      * * * * * * * *
// You can mirror the following:
//      X * * * X * * *
//      * * K * * * * *
//      X P * * X * * *
//      * X * X * * * *
//      * * * * * * * *
//      * * * * * * * *
//      * * * * * * * *
//      * * * * * * * *

        return result;
    }

    /**
     * Execution entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }

}
