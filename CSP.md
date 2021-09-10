# Constraint Satisfaction Problem
## A **CSP* consists of three components:
    1. *X = {x1, ..., xn}* is a set of variables
    2. *D = {D1, ... , Dn}* is a set of domains, one of each variable
    3. *C* = a set of constraints that specify allowable combinations of variables
* A domain Di consists of a set of allowable values *{v1, ..., vk}* for variable *Xi*
* Each constraint *Cj* = (Scope, rel) defines the set of variables participating in the constraint (scope) and a relation (rel) that specifies what the allowable combinations of values are
* The task is to final a value assignment for the variables that satisfy the constraints.

## CSP as Search Problem
* **Initial State**: the empty assignment {}, in which all variables are unassigned
* **Successor Function**: A value is assigned to any unassigned variable, provided it does not conflict with previously assigned variables
* **Goal Test**: The current assignment is complete(and violates no constraints)
* **Path Cost**: A constant cost (e.g. 1) for each step

### Example: Map Coloring
    we want to color the map such that such states that share a border with have a different color
* think of it as a graph using state-space search

## Types of CSPs
* Discrete variables with finite domains
    Boolean CSPs are a special subclass
* Discrete variables with infinite domains
* Continuous variables
* Linear constraints
    * Linear programming is most famous example
* Nonlinear constraints

### Types of Constraints
* Unary Constraint: Constraints on a single variable. Note that every unary constraint can be eliminated through preprocessing - remove values that violate the constraint
* Binary Constraints: Constraints relating two variables (e.g., SA not equal NSW). Note that binary CSPs only have binary constraints. These should not be confused with boolean CSPs
* High-order constraints: Constraints involving three or more variables. 
####  Example: Cryptarithms
    see class notes
    Backtracking search