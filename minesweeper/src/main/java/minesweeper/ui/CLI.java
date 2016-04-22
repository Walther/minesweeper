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
package minesweeper.ui;

import minesweeper.logic.Board;
import java.util.Scanner;
import minesweeper.logic.Game;

/**
 *
 * @author veeti "walther" haapsamo
 */
public class CLI {

    private final Scanner scan;

    // Primitive CLI
    // Add actual clickable GUI later...

    /**
     *
     */
    public CLI() {
        this.scan = new Scanner(System.in);
        System.out.println("Welcome to minesweeper!");
        System.out.println("How wide of a board do you want?");
        int width = scan.nextInt();
        System.out.println("How tall of a board do you want?");
        int height = scan.nextInt();
        System.out.println("How many mines do you want?");
        int mines = scan.nextInt();

        Game game = new Game(width, height, mines);
        Board board = game.board;

        boolean playing = true;
        while (playing) {
            System.out.println(board);

            System.out.println("Where do you want to step? X = ");
            int stepX = scan.nextInt();
            System.out.println("Where do you want to step? Y = ");
            int stepY = scan.nextInt();

            game.turn(stepX, stepY);
            
            if (!game.playing && !game.won) {
                System.out.println("You stepped on a mine :(");
                System.out.println(board);
                playing = false;
            } else if (!game.playing && game.won) {
                System.out.println("You won!");
                System.out.println(board);
                playing = false;
            }
        }
    }

}
