# First Order Logic
## Limitations
* doesn't have variables
* doesn't have quantifiers
* doesn't have function information
    * Example: strength of stench
* doesn't have uncertainty

* based on info you're getting, you can't choose a safe route. Probability will solve that

## First Order Logic
* Predicate Logic/Calculus
* Start with prop logic and extend it
    * constants: true and false
        * Now an arbitrary number of constants possible
    * functions
        * strength of breeze or smell
    * predicates
        * special kind of function, takes input and returns true or false
    * variables
        * quite nicde
    * Connective, and not or if then
    * Quantifier
        * UNiversal: for all
        * Existential: there exists
    * Equality
        * sematics, or values or objects tied to the thing we're testing are the same
        * very tricky

### Atomic Sentences
* Either a predicate applied to set of terms or equality between two terms (true or false)
* A term can be a var, const, or func

### Complex Sentences
* Like atomic sentences, but built up to be more complex using connectives
* A legal logical sentence
    * 1 > 2 or 1 ~> 2

## Truth in First Order
* define truth relative to model and interpretation
* models contains objects and relations amoung them
* An atomic sent predicate is true iff the objects referred to by term

## Quantifiers
* Universals are like Conjuctions
* Existentials are line disjuction
* if you have a universal quantifier, chance are you will have a sentence that includes implication
* existentials use quantifiers, or universals use and,go back and check your logic
* order of like quantifiers does not matter
* order of different quantifiers does matter, it changes their meaning

### Universal Quantification
* Use the inverted a (for all) followed by a list of variables
* Assume that everything is universally qualified
* Enumerate all people at MSU into a sentence to make this true
* As writing up sentences to project, remember this.

### Existential Quantifications
* Use reverse E (there exists)
* at least one thing is true
* use conjunction symbols, not implication symbols

### Properties
* if it involves all the same quantifier, it does not matter the order
* if it involves different quantifiers, the order will change the meaning
* Duality of Quantifiers: express each as the other
    * Teaching DeMorgons in shorthand
* DeMorgan's Law: appliy law and factor the implication out, they sentences are treated as disjunctions
