# Graph Planning
* Build a graph incrementally (Progression planner)
    * beginning at the beginning and moving forward towards the goal
* state literals and action literals
* alternating state and action layers
* Only for **propositional planning problems**
* once something has added into the their layer in the plan, its grounded
## Example: Cake
* Can you have your cake and eat it too
## Persistent Actions
* represent persistence
## Mutual Exclusions
* Mutex
* Conflicts between actions
* Things that cannot occur at the same time
    * having cake and not having cake
    * eating the cake and not eating cake
### Mutex Links
* Inconsistent Effect - mutex link between affects and actions
* Interference: action has an affect of another action
* Competing Needs: preconditions of an action is mutually exclusive. 
* Is there a combination of actions that creates a conflict
## GRAPHPLAN
* is it possible to extract a plan directly from the graph
### Algorithm
* identify goal, are goals mutex?
### Spare Tire Planning Graph
* Bold lines are the plan
