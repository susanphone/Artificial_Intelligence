# Planning
## Generic Logic-Based Agents
### Generic Agent
* Record current percept as *fact*, no uncertainty
* Infers best action using *inference*
* Records everything, see, done, response
* Performs the action as *function*
### Predicates must be indexed by time
    indexing by time -> important, some things will be easier, process will be harder
### Percepts are tested using predicates at appropriate time

### Reflex agents
* decision on the here and now and no history
* maps percepts to actions
* requires a rule for every possible state, more than a description of the percepts
* can reduce rules by rules of perception -> propositional
* reflex agent must add states for having gold to decide when to leave
* cannot avoid infinite loops-no history

### Model Based Agent
* develop internal model of world as you go(internal state)
    * record whether have gold
* **Situation Calculus** can be used to represent changing world
### Keeping track of change
* facts don't hold forever, only in situations
* Situation calculus is one way to represent change in first order logic
* situation are connected by a *Result* function
    * that includes an argument that applies to situation and action to take
### Situation Calculus
* situations ties to time
* actions map situations to situations
* must state all things that do not change - *axiom*
### Describing Actions
* effect axiom: changes due to action
* frame axiom: no changes due to actions
* this attempts to address the **frame problem**
#### Yale Shooter Problem
* is Fred alive or dead? can't answer without preception
    * what changes and what doesn't changes the outcome
### The frame problem
* represent change and non-change
* Qualification Problem: true descriptions require endless caveats
* Ramification Problem: real actions have secondary consequences

### Successor State Axioms
* doesn't solve qual or ram problem, but does determine how many axioms need to be defined
* each axiom is about a predicate

## Making Plans
* find sequence of actions to get us to the goal
* assume agent is interested in the initial plans and that plan is the only situation described in the knowledge base

## Making Better PLans
* plans as action sequences
* planResult(p,s) is the result of executing plan p in situation s
* planning systems are special purpose reasoners designed to do this type of inference more efficently than a general purpose reasoner

## The Planning Problem
* Given:
    * set of operators, actions, thing that can be be
    * initial state
    * the goal or predicate
* Find a sequence of operator instances that will modify the state to achieve the goal state

### Typical Assumptions
* Each action is atomic, cannot be split
* no concurrent actions allowed, cant to two at once
* actions are deterministic(no uncertainty in outcome)
* agent is sole cause of change to world
* agent is omniscient
* **Closed World Assumption** - Everything known to be true is included in state description. Otherwise its assumed to be false

### Possible Approaches
* State-Space Search
    - unfortunately this ignores information inherent in state and operator descriptions
* Situation Calculus

