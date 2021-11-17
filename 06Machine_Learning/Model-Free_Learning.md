# Model-Free Learning
## Model-Free RL
* What we need is def of reward and trnasition functions
* agent knows all available states and actions
* rewards comes as feedback from environment
* Non-deter -> end up is same successor state
* estimate behaviors probabilistically
* two categories of learn: 
    * model based learning: learn the model, reward function, estimate transition function
    * model-free learning: agent estimates the value function (v) and act based on v
* online learning
* algorithm q-learning
## Q-Learning
* maintain a table of q values
* q function of states and actions
* q function is being maintained
* agent act based on q function then update q function
## Q-Update Rule
* Q(sa) state action pair at time t which is the iteration or step
* q is estimate of immediate reward plus the expected reward from that point on
* iterative learning algo - control size of changes with each iteratuion (learning rate alpha(0-1))
* indexing into table state action pair, every pair has own learning rate
* indexed by time, learning rate changes over time
* required to guarentee convergence
* use single static learning rate for project (warning: it may not converge)
* learning rate is modifying how big the change is from the estimate
* maximize q, reducing the range(switchback up the mountain), Annealing learning rate
* s' average current q est with the next q est, they will get closer together every iteration
* reduce the error, temporal difference error
* simulat tells the state you are in, simulator has the model(transition and reward function)
* pi function is policy
## Learning Rate
* Anneal it
* infinite sum squared is finite
* n_t number of times visited state action pair
* tau = 1 if you do tune 0.1 or less
## Q-Learning Algorithm
* initialize to random numbers, based on size of the track
* run until done
* initializae state on starting line
* start by training at the end of the track, then back up to the corner, then back it up again and again until you cover the whole track
    * this is putting a direct backup process in place
* choose action using policy (exploration/exploitation)
    * every so often pick a random action to break from the policy
* apply action and observe (feedback signal from simulator), known current state
* q-learning = on-policy algorithm
    * regardless of the action taken
    * still updating what policy said, not what was done
    * off-policy, what was done not what was said
* actions are accelerations (increase or decrease)
* alpha is the constant learning rate
## Q-Learning Convergence
* this will converge to the optimal limit (just as effective as dynamic programming)
## exploration/exploitation
* epsulon greedy method (E ~ [0,1])
    * with prob E, use pi(s)
    * with prob (1-E) move randomly
    * this method is not strong enough to guarentee convergence
* softmax
    * Annealing
    * low temp is deterministic
* normalizer equation
* you may need to reanneal, the temps will be different

## Off-Policy Algorithm (SARSA)
* like q learning with one difference, actual state action not the maximal. {+ yQ(s',a')}
* sarsa starts long and shortens the path, q starts short and lengthens until it gets to the goal?

* project goal value iteration is base line, q or sarsa is the other, it doesn't have to cross the finish line to do the project

