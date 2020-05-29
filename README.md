## What is 3D-Tic-Tac-Toe?

What started off as a final project for our introduction to computer science (in Java) class spun into one fully functional 3 dimensional game! 3D Tic Tac Toe, as its name suggests, is the three dimensional version of the traditionally played 2D game. Instead of playing on a 3 by 3 playing field, this version of the game covers six faces of a cube. The placement of a counter can affect up to three faces (i.e. if you place it in the corner). This version of the gaem requires more mathematical thinking and more fun.

See our project design document and specification for more details.

## Installation
to compile: javac -d . * . java

to run: java src.rubik

## Usage
You will place your piece in the cube by clicking on the corresponding cube you wish to play your piece on. The figure below shows how our game would look like in the middle of a game. The direction buttons to the left of the cube allows the player to be able to view and choose to place his/her counter on any part of the cube.

The cube will behave in such a way that if you place your move on the top right corner of one face, the same move will also be played on the top left of the face to the right of your chosen face and the bottom right of the face on top of the chosen face.

You will win the game once there are 3 of your counters in a row (or lose if 3 of the opponent’s counters are in a row) and be met with a pop up stating who won the game. The figure below shows the situation in which Player O wins by having three consecutive O’s in a horizontal row.

## Credits
Lesley Zheng

Kieren Brookens
