# Local Agents
* Reasoning, drawing conclusion based on evidence, knowledge base 
* fact, something from the agents perspective of being either true or false
* how these fast relate, inference based on previous observations
* can be added to the knowledge
1. how do we represent that knowledge?
    * many factors affect efficiency, don't want to brute focre
2. how do we go about reasoning with that knowledge?
    * make decisions
### Logic Motivatin
* Why logic? 
    * memory
    * experience
* good old fashion AI
* Formal methods based on logic
* Symbolic AI
### Declarative Approach
* Agent broken into declarative view:
    * Inference Engine
        * does all the work
        * never changes
    * Knowledge Base
        * A set of rules/logical statement
        * can always change
* **Resolution-> Unification**
* Percepts capture information
    * ask the agent a question
* Actions
    * agents response to the question

* facts are added all the time
### Simple Knowledge-Based Agent
* action is what is determined from this algorithm
## Logic
* Logic is a language
    * Syntax - rules 
    * Semantics - interpreter
### Types
Characterized by:
1. ontological commitment
    * facts, objects, relations, time
2. epistemological commitment
    * true or false or unknown
    * what are the possible states we can take
    * conditional belief
* Propositional Logic
    true/false/unknown
* First-order Logic
    **project 2**
* Temporal logic
    * can be time
* Fuzzy Logic
    * attempts to deal with uncertainty
    * procedes from set theory, asks what set does it belong to
        * assign a membership function()
            * need to find them
### The Landscape
* Syntax and semantics
* Think about how inference works
    * two aspects of inference
        * 1. entailment $\entail$
            * extremely strong concept
            * defines a set of models
        * 2. Inference procedure derives $\derive$
* Entailment
    * a sentence follows logically from another sentence always
    * Alpha entails beta iff every model is true, beta is also true
* Soundness 
    * proofs
    * essential
* Completeness
    * if fact true in model case, then we can prove it
    * difficult to establish(sometimes impossible)
* **prolog** occurs check-> $$, therefore prolog not complete

* map from abstract to common
### Semantics
* interp is a function that maps a sentence -> is this true of false
* languages are compositional, can be built up
## Truth in Logic
* A sentence is valid if its always true, tautology
* unsatisfiable is always false, contradiction
* satisfiable if possible to be true

* Does there exist a truth valuable assignment?







