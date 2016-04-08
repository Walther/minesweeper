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

import java.util.Random;

/**
 *
 * @author veeti "walther" haapsamo
 */
public class Board {

    private final Square[][] board;
    private int mines;
    Random rn = new Random();
    private final int width;
    private final int height;

    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.board = new Square[this.width][this.height];

        clear();
        addMines();
        addNumbers();

    }

    private void clear() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                System.out.println("DEBUG: Trying to add a square at " + x + "," + y);
                this.board[x][y] = new Square();
                System.out.println("DEBUG: added square at " + x + "," + y);
            }
        }
    }

    private void addMines() {
        // Add mines
        int addedMines = 0;
        while (addedMines < this.mines) {
            int randomX = rn.nextInt(this.width);
            int randomY = rn.nextInt(this.height);
            if (!this.board[randomX][randomY].isMine()) {
                this.board[randomX][randomY].setMine();
                System.out.println("DEBUG: added mine at " + randomX + "," + randomY);
                addedMines++;
            }
        }
    }

    // NOTE: has to be public due test cases, we need to be able to precisely set mines and recount
    public void addNumbers() {
        // Calculate number values for squares
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (!this.board[x][y].isMine()) {
                    int nearby = countNearbyMines(x, y);
                    this.board[x][y].setValue(nearby);
                }

            }
        }
    }
    
    // NOTE: for testing purposes only. TODO: clean up this hack somehow!
    public void recountMines() {
        int minesOnBoard = 0;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (this.board[x][y].isMine()) {
                    minesOnBoard++;
                }
            }
        }
        this.mines = minesOnBoard;
    }

    private int countNearbyMines(int i, int j) {
        int nearbyMines = 0;
        for (int k = i - 1; k <= i + 1; k++) { // three wide
            for (int l = j - 1; l <= j + 1; l++) { // three high
                System.out.println("DEBUG: trying to see if " + l + "," + k + "has a mine");
                try {
                    if (this.board[k][l].isMine()) {
                        System.out.println("DEBUG: Had a mine");
                        nearbyMines++;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {

                }

            }
        }

        return nearbyMines;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Square sq = this.board[x][y];
                if (sq.isVisible()) {
                    ret += sq.toString();
                } else {
                    ret += "?";
                }
            }
            ret += "\n";
        }

        return ret;
    }

    // Step onto a square. Following things happen:
    // - The square you stepped on is revealed
    // - if it is a mine, return 1 - you lost
    // - if you hit an empty square, areaFill other nearby empty squares + one row of numbers
    // - if you opened the last non-mine square, return 2 - you won
    public int step(int stepX, int stepY) { // return 0 = normal click, 1 = stepped on mine, 2 = won the game
        this.board[stepX][stepY].setVisible();

        if (this.board[stepX][stepY].isMine()) {
            return 1;
        } else {
            if (this.board[stepX][stepY].isEmpty()) { // If hit an empty square, fill outwards
                for (int k = stepX - 1; k <= stepX + 1; k++) { // three wide
                    for (int l = stepY - 1; l <= stepY + 1; l++) { // three high
                        System.out.println("DEBUG: trying to see if " + l + "," + k + "is steppable");
                        try {
                            if (this.board[k][l].isEmpty() && !this.board[k][l].isVisible()) {
                                System.out.println("DEBUG: Was steppable, stepping");
                                this.step(k, l);
                            } else if (!this.board[k][l].isMine()) {
                                System.out.println("DEBUG: Hit a number instead, only revealing");
                                this.board[k][l].setVisible();
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {

                        }

                    }
                }
            }
            if (invisibleCount() == this.mines) {
                return 2;
            }
            return 0;
        }

    }

    private int invisibleCount() {
        int count = 0;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (!this.board[i][j].isVisible()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Square getSquare(int y, int x) {
        return board[y][x];
    }
}
