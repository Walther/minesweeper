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
public class Game {
    // new Game();
    // game inits Board
    // on every turn, UI calls Game with a move
    // Game uses Board
    // Game checks status
    // Game returns status

    public boolean playing;
    public boolean won;
    public final Board board;
    public final int height;
    public final int width;
    private final int mines;

    public Game(int x, int y, int mines) {
        this.playing = true;
        this.won = false;
        this.width = x;
        this.height = y;
        this.mines = mines;
        this.board = new Board(x, y, mines); // Default board. Change later to support asking for details
    }

    public void turn(int x, int y) {
        // step on the board
        board.step(x, y);
        // check status
        if (board.getSquare(x, y).isMine()) {
            this.playing = false;
        } else if (board.invisibleCount() == this.mines) { // TODO: make win logic better. Include flagging.
            this.won = true;
            this.playing = false;
        }
    }
}