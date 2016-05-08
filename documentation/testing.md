# testing documentation

## Unit testing

Unit tests have been written for all classes in the `minesweeper.logic` package. `minesweeper.ui` classes have not been unit tested, as UI testing is not in the scope of this course project.

UI has been tested manually though, with following checks:

- Starting the program has to launch GUI
- Clicking on unopened squares reveals the square
- If the square doesn't contain mine, but there are mines in its vicinity, the square will reveal a number
- If the square doesn't contain a mine and there are no mines in its vicinity, empty area is revealed until a margin of one row of numbers
- If the square contains a mine, the square will turn red, and all mines are revealed, game is lost
- If a square is right-clicked, it will be flagged
- If all mines are flagged and all other squares are visible, the game is won
- After the game has been won or lost, there's a 5 second delay before the program exits. Won/Lost status will additionally be logged in standard output.
