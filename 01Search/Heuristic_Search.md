# Heuristic Search
## Informed Search
* Adds domain-specific knowledge to identify best path
* Defines a *heuristic* function, $h(n)$, to estimate node goodness
    * $h(n)$ = estimated cost from *n* to goal
* Characteristcis of Heuristics
    * $h(n) = 0$, goal
    * $h(n) = \inf$, dead-end
    * $h(n) \geq 0$, for all(*n*)

## Heuristic Function (review)
* Let $f(n)$ be an estimate of the cost from start to the goal
* Let $g(n)$ be the known cost from the start to the current node
* Let $h(n)$ be an estimate of the cost from the current node to the goal

## Best-First Search
* Nodes queued in order of evaluation function $f$ that includs the heuristic
    * Greedy Search
    * Uniform Cost Search
    * A* Search

### Algorithm for Best-First Search in Slides
* a lookup table called *reached*

### Greedy Search
* $f(n) = h(n)$
* Nodes sorted in increasing order of $f$ (upon expansion)
* Not comlete (if starts on infinite path)
* Not admissible - not guarenteed to find the optimal path
#### Search Order (Greedy)
    S
    A 8 
    B 4
    C 3
    D inf
    E inf
    G 0

              Start
           1/   5|  8\
           A     B    C
          /|\    |   /
        3/7| 9\ 4|  /5
        D  E    Goal

    C/3, B/4, A/8 -> {G/0, B/4, A/8}
    S-C-G is still suboptimal

### Uniform Cost Search
* $f(n)=g(n)$
* Nodes sorted in increasing order of $f$ (upon expansion)
* Complete if all costs positive
* Admissible - Basically makes Best-First Search optimal

        S
        A 8   B 4   C 3   D inf   E inf   G 0

              Start
           1/   5|  8\
           A     B    C
          /|\    |   /
        3/7| 9\ 4|  /5
        D  E    Goal

        {A/1, B/5, C/8}
        {D/3, B/5, E/7, C/8, G/9}
        {B/5, E/8, C/8, G/10}
        {E/8, C/8, G/9, G/10}
        {C/8, G/9, G/10}
        {G/9, G/10, G/13}
        => S-B-G

### A* Search
* $f(n) = g(n) + h(n)$
    * $g(n)$ = minimal cost from start to $n$
    * $h'(n)$ = minimal cost from $n$ to goal
    * $h(n)$ = estimate of $h'(n)$
* If $h(n) \leq h'(n)$.... check slides
    * A* is complete
    * A* is admissible

            {A/9, B/9, C/11}
            {B/9, G/10, C/11, D/inf, E/inf}
            {G/9, G/10, C/11, D/inf, E/inf}
            => S-B-G (Optimal)
#### Admissibility of A*
* **Theorem**: if $h'(n)$ is admissible, then A* is guarenteed to find the optimal path
* **Proof**: Let $G$ be a goal state reached by an optimal path with path cost f*
* Let $G_2$ be a goal state possible the same reached by suboptimal path, that is $g(G_2) > f* = g(G)$
* Suppose A* sleects $G_2$ from the queue 
**see slides**

8 Consider node $n$ that is currently a leaf node on the optimal path to G
* Because h is admissible, $(h \leq h')$, we must have $f* \geq f(n)$ since the heuristic function is also monotonic
* if n is not chsen for expansion,
* **Corollary**: If $h'(n) = h(n)$ forall n, then only path expaned is the optimal path
* **seriously check slides**

## Bandit Problem
Slot machines all have a different chance on winning
* Limited generation(resources)
* The mechanism of selection
## Stochastic Search
Incorporates randomness into the search
* Simulated Annealing
* Genetic Algorithm
* Random Restart Hillclimbing
* Particle Swarm Optimization