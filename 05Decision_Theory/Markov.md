## Hidden Markov Models
* Def: $HMM = \langle N, M, A, B_j, \pi \rangle$
## Observation Sequence
* this is the hidden part of the markov model
* forward sampling
## Three tasks for HMMs
1. Model Evaluation: how do we compute the probability of the observation sequence?
2. Sequence Determination: how to choose corresponding state?
3. Model Discovery: How do we adjust the parameters M to maximize P(O|M)?

## Model Evaluation
* calculate the probability of the observation given the model P(O|M)
* expensive to marginalize Q out (exponential)

## Forward-Backward Procedure
* Define the forward variables
    * Initialization
    * Recursion
    * Termination
* Find the backward variables
    * Initialization
    * Recursion
## State Sequence Determination
* Viterby Algorithm/Trellis
* Glihausen
* Define gamma using forward and backward variables
* Find the state that maximizes the gamma
* Use dynamic programming to solve

## Viterbi Algorithm
* Find the best score
* By induction, we have Bellman equation
* Memoization
* Maintain array \psi (trident) to keep arguments that maximize delta
    * Initialization
    * Recursion
    * Termination
    * Path Backtracking


## Model Discovery 
* Baum-Welch Algorithm(Expectation Maximaization)
* Learn parameters for hidden variables, so alternating min and max step
* em has one serious limitation: hill climb, can get caught in local optimal

## Baum-Welch Method
* Squiggle e \xi
* relate gamma to xi
* gamma is the marginalized xi


1. expected number of time in state at the beginning
2. expected number of transitions
3. observation / expected time in 

repeat the model until you converge

## Word recoginition
* each word has an HMM
* returns word with a highest probability
