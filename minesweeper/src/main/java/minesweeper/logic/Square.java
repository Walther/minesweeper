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
public class Square {

    private boolean visible;
    private int value;

    public Square() {
        this.visible = false;
        this.value = 0; // 0-8 = amount of mines in the square's surroundings, 9 = mine
    }

    public void setVisible() {
        this.visible = true;
    }

    public void setMine() {
        this.value = 9;
    }

    public void setValue(int value) {
        if (value >= 0 && value < 10) {
            this.value = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isMine() {
        return this.value == 9;
    }

    public boolean isEmpty() {
        return this.value == 0;
    }

    @Override
    public String toString() {
        return this.value + "";
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isNumber() {
        return this.value > 0 && this.value < 9;
    }

    public int getValue() {
        return this.value;
    }

}
