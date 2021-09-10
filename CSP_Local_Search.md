# CSP Local Search
* So far, we fave focused on incremental search for CSPs
* CSPs are well-suited to local search methods
* Local search begins by assigning values to all variables whether the resulting assignment is consistent or not
* Variable solutions are varied
### Hill-Climbing algorithm   
see class notes/textbook
* pick a variable and change the value to a state that is better, if its not change it again.
* most optimal, can never find a solution to the puzzle, except for the easiest puzzles
### Min-Conflicts
* Local search depends on selecting and changing variable assignments
* In choosing a new value for a variable, the most obvious heuristic is to select the value that results in the minimum number of conflicts with other variables
* see note for min-cinflicts function

### Char of hill climbing
### Drawbacks of Hillclimbing
* Local optima
* Plateaus
* Ridges

## Randomized Search
* Hillclimbing with Multiple random restarts
* Simulated Annealing
* Genetic Algorithm
    population based algorithm

## Multiple random restarts
* SImplest randomized restart
* requires multiple hill climbs in fitness space
* each hill climb begins from different location
* Select start location
    * Uniform random pick
    * systematic coverage of space(aka factorial study)
