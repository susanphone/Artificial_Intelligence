# First Order Inference
## Inference Rules
* an extension of propositional logic
### Universal elimination
### Existential Intro and Elimination
* **Unification**
    * Most general unifier(mgu)
* Quantifier useful for knowledge, but difficult to get that knowledge
* We have a predicate applied to a constant, 2 > 1, now introduce the existential quantifier. Replace 2 > 1 with there exist x > y where x > y. Making the statement true
* Exist Elim: We know the constant has to be in the domain, we do not know which constant. **Skolem Constant**
* Skolomization gets complicated because it can get tied to a function. Done so the inference procedure in complete
* No universal introduction

### Generalized Modus Ponens
* Combines AND-intro, Universal-Elimination, and Modus Ponens
    * take two facts and create the logic with AND
* P and Q imply R
* Drop the universal, assignment a constant to the variables, a constant that matches the constant in the other sentence
* theta is a substitution string
* Alpha is the sentence that you are applying the substitution to
* Finally apply Modus Ponens directly

## Inference in FOL
* matching process known as **unification**

### Unification
* define the function that youre going to pass two sentences into
* requirements:
* examples:
    * x is replaced with b
    * y is replaced with a, x is replaced with b
    * y is replaced with a, x is replaced with f(a), f is a function
    * not possible
        *cannot swap a variable in place of a constant
* common variable was trouble
* Solution: stardardizing apart variables
    p(x1) p(x2)
* Inference is going to work a lot like backtracking    
* Not everything can be unified
    * cannot standardize within the same sentence
    * cannot replace a ground literal with another, cant map a constant to a constant
    * blows a stack, becomes infinite, *occurs check*

### Unification Algorithm
* two sentences x and y 
* return substitution string
* its recursive, so if string fail, done
* otherwise compare x and y are the same, no unification required
* suppose x is a variable, unify var
* suppose y is a variable, unify var
* Compound sentence
    * if both x and y are compound, then it get recursive in trying the unify these terms. Trickery, this is the substitution string 
    * you cannot change the operators, they are like constants. Cannot unify p(x) with q(x).
* finally, two list of variables, arguments past in as a list. Try and unify them one at a time. Unify heads first and then the tails. 

#### UnifyVar Function
* test to see if that substitution already exists
* check for substitution
* unify variable with the value
* occurence check
* if substitution isn't there, then add it

## Sample Proof
* Apply Generalized Modus Ponen to solve this

