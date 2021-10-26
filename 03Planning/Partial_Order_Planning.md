# Partial Order Planning (POP)
Step 3: Add temporal constraints
## Algorithm
* inscription in initial state
* give a state of operatores
1. minimal plan (start action -> finish action)
2. fill in the stuff in between
    * complete?
        no, create subgoal
        yes, return plan
### Selecting the Subgoal
* pick a plan step need and fill in preconditions
### Choose Operator
* where performance of operator satisfies
    * if no where, then fail
    * if yes, link and add specific constraints
### Resolve Threats
* for each threat, is there a solution?
* repair the plan to remove threat
    * Demote - ealier in the plan
    * Promote -  later in the plan
* if step cannot be promoted or demotes, fail

## Changing the Flat Tire
1. Initial Plan: first step(effects) and last step(preconditions)
2. PutOn (Spare on Axel) -> finish
3. Remove Spare from trunk -> puton spare on axel -> finish
* If overnight before remove spare -> conflict
* Leave overnight cant be demoted or promoted -> leave overnight -> backtrack
4. Remove flat from axel -> putOn(spare)
