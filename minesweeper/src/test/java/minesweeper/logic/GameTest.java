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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author veeti "walther" haapsamo
 */
public class GameTest {

    private Game instance;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // Set up a 3x1 board:
        // [mine, number 1, empty]
        instance = new Game(3,1,0);
        instance.board.getSquare(0,0).setMine();
        instance.board.recountMines();
        instance.board.addNumbers();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of turn method, of class Game.
     */
    @Test
    public void testTurnOnMine() {
        System.out.println("testTurnOnMine");
        int x = 0;
        int y = 0;
        instance.turn(x, y);
        assert(instance.playing == false);
        assert(instance.won == false);
    }
    
    @Test
    public void testTurnOnNumber() {
        System.out.println("testTurnOnNumber");
        int x = 1;
        int y = 0;
        instance.turn(x, y);
        assert(instance.playing == true);
        assert(instance.won == false);
    }
    
    @Test
    public void testWin() {
        System.out.println("testTurnOnEmpty");
        int x = 2;
        int y = 0;
        instance.turn(x, y);
        instance.board.getSquare(0, 0).toggleFlag();
        instance.checkWon();
        System.out.println("playing, won: " + instance.playing + "," + instance.won);
        System.out.println("this.mines, getMines: " + instance.board.mines + "," + instance.board.getMineCount());
        System.out.println("invisiblecount: " + instance.board.invisibleCount());
        assert(instance.playing == false);
        assert(instance.won == true);
    }
    
}
