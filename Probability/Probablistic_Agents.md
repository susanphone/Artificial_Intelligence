# Probabilistic Agents
* Assumption eveything you know is true totally and remains true
    * when you do something, take an action, the way it affect the environment is what you expect it to be
* Perfect Information?
    * Is what we assume, even if the knowledge is incomplete, uncertain, or contains conflicts
    * These questions apply to all approaches
## Why Reason Probability
* Resource bound situations, need to make a decision under uncertainty
* can model randomness and uncertainty

## Probabilistic Agents
* **Bayesian** view of the world -> will see in machine learning
* Looking at beliefs qualitatively
* Bayes rule
## Probability Review
* propositions as if they are random variables
* *probability measure* a function that is a mapping of the state space in x and y
* size is 1, a subset of the space is somewhere between 0 to 1
* subset A and B union is the sum of the probabilities - the overlay of the subsets

## Random Variables
* random variable x
* distribution over the values of the random variable
* boolean random variables
    * True = P(x)
    * False = P(\negate X) = 1 - P(X)

## Unconditional Probabilities
* types of probabilities distributions
* Unconditional probabilities(prior or marginal)
* No assumptions, just probability
* a random value that is not boolean has values that sum up to 1

## Conditional Probailities
* Modified based on other information
* Posterior or likelihood probabilities
* **$P(a|b) = P(a \AND b)\P(b)$**
    * the p of a given b is equal to the p of a and b divided by p of b
* with a,b is joint

# Marginalization and Conditioning
* Marginalization $P(Y) = \Sigma_z P(Y,z)$
    * Extracting Sub-distribution
* Conditioning $P(Y) = \Sigma_z P(Y|z)P(z)$
    * With conditional probabilities
* Given the def of conditional probability the two are equivalent
* value is lowercase, variable is uppercase
* All values that could be taken

## Product Rule
* **$P(a \and b) = P(a|b) P(b)**
* $P(a,b|e) = P(a|b,e)P(b|e)
* expanded form: $P(v_1...v_n) = P(v_1) x P(v_2|v_1)x ...xP(v_n|v_1,...,v_{n-1})$
* Called the **chain rule** by factoring

# Bayes' Rule
* two instances of the product rule
    * $P(a \and b) = P(a|b)P(b)
    * $P(b \and b) = P(b|a)P(a)
* since these of the same they can be equal to eachother

* posteriors = liklihood x prior / marginal = normalization
* if you know the priors and the likelihood you have everything you need




0.0006667
0.005
0.013
0.013 * 00006667 / 0.005

