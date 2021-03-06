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

import minesweeper.logic.Square;
import minesweeper.logic.Board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import minesweeper.logic.Game;

/**
 * Graphical user interface.
 *
 * @author veeti "walther" haapsamo
 */
public class GUI {

    private boolean playing;

    /**
     * A simple graphical user interface for the game.
     */
    public GUI() {
        JFrame win = new JFrame();
        win.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        // Default board size
        // Add ability to choose later.
        int width = 10;
        int height = 10;
        int mines = 10;

        Game game = new Game(width, height, mines);
        JButton[] buttons = new JButton[width * height];

        // Main button logic
        //ActionListener mineListener = new MineListener(game, buttons);
        // Add buttons for each square of the board
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                c.gridx = x;
                c.gridy = y;
                JButton button = new JButton(" ");
                buttons[y * width + x] = button;
                MineListener mineListener = new MineListener(game, buttons, button, game.board.getSquare(x, y));
                button.addMouseListener(mineListener);
                button.setFont(new Font("Monospace", Font.BOLD, 16));
                button.setMargin(new Insets(0, 0, 0, 0));

                win.add(button, c);

            }
        }

        win.setSize(win.getPreferredSize());
        win.setVisible(true);
    }
}
