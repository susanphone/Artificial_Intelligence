# CSP Improvements
## General Purpose Improvements
* We know that DFS is not necessarily very efficient
* We can improve on simple backtracking (DFS) by addressing the following
    * Which variables should be assigned next, and what order should the values be tried?
    * What are the implications of the current variable assignments for the other unassigned variables?
    * When a path fails, can the search avoid repeating this failure in subsequent paths?

# Forward Checking
Whenever a variable *X* is assigned,
* Look at each unassigned variable *Y* connected to *X* by a constraint
* Delete from *Y*'s domain any value that is inconsistent with the value chosen for *X*
### Example
Map coloring problem, see class notes
* Constraint propogation will notice the error
* When nothing is left, a backtrack is triggered
* forward checking is checking then add, then checking again

# Constraint Progagation
Is the general process of propagating the implications of a constraint on variable forward onto other variables.
* The goal is to perform this propagation efficiently
* We will apply the concept of *k*-consistency to do this
## K-Consistency
* if, for any set of *k*-1 variables, and for any consistent assignment to those variables, a consistent value can be assigned to any *kth* variable
* 1-consist: *node consistency*, refers to each individual variable being self-consistent
* 2-consist: *arc consistency*, refers to the case when for every value of some variable, there exists a consistent value for each of the neighboring variables
* 3-consist: *path consistency*, refers to the case when any pair of adjacent consistent variables can be extended to a third neighboring variable.
* A graph is stringly k-consistant if it is j-consistent for j = 1,...,k.
## Arc Consistency
* see class notes
* see book for arc consistency algorithm
### Variable Assignment
* Recall the line in simple backtracking
    var:=SELECT-UNASSIGNED-VARIABLE(VARIABLE[csp], asg, csp)
* Careful selection of which variables to assign can improve the performance of backtracking search.
* Heuristics:
    * Minimum remaining values: choose the variable with the fewest "legal" values
    * Degree heuristic: choose the variable involved in the largest number constraints on other unassigned variables
        * More choices to explore and leaving your options open
    * Least constraining value: prefer the value that rules out the fewest choices for neighboring unassigned variables.
        * More choices to explore and less likely to backtrack



