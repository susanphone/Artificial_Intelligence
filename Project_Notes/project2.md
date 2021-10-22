## Statistics 
The statistics class is used to keep track of every time the explorer(Agent) finds the gold, kills a wumpus, fall into a pit, and gets killed by a wumpus. The program will increment the values of these events every time they occur. The stats class will also track the number of squares the Agent has explored. Which means every time the agent moves to a new cell, the cells_explored stat will need to be updated. The tricky part will be if the location of the cell has been explored in this round of the game. 

Some of the variables will need to be reset when the game is over, like cells_explored. We only care how many cells have been explored for the current game, not the past games.

We could also assume that we only care about how many wumpuses we kill in the current game, but also keep a count of the total number of wumpuses that have been killed in all the games?

## Board
The board consists of rows and columns. The board size can vary from 5 by 5 to 25 by 25. 

## Cell
A cell uses a unique coordinate on the board. Each cell will contain a unique truth table of the contents of the square and the surrounding squares. For example: if the explorer smells a stench, the wumpus is in a neighboring square. Or if gold is in the square, then gold would be true.

Each cell also has a status, which is either safe, goal, danger, or "frontier". Frontier status means we do not know was is in or near that cell. We can track that stats of unexplored cells by setting all but the starting cells to frontier.

## Logic
Using first order logic reasoning, or a truth table, we can decide on what is the best neighboring cell for the explorer to move to.

Each cell contains it's own logic and the agent retrieves the logic from the current cell, which will provide some guidance on what would be the next best move.

## Agent
The agent is the explorer and can perceive everything about the board. The explorer can smell a wumpus nearby, see the gold, feel a breeze, hear the screams of a wumpus, and shoot at the wumpus. 

The agent can also move up, down, left, or right around the board, and if it runs into a wumpus the agent will die. Or if the agent shoots and hits the wumpus, then the wumpus will die.


# Notes
turning counts as 1
turning around counts a 2
points for getting gold
points for killing the wumpus
agent can only move in the direction facing
percepts and map
kill agent if its been going too long
know how many wumpuses -> that many arrows


## Logic
Map of all the cells -> knowledge
Update Map using Logic based on input from explorer
If next cell is safe, move there
If not, check other cells
in none-> got back
key(position) value(knowledge about cell)

if bump-> then obstacle
if scream -> wumpus dead and cell facing is safe
if breeze in one cell has been explored and not in the neighbor, then no pit in the neighbor
if there is one unexplore neighboring cell, then go to that cell, unless there is a smell or a breeze


Each Function and its Purpose(just for reference while writing paper)
Get_neighbors(): takes in the current cell and the board, and returns a list of all possible neighbors

Logic.decide(): take in current cell, neighbors and board-> gets the clauses and returns of the possible neighbors, which is the best choice. 

Explorer.die(): takes in current cell, if state of cell contains a ‘W’ or a ‘P’ then return dead = true.

Explorer.move() : take in cardinal direction of destination, then determines how to proceed with the best move by moving forward or turning and then moving forward. If the best move is an obstacle, then the function returns false. Otherwise, the move is successful and returns true.
	turn_right(): (north to east, east to south, south to west, or west to north)
	turn_left(): (north to west, west to south, south to east, east to north)
Statistics:
	gold-found(): add 1 to score 
	wumpus-killed(): add 1 to score
	death-by-wumpus(): add 1 to score
	death-by-pit(): add 1 to score
	cells-explored(): add 1 to score
	increment-moves(): add 1 to score
Cell: contains x, y, and state of the cell
Board.generateBoard(): takes in the size of the board, and the probability of pits, obstacles, and wumpuses. Returns a completed board.
Board.printBoard(): returns a visual of the board with the states of each cell
Board.starting-position(): returns a random cell with a safe state.
