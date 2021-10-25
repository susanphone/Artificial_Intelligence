# Inference in Bayesian Nets
* Diagnostic Inference
* Causal Inference
* Intercausal Inference
* Mixed Inference
## Another Network
### Diagnostic Inference
john calls, what the probability of a burglary
* P(B|J) -> Bayes' Rule = $\frac{P(J|B)P(B)}{P(J)}$

* We want $P(J) = P(J|B)P(B) + P(J|\neg B)P(\neg B)$

* = $P(J|A)**P(A)** + P(J|\neg A)**P(\neg A)**$

* What about $P(A) = **P(A|B)**P(B) + **P(A|\neg B)**P(\neg B)$

* $**P(A|B)** = P(A|B,E)P(E) + P(A|B,\neg E)P(\neg E)$

* Still need $P(J|B) = P(J|A)P(A|B)+P(J|\neg A)P(\neg A|B)$

#### Computing P(B|J)
### Causal Inference
### Intercausal Inference
* v structure
* prob of burglary given earthquake and alarm

## General Exact Inference
* marginalize y out
* ENUM-ASK(polytree- directed acyclic graph)
* ENUM-ALL

# Variable Elimination
