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
* Local Optima
* Plateaus
* Ridges

## Randomized Search
* Hillclimbing with Multiple random restarts
* Simulated Annealing
* Genetic Algorithm
    population based algorithm

## Multiple Random Restarts
* Simplest randomized restart
* requires multiple hill climbs in fitness space
* each hill climb begins from different location
* Select start location
    * Uniform random pick
    * systematic coverage of space(ala factorial study)

## Simulated Annealing
* Minor variation on hill climbing
* Permits transition to point of lesser value with some probability
* Probability of accepting poor positions changes with time
### algorithm for simulated annealing in slides
* T stands for temperature
    * heat it up and anneal the metal 
    - so start the search at a really high temperature until the limit get down to zero
* *Boltzmann Constant= 1.38E-23*
    
* k is a constant
    k = Boltzmann Constant

#### For the project:
* **How are we going to decide to stop?**
* **How do we evaluate how good/bad our solution is?**
    * Count the conflicts
    * If the difference is positive, that means it has improved
* **Track the fitness function if you can't solve the puzzles**

### Annealing Schedule
* Consider the annealing schedule as a time series. Common criteria for schedule satifies follow:
    see slide for equation
* One schedule satisfying these constraints is T_t = T_0^J / J+n_t, where T_0 is the initial (high) temperature, J is a user-defined parameter (need to be tuned), and n_t is the number of updates performed at time t

## Genetic Algorithms
* Based of biological principle "survival of the fittest"
* Encodes solution as a "chromosome"
* Maintains population of potential solutions
    Figure out the size of your problem
* Allows individuals in population to reproduce and generate new individuals
    population evolves through a number of generations until you have the solution somewhere
* Search driven by fitness function, selection mechanism, and reproduction operators
    Better has higher fitness, worse has lower fitness
        **This is a design decision**
    Reproduce the better and take operators from both parents
### See slide for genetic algorithm
1. Generate 20 boards and start a hill climb
2. Evaluate the fitness of the individuals
3. select candidates to reproduce
4. recombination
5. diversity is key, so offer mutation. Random changes in the offspring
6. replace
**How are you going to terminate**
* The board is the representation is comparison to a chromosome
### Other Representations
* Real-valued strings
* Symbolic strings
* Trees - parse tree(genetic programming)
* Graph
* Programs
    * Linear
    * Tree

### Fitness Function
* Determines the relative worth of an individual
* Analogous to fitness as in traditional iterative inprovement search
* Exogenous.....

### Fitness Proportionate Selection
* Select an individual is probabilistic
* Selection is with replacement (thus individuals can be picked more than once)
* Probabilities are etermined in proportion to fitness
    see slides for equation

### Tournament Slection
* Slecting an individual is probablistic but not with replacement
* Define a tournament size of q and select q individuals using uniform distribution
* Select the individual from tournament with maximum fitness
    * **Selection Pressure**  diversity -> premature convergence
        * the higher the pressure, the more likely you will lose diversity and create premature convergence
    * It depends on the tournament size
    * Elitism selecting only the best, but this will lose diversity quickly

### Rank-Order Search
* Selection an individual is probablistic and with replacement
* Probabilities based soley on rank (based on fitness) of individual in ordered list
* See slides for equation

### Replacement
* Generational: construct whole new population and replace previous generation
* Steady state: select small number of replacements for the next generation
* Elitist: retain n best members of population
* Endogenous: let resource availability determine survival

### Mutation
    take a bit and flip it

### Crossover
    two individuals find crossover point and offspring swap the two

### A Genetic Algorithm
see class slides
        notice is has a cooling schedule
    procedure random_selection:
        selection probability proportional to e^(-h(A)/T)
    procedure crossover
        a one point crossover








