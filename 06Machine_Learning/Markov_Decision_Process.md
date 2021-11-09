## Markov Decision Process
* states
* actions
* transition functions
* reward function - reward to taking action at that state
* discount factor
* Define: A policy is a specification that the agent uses to decide what action to take for each state
* **Stationary policy - doesn't matter when you land in a state**
* Non-stationary policy - action might be different depending on time

For project, it begins non-stationary and once enough learning has occurred, then the policy becomes stationary

## Evaluating and MDP
* MDP is the model
* Given an MDP and a model, evaluate the expected
* $V^\pi(s)$ be the expected, discounted future reward. pi means policy
* infinite horizon value possible
* value of pi can be found through Gaussian elimination
* Two common Algorithms
    * **Value Iteration**
    * Policy Iteration

## Dynamic Programming
* The original Dynamic Programming problem
* Bellman equations
* Problem divided into policies at each stage
* Stage divided into states
* Policy results to state
* Principle of Optimality -> Bellman equations
    * Memoization
