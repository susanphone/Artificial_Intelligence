# CSP Advanced Topics
## Tabu Search
* Similar to hillclimbing except performs better
* Maintain a tabu list which corresponds to states that we have previously visited.
    - if we see that node about, we ignore it
    - once the queue is filled, you push them out and they are no longer tabu.
    - as nodes pop off, they become available again
### Example: Vercox Cover
* Find a minimal size vertex cover
* **see slides for algorithms**
* Using index vector, we can apply hillclimbing to search for an optimal vertex cover
* For this and the following methods, we need a fitness function:
    - $f(C) = \{ |V|^2 + sum(|J| i=1)ind_i$ if not a valid cover
    $sum(|J| i=1)ind_i$ if a valid cover
* This function has the effect of minimizing the number of the vertices in the cover
### Hillclimbing Vertex Cover
* Define a neighborhood function $N(J)$
* This approach avoids getting stuck in a local optimum and is guarenteeds to find the global optimum
### Tabu Vertex Cover
* Use the same neighborhood function $N(J)$
* Define a "tabu" list that removes candidates from consideration
* This approach enables areas other than monotonic inprovements to be explored
## INtelligent Backtracking
* DFS just backtracks one level at a time (called *chronological*)
### Backjumping
* Define the *conflict* set to be the set of variables preceding a point requiring bachtracking that is responsible for "causing" the failure
* Generally, the conflict set for variable X consists of the set of previously assigned variables connected to X.
* Backjumping consists of backtracking to the most recent variable in the conflict set
* See slides for example
