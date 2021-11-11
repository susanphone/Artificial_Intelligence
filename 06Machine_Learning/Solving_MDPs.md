## Value Iteration
* value iteration is not a learning algorithm, just dynamic programming algorithm to process the model and give you a base line
* q function -> helps with q learn. applied to a state action pair
* greedy choice maximizes over the q function rather than v function
* interpret state s from action a.
    * stationary vs non-stationary(q and v function)
    * stationary once the algorithm stops
* if max diff drop below a threshold, cut it off
    * tunable parameter - you need to figure it out
    * Bellman error magnitude
## Algorithm
* spec of MDP and discount factor(tunable parameter <= 1)

## Policy Iteration
