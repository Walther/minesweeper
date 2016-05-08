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

import java.util.ArrayList;

/**
 * Game class.
 *
 * @author veeti "walther" haapsamo
 */
public class Game {

    /**
     * Game status, is a game ongoing?
     */
    public boolean playing;

    /**
     * Has the game been won?
     */
    public boolean won;

    /**
     * The board object for the game.
     */
    public final Board board;

    /**
     * Height of the board.
     */
    public final int height;

    /**
     * Width of the board.
     */
    public final int width;

    final int mines;

    /**
     * Game object.
     *
     * @param x Width of board
     * @param y Height of board
     * @param mines Amount of mines on the board
     */
    public Game(int x, int y, int mines) {
        this.playing = true;
        this.won = false;
        this.width = x;
        this.height = y;
        this.mines = mines;
        this.board = new Board(x, y, mines); // Default board. Change later to support asking for details
    }

    /**
     * Abstraction of one turn.
     *
     * @param x Coordinate x of where to play
     * @param y Coordinate y of where to play
     */
    public void turn(int x, int y) {
        // step on the board
        board.step(x, y);
        // check status
        if (board.getSquare(x, y).isMine()) {
            this.playing = false;
        } else if (board.counter.invisibleCount() == board.mines) { // TODO: utilize flagsCorrect
            //} else if (flagsCorrect()) { // WHEN USING FLAGGING, FAILS ONE TEST
            this.won = true;
            this.playing = false;
            System.out.println("Won the game!");
        }
    }

    private boolean flagsCorrect() {
        ArrayList<Square> flagList = board.getFlagged();
        ArrayList<Square> mineList = board.getMines();
        return flagList.containsAll(mineList) && mineList.containsAll(flagList) && mineList.size() == flagList.size();
    }
}
