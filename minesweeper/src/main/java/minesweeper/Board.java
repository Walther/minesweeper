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
package minesweeper;

import java.util.Random;

/**
 *
 * @author veeti "walther" haapsamo
 */
class Board {

    private final Square[][] board;
    private final int mines;
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
        addNumbers();

    }

    private void addNumbers() {
        // Calculate number values for squares
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                countNearbyMines(y, x);
            }
        }
    }

    private void clear() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.board[i][j] = new Square();
                System.out.println("DEBUG: added square at " + i + "," + j);
            }
        }
    }

    private void countNearbyMines(int i, int j) {
        int nearbyMines = 0;
        if (!this.board[i][j].isMine()) { // Count nearby mines only if not mine itself
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
            this.board[i][j].setValue(nearbyMines);
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

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Square sq = this.board[i][j];
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
    int step(int stepY, int stepX) { // return 0 = normal click, 1 = stepped on mine, 2 = won the game
        this.board[stepY][stepX].setVisible();

        if (this.board[stepY][stepX].isMine()) {
            return 1;
        } else {
            if (this.board[stepY][stepX].isEmpty()) { // If hit an empty square, fill outwards
                for (int k = stepY - 1; k <= stepY + 1; k++) { // three wide
                    for (int l = stepX - 1; l <= stepX + 1; l++) { // three high
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

    int invisibleCount() {
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

    Square getSquare(int y, int x) {
        return board[y][x];
    }
}
