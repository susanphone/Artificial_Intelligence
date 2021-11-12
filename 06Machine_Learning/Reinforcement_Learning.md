# Reinforcement Learning
## Agent-Based Systems
* Environment -> Agent = State
* Agent -> Environment = Actions

## Sample Problems
* A simple navigation problem
* **The race-track problem**
* The evasive maneuvers problem

## Simple Navigation
* basic grid problem
* there are terminal cells
* each move has a cost, min cost max payoff
### Two Versions
* Deterministic Navigation
* Non-deterministic Navigation

## Race-Track Problem
* Set up as a grid 
* Running time trials
* Car begins in one of four spots and attempt to cross the finish line as soon as possible
* Actions are accelerations
* Non-determinism - oil slicks happening
* Can fall off the race track
* Goal: get the agent around the track
* If you hit the wall you either stop, or go back to the beginning (for the r track)
* Don't have to stop on the finish line
* Model-Based algorithm
* Simulator is the hardest coding
* single dot is first derivative, two dot is second derivative

## Evasive Maneuvers
* Differential Game Theory
* Missile Evasion

## Utilty
* Sequential decision problem, max utility initial state to goal state
* max expected utility principle

## Reinforcement Learning
* training agent to figure out what action to take, environment only tells the quality of the choice taken
* learning -> what the policy should be
* value and policy iteration you have the model, in this case you don't have the model
* only the interaction with the environment
* means that the behavior that reflects the dynamics has to be embodied with the simulator
* agent knows current state, action applied, and reward it received

## Not supervised learning
* RL and SL are different
* Supervised learning - choices being made, you get what the correct choice should have been
* There is no way the agent gets to know the answer, stochastic element makes it hard to know what should've been done
* No simulation, learning process as an online learning process through sweeps and derive the value function
* RL online learning, as it's acting, it updates knowledge immediately
* Process of exploration, genetic algorithms. exploration/exploitation tradeoff
* exploitation means agentfollows policy
* exploration mean agent may not follow policy, to learn more

## When RL = SL
* reward function - immediate feedback
* reward is 1 when correct, reword is 0 when it's not
* cost the agent to take action

## Models of Optimal Behavior
* Finite Horizon reward
* Infinite horizon discounted reward - project
    * take sequence and create exponential delay the longer it goes
    * discounting put emphasis on near-term action and ignore long term
    * credit assignment problem, good choices and bad choices.
* Average reward
    * calculate expected value of avg over sequence
    * sample at different lengths

## K-Armed Bandit
* exploration/exploitation

## Delayed Reinforcement
* Actions determine immediate reward
* do I remain in state 1 or move to state 2
* only consider immediate return -> the state will change once and stay there
* opposite of greedy choice, how reward accumulate over time, until the end
* this is what we are trying to solve in the project
* if we have model and if we dont have the model

## Real-Time DP
* subset is action in environment, gives sequence, gives states, and do an update
* async be an online algo
* don't need to know the model
* cap to estimate model based on experience

* Trial-based RTDP
    * discount mdp, and init eval 0, it will converge
    * undiscounted, nondeterministic, converge with prob
    * undiscounted, nondeterministic, trialbased, restrict the starting line, converge to optimal policy
        * init cost of all states are non-overestimating (a* algo)
        * cost of 0
    