# Sudoku Operators
Inversion
Building Block *Hypothesis*:
    starts by solving small parts of the problem and starts piecing them together. Treat a substring like a building block and put the blocks together.
    still has not been proven
    "Royal Roads" problem
        Hitckhiking - neighboring bits hitchhike along making it impossible to find the neighboring blocks
## Mutation
### Random
     green - values are provided
     white - squares to manipulate
* simple random mutation, choose one of those cells
    9 -> 2 randomly changed
### Swap
    choose two values and swap their places
### Rotation
    can vary amount, row, or column
### Row Replacement - also columns
    random replace the entire row
### Blocks replacement  
    changes the values in a block
### Crossover
    One Point, serialized one puzzle along the rows.
    Two Point, select the start and finish, and only the middle gets swapped
    Uniform, flip a digit in every row/column
    Column
    Blocks
