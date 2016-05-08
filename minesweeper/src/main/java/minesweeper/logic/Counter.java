/*
 * The MIT License
 *
 * Copyright 2016 veeti "walther" haapsamo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package minesweeper.logic;

/**
 *
 * @author veeti "walther" haapsamo
 */
public class Counter {

    private final Board board;
    
    public Counter(Board board) {
        this.board = board;
    }

    /**
     * Counts the number of invisible / unseen squares on board.
     *
     * @return number of unseen squares
     */
    public int invisibleCount() {
        int count = 0;
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                if (!board.board[i][j].isVisible()) {
                    count++;
                }
            }
        }
        return count;
    }

    // NOTE: for testing purposes only. TODO: clean up this hack somehow!
    /**
     * Counts the number of mines on the board.
     */
    public void recountMines(Board board) {
        int minesOnBoard = 0;
        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                if (board.board[x][y].isMine()) {
                    minesOnBoard++;
                }
            }
        }
        System.out.println("DEBUG: recounted mines: " + minesOnBoard);
        board.mines = minesOnBoard;
    }

    int countNearbyMines(int i, int j, Board board) {
        int nearbyMines = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            // three wide
            for (int l = j - 1; l <= j + 1; l++) {
                // three high
                //System.out.println("DEBUG: trying to see if " + l + "," + k + "has a mine");
                try {
                    if (board.board[k][l].isMine()) {
                        System.out.println("DEBUG: " + k + "," + l + "has a mine");
                        nearbyMines++;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                }
            }
        }
        return nearbyMines;
    }

    /**
     * Returns number of mines on board.
     */
    int getMineCount(Board board) {
        return board.mines;
    }
    
}
