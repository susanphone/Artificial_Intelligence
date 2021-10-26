# See class slides
## Full joint Distribution
reduced 
assume a pit in a one location is dependent of a pit in another location
## Wumpus World (Again)
## Example
Is there a pit in cell 1,3
no breeze in 1,1  but 1,2 and 2,1 have a breeze
eliminate the known from the unknown
eliminate the current cell. 12 cells unkown.
set up query: probabilitiy of pit in 1,3, given what's known and the evidence.
* Marginalization: 
    reverse the process
    marginalize out the unknown variables
* Then we have ... inference!
large and expensive so we simplify
worst case 2^12 values (so 2^options)

neighboring cells define whether we will feel a breeze in the cells we've been to. Conditional dependence -> reduces parameters
assumption that probability was marginal
unknown block -> two smaller blocks
    fringe -> neighbros
    other -> the rest of unknown 
independent properties do not change
## Key Insight
Independence and conditional independence
query: prob pit in 1,3
marginalization of unknown cells
    pit     known   evidence    unknown
use product rule to rewrite the joint distribution of the two distributions
    multiple that
unkown -> fringe and other
join / p1,3 known fringe other
other is dropped because the evidence and the other are conditionally independent given a known and fringe
## Simplifying
pull middle out, because doesn't depend on other
P(p1,3, known, fringem other) -> P(p1,3) P(known) P(fringes) P(other)
separate if they don't depend on eachother
sum of other is 1, therefore it can drop
alpha to alpha' cause sum other p(other) is 1, and not needed
## Consistent Models
focus on 3 cells
build up probability for the case that there is a pit in 1,3
what are the states of the other cells
probaility of pit is 0.2
    .2 x .2 = .04
    .2 x .8 = .16
    .8 x .2 = .16
probaility of no pit
only 2
    .2 x .2 = .04
    .2 x .8 = .16
sum over fringe variables 2,2 and 3,1
now normalization: 0.31, 0.69
there is some symmetry, derivation is virtually identical


