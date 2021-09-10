# State-Space Search

### Example: 15-puzzle
* environement - board
* sensors - the numbers
* actions - moving the tiles
    Initial State S_0
        Traversing the Graph
            State S_1, S_2, ..., S_g
    Think of moving the empty space vs moving the tile

### Example: Wolf, Goose, Acorn
    Wolf can't be with goose
    Goose can't be with acorn
    One can cross at a time

### Example: N-Queens
    8 by 8 chess board
    8 queens
    put the queens on the chessboard so that no queen can attack another

## Formalizing State-Space Search
* A state-space is a graph, G = (V,E)
    * Each node is a state description
    * Each edge is a transition resulting from application of an operator
* Edges have fixed costs associated with them
* One or more nodes is designated start
* One or more nodes is designated goal


* What is the initial state?
* What are the applicable operators?
* What is the goal test?
* What is the cost function?


* A goal preedicate determines if a goal node has been reached
* A solution is a sequence of operations associated with a path from start to goal
* The cost of the solution is the sum of the costs on the traversed edges
* State soace search is the process of exploring the graph to find a solution


## General Search
    see slides for algorithm
* this suggests a class of data structures

## Graph Search
    see slides for graph
* G means goal
* edges are costs

### Breadth First Search (BFS)
    see slides for algorithm and search tree
* Known as a complete algorithm
    if a solution exists, you will find it
* Left to right in that order in the level
    Solution: S-A-G
    This isn't the best path though

* Nodes placed on queue in FIFO order
* Algorithm is complete
* Optimal?
    * Yes if uniform cost on operators
    * No if variable cost on operators
* Time and space complexity = O(b^d)
    b is the branching factor
    d is the depth of solution

### Depth First Search (DFS)
    see slides for algorithm and search tree
* In a stack and use LIFO
* Backtracking to continue search
* It is complete only with a finite graph

* Not complete and not optimal
* Time and space complexity = O(b*d)?

### 4 by 4 Chess Board
    See slides
    When you have no more moves, you backtrack

## Search Order (DFS)
### Informed Search
* Adds domain-specific knowledge to identify best path
* Defines heuristic function, h(n), to estimate node goodness
* Characteristics of Heuristics
    * h(n) = 0, goal
    * h(n) = infinity, dead-end
    * h(n) >= 0, for all n

### Heuristic Function
* Let f(n) be an estimate of the cost from start to the goal
* Let h(n) be an estimate of the cost from the current node to the goal
* Let g(n) be the known cost from the start to the current node
#### f(n) = g(n) + h(n)




