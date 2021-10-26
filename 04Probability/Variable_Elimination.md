# Variable Elimination
* Does marginalization all over
    * whether or with a joint or conditional probabilities -> summing/eliminating variables out
* ENUM-JOINT-ASK -> horrible complexity
* EMUN-ASK -> only removes the end term, but it's better
    * graph structure does not matter, still expensive
* ELIM-ASK -> O(2^n) worst case, but with polytree(multiple roots) O(n) case
    * real world networks are sparse-> can get close to ELIM-ASK
    * tree width
    * largest subtree of the graph

## Example
* Sum out everything else and normalize result
* impose an order on the variables and why.
    * order impacts computational complexity (good order is fast)
        *optimal order is NP-Hard
* once we have a ordering, process the variables right to left
    * looks like dynamic programming algorithm because its reusing previously computed subproblems
1. rewrite the query
2. marginalize the entire joint distribution - remove earthquake 
3. Rearrange terms because some terms don't depend on anything we're summing over, order matters!, but doesn't change math
4. right to left order, use factor notation
5. store probability f_M(A) (f is fy) and it defined
6. store in a matrix (2x2x2), f_A(A,B,E)
7. Sum A out from the product, since all three use A
    * AJM(B,E) are the variables left after summing out A
8. Sum E out from the product, F_EAJM(B)
9. Take product of original factor and Normalize
* M and J are given, so that's why we only need to eliminate A and E

## Pointwise Product

## Variable Elimination Algorithm
hidden variable, is an unobserved variable, no evidence other than the query
create new factor by summing out hidden variables

## A Larger Network - Determine P(J)
It can occur in the interior of the network
no evidence, what is maerginal distribution of J
elimination order -> from the outside in

### Eliminate C
sum out C
### Eliminate D
sum out D
added dash line, the two variables are related
### Eliminate I
sum out I
fake edge between G and S
### Eliminate H
add fake edge between G and J
### Eliminate G
add fake edge L and S
### Eliminate S
sum out S
### Eliminate L
sum out L and done

* Triangulation - longest undirected cycles
* subcleet is G S and J, that cleet drive the complexity
