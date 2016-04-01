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
public class SquareTest {

    public SquareTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setVisible method, of class Square.
     */
    @Test
    public void testSetVisible() {
        System.out.println("setVisible");
        Square instance = new Square();
        instance.setVisible();
        assert (instance.isVisible() == true);
    }

    /**
     * Test of setMine method, of class Square.
     */
    @Test
    public void testSetMine() {
        System.out.println("setMine");
        Square instance = new Square();
        instance.setMine();
        assert (instance.isMine() == true);

    }

    /**
     * Test of setValue method, of class Square.
     */
    @Test
    public void testSetValueValidInputs() {
        System.out.println("setValue");
        Square instance = new Square();

        for (int value = 0; value < 10; value++) {
            instance.setValue(value);
            assert (instance.getValue() == value);
        }
    }

    @Test
    public void testSetValueInvalidInputs() {
        System.out.println("setValue");
        Square instance = new Square();

        int[] invalidInputs = new int[]{-1, 10};

        for (int value = 0; value < invalidInputs.length; value++) {
            try {
                instance.setValue(invalidInputs[value]);
                fail("Did not throw IllegalArgumentException");
            } catch (IllegalArgumentException expectedException) {

            }
        }
    }

    /**
     * Test of isMine method, of class Square.
     */
    @Test
    public void testIsMine() {
        System.out.println("isMine");
        Square instance = new Square();
        assert (instance.isMine() == false);
        instance.setMine();
        assert (instance.isMine() == true);
    }

    /**
     * Test of isEmpty method, of class Square.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Square instance = new Square();
        assert (instance.isEmpty() == true);
        assert (instance.getValue() == 0);
    }

    /**
     * Test of toString method, of class Square.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Square instance = new Square();
        for (int value = 0; value < 10; value++) {
            instance.setValue(value);
            assert (instance.toString().equals("" + value));
        }
    }

    /**
     * Test of isVisible method, of class Square.
     */
    @Test
    public void testIsVisible() {
        System.out.println("isVisible");
        Square instance = new Square();
        assert (instance.isVisible() == false);
        instance.setVisible();
        assert (instance.isVisible() == true);
    }

    /**
     * Test of isNumber method, of class Square.
     */
    @Test
    public void testIsNumber() {
        System.out.println("isNumber");
        Square instance = new Square();
        for (int value = 0; value < 10; value++) {
            instance.setValue(value);
            if (value == 0 || value == 9) {
                assert (instance.isNumber() == false);
            } else {
                assert (instance.isNumber() == true);
            }
        }
    }

    /**
     * Test of getValue method, of class Square.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Square instance = new Square();
        for (int value = 0; value < 10; value++) {
            instance.setValue(value);
            assert (instance.getValue() == value);
        }
    }

}
