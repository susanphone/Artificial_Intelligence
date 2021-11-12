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

## Synchronous DP
* Value iteration responds to synchronous DP
* value function is updated for each state at each step

## Complexity of Synchronous DP
## Convergence
* goal states
* complexity and convergence -> alternative DP approaches

## Gauss-Seidel DP
* backup in an ordered sweep of the states
* method for project
* if value function is update, you could be using a new or an old value
* updated according to order
* indexing states based on order
* if order is updated prior to ith state, use new value. else use old value
* convergence works in discounted and non-eposodic case

## Asynchronous DP
* not an etire sweep, randomly select a subset of states
* do gauss-seidel on the subset. 
* do it enough to cover the entire space
* will converge to the limit
* cost/reward of each state is backed up infinitely oftens, visit every state action pair inifinitely often

## Nondeterministic Navigation
* transition probability
* cost function
* values calculated through dp
* optimal policy
* move to the square with the higher value
* model gives you probabilities (t function)


