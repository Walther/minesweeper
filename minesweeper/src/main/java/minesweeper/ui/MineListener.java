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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import minesweeper.logic.Game;
import minesweeper.logic.Square;

/**
 *
 * @author veeti "walther" haapsamo
 */
class MineListener extends MouseAdapter {

    private final Game game;
    private final JButton[] buttons;
    private final int height;
    private final int width;
    private final JButton button;
    private Square square;

    public MineListener(Game game, JButton[] buttons, JButton button, Square square) {
        this.game = game;
        this.buttons = buttons;
        this.button = button;
        this.square = square;
        this.width = game.width;
        this.height = game.height;
    }

    boolean pressed;

    @Override
    public void mousePressed(MouseEvent e) {
        button.getModel().setArmed(true);
        button.getModel().setPressed(true);
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button.getModel().setArmed(false);
        button.getModel().setPressed(false);

        if (pressed) {
            if (SwingUtilities.isRightMouseButton(e)) {
                square.toggleFlag();
                updateButtons();
            } else {
                step(e);
            }
        }
        pressed = false;

    }

    @Override
    public void mouseExited(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        pressed = true;
    }

    private void step(MouseEvent e) {
        // Step into the coordinate of button
        int index = java.util.Arrays.asList(buttons).indexOf(e.getSource());
        int y = index / width;
        int x = index % width;

        game.turn(x, y);
        updateButtons();
    }

    private void updateButtons() {
        // Update all buttons
        for (int i = 0; i < width * height; i++) {
            int allY = i / width;
            int allX = i % width;

            Square currentSquare = game.board.getSquare(allX, allY);
            JButton currentButton = buttons[i];

            // Disable clicking on visible / seen squares
            if (currentSquare.isVisible()) {
                currentButton.setBackground(new Color(27, 28, 22));
            }
            // No text on empty squares
            if (currentSquare.isEmpty() && currentSquare.isVisible() || !currentSquare.isFlagged()) {
                currentButton.setText(" ");
            }
            // F on flagged squares
            if (currentSquare.isFlagged() && !currentSquare.isVisible()) {
                currentButton.setText("F");
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
                    for (MouseListener l : btn.getMouseListeners()) {
                        btn.removeMouseListener(l);
                    }
                }
            }

        }
    }

}
