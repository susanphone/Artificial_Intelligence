## Conditional Independence
* we have a portion of a bayes net
notice the highlighted node

$(X \perp Y | Z)$
x is independent of y given z
$Pa(x) = U_1, ..., U_m$ to represent the parents of x
$Ch(x) = Y_1, ..., Y_n$ to represent the children of x

siblings of x are the z nodes
keep it in the context of parents and children

## Conditional Independence 1
## Conditional Independence 2
* Markov Blanket (parents of x, children of x and the childrens parents) $Pa(x) \union CH(x) \union Ch(Pa(x))$
## Direction-Dependent Separation
* disjoint sets x and y / set of evidence E d-separates x and y 
* $(X \perp Y | E)$

## Path Blocking

x -> z -> y
x <- z -> y
x -> z <- y

### Example 1
dog_out is evidence
joint prob family out and hear bark and dog out given dog out
### Example 2
family out is evidence
prob light on and dog out given family out
### Example 3
no evidence
joint prob -> marginalize, sum out the one that we don't want
V shape example
family out and has fleas are dependent? Explaining away
