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
import minesweeper.logic.Square;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 *
 * @author veeti "walther" haapsamo
 */
public class BoardTest {

    private Board instance;
    
    public BoardTest() {
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
        instance = new Board(3,1,0);
        instance.getSquare(0,0).setMine();
        instance.addNumbers();
        instance.recountMines();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Board.
     */
    @Test
    public void testToStringUnknown() {
        System.out.println("toString");
        String result = instance.toString();
        String expResult = "???\n";
        assertEquals(expResult, result);
    }

    /**
     * Test of step method, of class Board.
     */
    @Test
    public void testStep() {
        System.out.println("step");
        int stepX = 2;
        int stepY = 0;
        instance.step(stepX, stepY);
        String result = instance.toString();
        String expResult = "?10\n";
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSquare method, of class Board.
     */
    @Test
    public void testGetSquare() {
        // The square we get has to be a Square
        System.out.println("getSquare");
        int y = 0;
        int x = 0;
        boolean result = instance.getSquare(y, x).isMine();
        assertEquals(result, true);
    }
    
    /**
     * Test of addMines method, of class Board.
     */
    @Test
    public void testAddMines() {
        // Set up a 3x1 board
        instance = new Board(3,1,0);
        // Set more mines
        instance.mines = 1;
        instance.addMines();
        int result = instance.getMines();
        assertEquals(result, 1);
    }
    
    /**
     * Test of recountMines method, of class Board.
     */
    @Test
    public void recountMines() {
        // Set up a 3x1 board
        instance = new Board(3,1,0);
        // Set more mines
        // Set more mines
        instance.mines = 1;
        instance.addMines();
        instance.recountMines();
        int result = instance.getMines();
        assertEquals(result, instance.mines);
    }
    
}
