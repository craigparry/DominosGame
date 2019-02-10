This is a readme

How to play the game: 
Text Game: 
At the start of the game the board will be empty and the computer and the human
 player will each draw a hand of 7 dominos. 
To play a domino type in the domino with the syntax "#:#". 
A legal move is where the domino connects to an end pieces unconnected number. 
Dominos that are blanks or zeros are wild cards so they can be played on any domino.
If your hand does not have any legal plays, you will be asked to draw 
until you can play a legal move. If the graveyard runs out of dominos and the player 
cannot play a piece then the players turn ends. 
If neither player can extend the line of play of the game then the game ends 
and the last player to play the game wins. 

GUI game: 
On the GUI display click on the dominos pieces to play them. Once
a piece has been clicked it will automatically play on the board or 
the left and right buttons will appear enabled to choose a play on the 
left or the right side of the board. If there are no legal moves to 
be played in your hand the draw button will be enabled. You must draw 
from the graveyard until you have a legal move to play. If the graveyard
 runs out then the player and the computer will play their remaining hands 
 until no legal moves remain and the player who played the last piece wins the 
 game. The reset button will play a new game and is only enabled when the game 
 is over. 

DominosGame entry point is DominosGUI and DominosGameText entry point is 
MainGameLoop

fixed the end of game so that if the boneyard runs out
the game doesn't immediately end, but check first if the other player can make a move.

TODO: Refactor some of the GUI elements to make them on a ratio scale
to be more compatible with different screens.

If I had more time to fix it I would refactor some of my code 
for my game loop so that it is more malleable. I have used methods for both 
my GUI and text game, but I think that if I were to rethink how I 
initialized my Text Based game I could have made a method for the main game 
logic that could be used for both. Right now they are close and just use 
the same methods for my player and computer turn, but the methods for the 
Text based game are a bit too attached to the scanner for me to use them 
with the GUI game. Just not enough time for me to go back and extract all of 
it at this point. 