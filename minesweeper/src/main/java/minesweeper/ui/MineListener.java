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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import minesweeper.logic.Game;
import minesweeper.logic.Square;

/**
 *
 * @author veeti "walther" haapsamo
 */
class MineListener implements ActionListener {

    private final Game game;
    private final JButton[] buttons;
    private final int height;
    private final int width;

    public MineListener(Game game, JButton[] buttons) {
        this.game = game;
        this.buttons = buttons;
        this.width = game.width;
        this.height = game.height;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Step into the coordinate of button
        int index = java.util.Arrays.asList(buttons).indexOf(e.getSource());
        int x = index / width;
        int y = index % width;

        game.turn(x, y);

        // Update all buttons
        for (int i = 0; i < width * height; i++) {
            int allY = i / width;
            int allX = i % width;

            Square currentSquare = game.board.getSquare(allY, allX);
            JButton currentButton = buttons[i];

            // Disable clicking on visible / seen squares
            if (currentSquare.isVisible()) {
                currentButton.setBackground(new Color(27, 28, 22));
            }
            // No text on empty squares
            if (currentSquare.isEmpty() && currentSquare.isVisible()) {
                currentButton.setText(" ");
            }
            // Set number on number square buttons
            if (currentSquare.isNumber() && currentSquare.isVisible()) {
                currentButton.setText(currentSquare.toString());
                int value = currentSquare.getValue();
                switch (value) {
                    case 1:
                        currentButton.setForeground(new Color(0x4eb4fa));
                        break;
                    case 2:
                        currentButton.setForeground(new Color(0xfd971f));
                        break;
                    case 3:
                        currentButton.setForeground(new Color(0xa6e22e));
                        break;
                    case 4:
                        currentButton.setForeground(new Color(0xae81ff));
                        break;
                    case 5:
                        currentButton.setForeground(new Color(0xf6f080));
                        break;
                    case 6:
                        currentButton.setForeground(new Color(0x575855));
                        break;
                    case 7:
                        currentButton.setForeground(new Color(0x000000));
                        break;
                    case 8:
                        currentButton.setForeground(new Color(0xf92672));
                        break;
                    default:
                        break;
                }

            }
            // If we've lost, turn stepped square red, show all mines, and disable all buttons
            if (!game.playing && !game.won && currentSquare.isMine()) {
                if (currentSquare.isVisible()) {
                    currentButton.setBackground(new Color(0xf92672));

                }
                currentButton.setText("*");
                for (JButton btn : buttons) {
                    btn.removeActionListener(this);

                }
            }

        }
    }

}
