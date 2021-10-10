## Opening States/Operators
* Situation Calculus
* Goal state: predicate, if goal -> true else ->false
* Transitions that take us from one state to the next state
* **STRIPS** Operator: very early planning system that has survived to now
    Stanford Research Introduction PS
* Pre-conditions: predicates that need to be satisfied before the affects can happen
    * Example: If you want to walk you need to be standing
* The state is a conjunction of literals
## Example Operator Description
* Pickup: the command for a robot to pick something up
* Preconditions: objects we want to pick up on table, clear, and empty hand
* Once thing is picked up, we add the fact we are hold the oject and delete that fact that it is on the table, clear, and empty handed

* State, GOal predicate and results

## Problem Domain Description Language
* state explicitly what is true and false
* quantify the things in the goal test
* Equality predicate built in. Same thing, not just observation or value
* you can type the variables(int, string, char)

### Example: Moving a Piano 1
* Goal: Move piano through the window
* Planner not very good
* Finding many states, but none that move it toward the goal
### Example: Moving a Piano 2
* Faster
### Example: Air Cargo
* Description of initial state
* P1 C1 at San Francisco, P2 C2 at JFK
* Goal C1 to JFK, C2 to San Francisco
* Actions: Load, Unload, Fly(p, from, to)
### Example: Spare Tire
* Initial State at the axel, flat tire
* goal spare to the axel
* Actions: Remove(spare or flat), PutOn(Spare), Leave Overnight(if you do, you lose flat and spare)
## Planning a Search
* State space matches this
* two strat - state space search or local search
* **Situation Space Search** - build up plan one step at a time
* **Plan Space Search** - begin with a complete plan. As we go everything is valid relative to the final plan. partial order plans - a given plan still gives you multiple choices

