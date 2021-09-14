1. setupBoard(Sudoku)->empty space
2. emptySpace -> check
3. check -> generateValues
4. generateValues -> pickValues&Process
    * generateValues -> pickValue -> emptySpace is how to use the 5 variations
    * local search tree 
        * dictionary with key and value
        * agent moves through tree
        * memory stores the tree
5. pickValue&Process
    * updateBoard
    * updateMemory
        * add newly generated board
    * create array with row, column, matrix, and value
