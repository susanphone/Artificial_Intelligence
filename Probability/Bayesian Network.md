direct edges to nodes
    relationship between node
        P(x|y)P(y) 

        y ----> x

        y has probaility of y
        x has probability of x given y

        P(x|y)P(y) = P(y|x)P(x) = P(x,y)

no cycles are allowed
## Bayesian Net
five random variables
a node cannot be in more than one place
all variables are random and not constant until observed
## Topology
* independence assumption of distribution does not tell you about the dependence of distribution
a node given its parents is conditionally independent of its non-dependents
if a node does not have a parent, associated a marginal distribution to that node
everywhere else where a node is decended, conditional distribution
## Bayes Net
two probabilities given 1 - each to get the other 2

    P(hb,do,lo,fo,hf) = P(hb|do,lo,fo,hf)P(do|lo,fo,hf)P(lo|fo,hf)P(fo|hf)P(hf)
    P(hb,do,lo,fo,hf) = P(hb|do)P(do|fo,hf)P(lo|fo)P(fo)P(hf) **Conditional Independence**
    32 to 10

## Constructing Bayes Networks
set of variables relative to your domain
order them in some way, causal relationships
pick variable, add to network, select the parent
build up the graph assign the probability
data->exact
knowledge -> estimate

