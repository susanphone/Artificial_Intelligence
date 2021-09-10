Performance Measures
    arrays of filled in numbers

Environment
    Sudoku Board

Actuators
    deciding on its next number

Sensors
    iterate through the board

Goals: eliminate protential numbers

Local Search or Backtrack

Backtracking
    Forward Checking or Arc Consistency
        Arc Consistency


1. Read in the test file
2. Generate an array of values for each square
    - If value appears in square, remove value from array
    - If value does not appear, then turn null
3. Move generated value arrays to Memory
4. Find the square without a null value and go to that square (this is where square coordinates will vary between algorithms)
    1. Add value v in the square and ping the checker to come verify it works
        - If it works remove value v from the arrays with the same row, column, and matrix.
        - If it does not work, try value v + 1 and repeat the process until that spot is filled correctly.
5. While the Checker is checking, move to a new spot and retrieve the array for that spot and then repeat step a.
Update current board
6. Once board is filled, and Checker has verified all squares, update board to complete
Return complete puzzle

For step four, the starting coordinate will vary and here’s how:

* Simple Backtrack
	Start at the 1,1(x,y) and find a null value and move down the row

* Backtracking with forward checking
	Having an array of possible values
	Update neighbors when a value is removed
	Check to see everything is valid

* Backtracking with arc consistency
	When there is only one possible value, we put it in the square, and remove the value from all neighbors. Start with the coordinate that has the smallest array (least number of possible values).
	Possible Boards
* Local search using simulated annealing with the minimum conflict heuristic
	Optimal
	Insert the correct numbers in board and move them around towards a more finished board
* Local search using genetic algorithm with a penalty function and tournament selection
	Optimal
	Compare two boards and find one that is more optimal than the last