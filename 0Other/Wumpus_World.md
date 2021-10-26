$h X h$ grid world
treat as an agent problem

## Percepts
* Smell stench -> wumpus nearby
* Feel breeze -> pit
* See glitter -> gold
* Feel bump
* hear scream -> wumpus hit?

## Actions
go forward or turn
pick up
shoot (only one arrow)
climb
die

## Goal 
find the goal and exit the game
gains and loses points

### Game
* agent start in the substate, which is always safe (1,1)
* use truth table to percept different things at every position
* adjacent cells is up down left and right of the current cell, no diagonals
* the safest move could be to back track, always move to a safe square
* Wumpus never moves
* Can have more than one wumpus, one arrow per wumpus
* arrow flies until it hits something, or the end of the wall. 
* Wumpus stays and still smell it even when it's dead.

### Using logic
* Being able to specify propositions
* S_i,j,  i and j are not variables
* If its a 4 by 4 board, you need a new proposition for all 16 cells
* proposition for breeze, proposition for stench, ...etc.

### Define a set of rule
* Apply inference to the rules
* Example: 
    1. if no stench -> then a wumpus in current or neighbor cells
    2. if stench -> then wumpus is current or neighbor cells
* You have the if then rules before you start exploring, *hardest task* come up with rules

### Finding the Wumpus
* If a then b, therefore a -> b
* and elimination: a conjuction of three terms, we can break them down and evaluate each facts
* Unfortunately we do not have OR, but we do have **resolution**
* The idea of resolution is eliminating and narrowing down possibilities based on what we know
* 