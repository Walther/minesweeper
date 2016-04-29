# structure

`minesweeper.ui` holds the user interfaces, both a graphical one and a command line interface.

`minesweeper.logic` holds all the game logic - `square` holds state of individual squares, `board` contains the state of the entire board, and a `game` is one game instance.

When starting, the UI will call for a new `game` which will initialize a new `board` with the required `squares`. When user clicks on mouse, `mineListener` will capture the events and call the appropriate methods. `game` will keep track of the playing and won state.
