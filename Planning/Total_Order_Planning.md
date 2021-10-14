## Situation-Space Planning
* Progression: foward chaining
    * BFG, A *
    * STRIPS operators to help guide
* Regression: backward chaining
    * historically more efficient than progression planning. Look apply and test in pro. Reg precond of actions that get you to that state
* Now benefit from heuristics (piano 1 no heuristics)

## Progression Planning
* Initial State
* Actions
* Goal Test
* 
### Irrelavant Actions
* don't help the problem
* high branching slows search
### Branching Factor
* Looks at all choices instead of the choice that leads to the goal in progression
## Regression Planning
* remove any irrelevant actions
* goal -> precondition -> goal stack -> subgoal -> goal state
* consistent actions dont undo any desired literal
    * interacting subgoals
    * if all goals are dependent, then all subgoals are independent

## STRIPS
* uses a goal to support regression process
* maintains the current state while searching 
* maintain a goal stack
    * subgoals appear in certain area. once on stack is in desired order, pop from the stack until the operator remain and get to goal state
### STRIPS Algorithm
a list of goals
    
    1. push the list of goals to be satisfied (logical and) and assume in order
    if empty, done
    else, pop the stack
        1. conjuctive goal? -> test for satisfaction
            if yes, dump out the stack
            if not, fail
            push back to goal stack
            reorder and break apart using and elimination
            push back to stack
        2. individual goals? -> match state
            if yes, done
            if not, unify the operator with state
            push application of operator to goal stack
            push preconditions to goal stACK
        3. operator? -> apply the operator -> updates the state

### Example
* Operators:
    * pickup
    * putdown
    * stack
    * unstack
* State:
    * clear b and c
    * c on a
    * a on table
    * b on table
    * hand empty
* Goal:
    a on c on b
* Subgoals
    * how do we move toward the goal?
* Plan
    * updated as we achieve subgoals

## Goal Interaction
* most planners assume goals to be achieved are independent
    * this can cause infinite loops or inability to create a plan

### Sussman Anomaly
satisfying one subgoal will undo the work of another subgoal -> dependent goals
