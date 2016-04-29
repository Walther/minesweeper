package minesweeper.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Board class.
 *
 * @author veeti "walther" haapsamo
 */
public class Board {

    private final Square[][] board;
    int mines;
    Random rn = new Random();
    private final int width;
    private final int height;

    /**
     * Stores the board state.
     *
     * @param width width of the board
     * @param height height of the board
     * @param mines amount of mines to place on board
     */
    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.board = new Square[this.width][this.height];

        clear();
        addMines();
        addNumbers();

    }

    private void clear() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                //System.out.println("DEBUG: Trying to add a square at " + x + "," + y);
                this.board[x][y] = new Square();
                System.out.println("DEBUG: added square at " + x + "," + y);
            }
        }
    }

    void addMines() {
        int addedMines = 0;
        while (addedMines < this.mines) {
            int randomX = rn.nextInt(this.width);
            int randomY = rn.nextInt(this.height);
            if (!this.board[randomX][randomY].isMine()) {
                this.board[randomX][randomY].setMine();
                System.out.println("DEBUG: added mine at " + randomX + "," + randomY);
                addedMines++;
            }
        }
    }

    // NOTE: has to be public due test cases, we need to be able to precisely set mines and recount
    /**
     * Add numbers to the board state.
     */
    public void addNumbers() {
        // Calculate number values for squares
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (!this.board[x][y].isMine()) {
                    int nearby = countNearbyMines(x, y);
                    this.board[x][y].setValue(nearby);
                }

            }
        }
    }

    // NOTE: for testing purposes only. TODO: clean up this hack somehow!
    /**
     * Counts the number of mines on the board.
     */
    public void recountMines() {
        int minesOnBoard = 0;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (this.board[x][y].isMine()) {
                    minesOnBoard++;
                }
            }
        }
        System.out.println("DEBUG: recounted mines: " + minesOnBoard);
        this.mines = minesOnBoard;
    }

    private int countNearbyMines(int i, int j) {
        int nearbyMines = 0;
        for (int k = i - 1; k <= i + 1; k++) { // three wide
            for (int l = j - 1; l <= j + 1; l++) { // three high
                //System.out.println("DEBUG: trying to see if " + l + "," + k + "has a mine");
                try {
                    if (this.board[k][l].isMine()) {
                        System.out.println("DEBUG: " + k + "," + l + "has a mine");
                        nearbyMines++;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {

                }

            }
        }

        return nearbyMines;
    }

    /**
     * Prettyprint of board state, for CLI purposes.
     *
     * @return string representation of board
     */
    @Override
    public String toString() {
        String ret = "";
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Square sq = this.board[x][y];
                if (sq.isVisible()) {
                    ret += sq.toString();
                } else {
                    ret += "?";
                }
            }
            ret += "\n";
        }

        return ret;
    }

    /**
     * Step onto a square. The square you stepped on is revealed. If you hit an
     * empty square, fill outwards.
     *
     * @param stepX Coordinate x
     * @param stepY Coordinate y
     */
    public void step(int stepX, int stepY) {
        this.board[stepX][stepY].setVisible();

        if (this.board[stepX][stepY].isEmpty()) {
            for (int x = stepX - 1; x <= stepX + 1; x++) { // three wide
                for (int y = stepY - 1; y <= stepY + 1; y++) { // three high
                    //System.out.println("DEBUG: trying to see if " + y + "," + x + "is steppable");
                    try {
                        if (this.board[x][y].isEmpty() && !this.board[x][y].isVisible()) {
                            //System.out.println("DEBUG: Was steppable, stepping");
                            this.step(x, y);
                        } else if (!this.board[x][y].isMine()) {
                            //System.out.println("DEBUG: Hit a number instead, only revealing");
                            this.board[x][y].setVisible();
                        }
                    } catch (ArrayIndexOutOfBoundsException exception) {
                    }
                }
            }
        }
    }

    /**
     * Counts the number of invisible / unseen squares on board.
     *
     * @return number of unseen squares
     */
    public int invisibleCount() {
        int count = 0;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (!this.board[i][j].isVisible()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns flagged squares on the board.
     *
     * @return flagged squares
     */
    public ArrayList<Square> getFlagged() {
        ArrayList<Square> flagged = new ArrayList();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (!this.board[i][j].isFlagged()) {
                    flagged.add(this.board[i][j]);
                }
            }
        }
        return flagged;
    }

    /**
     * Returns mine squares on the board.
     *
     * @return mine squares
     */
    public ArrayList<Square> getMines() {
        ArrayList<Square> flagged = new ArrayList();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (!this.board[i][j].isMine()) {
                    flagged.add(this.board[i][j]);
                }
            }
        }
        return flagged;
    }

    /**
     * Returns a Square object from the board.
     *
     * @param x Coordinate x of square to request
     * @param y Coordinate y of square to request
     * @return the Square object
     */
    public Square getSquare(int x, int y) {
        return board[x][y];
    }

    /**
     * Returns number of mines on board.
     */
    int getMineCount() {
        return this.mines;
    }
}
