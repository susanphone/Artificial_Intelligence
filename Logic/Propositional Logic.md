# Propositional Logic
* simplest logic based on set theory
* foundation for most other logics
## Syntax
* 2 constants T and F
* Propositional symbols in a sentence
* alpha is a sentence, beta is a sentence, 
    * (a)
    * \rightangle a
    * a V b
## Semantics
* T means true and F means false
    * if *a* true -> (a) true
    * if *a* false -> $\neg a$ is true
    * if *a* or b true-> $a V b$ is true

## Truth Tables
* we know this
* take all possible combinations of true and false
* conjuction $\and$
* equivalence or iff $\iff$ <=>
* most important truth table for **project**is structure of rule, if =>then $\Infer$

### Derived Sentences
All sentences can be derived from sentences involving operators

### Interpretations
* Negation
* Disjunction
* Conjunction
* Implication - if .... then ...
* Equivalence

### Proof by Truth Tables
* Given: $P_1 \or P_2$ and $\neg P_2$
* Proof $P_1$
* Trident shape, tri?

## Normal Forms
* Conjuctive Normal Form(CNF)
    * Conjuction of disjuction of literals
    * convert to CNF is easy
* Disjunctive Normal Form(DNF)
    * A disjunction of conjunctions of literals
    * convert to DNF is difficult
* Horn Form
    * A conjunction of Horn clauses(clauses with $\leq$ 1 positive literal)
    * at most one positive literal for each clause
    * can't all be propositional sentences
    * Might have to use horn form for Program 2

### Propositional Inference
bracket mean things given
* Modus Ponens
    * if its raining, there will be wind
* Modus Tollens
* AND-Elim
* AND-Intro
* Or-intro
* Double-Negation
    * cancels out negations
* Unit resolution
* Resolution
    * Use this a lot for Project 2
* Subsumption

## Example 1
* Given $(P \equiv Q) \and (\neg P \equiv Q)$ , def implication
1. Define Implication
2. Distribution 
3. Tautology 
4. Subsumption 
5. Q.E.D

## Example 2
* Given $(P \equiv Q) \and (Q \equiv R),$ Prove $((P \equiv \neg R) \equiv \neg P)$
1. AND Intro
2. Def implication
3. Resolution
4. AND INtro. w/Tautology
5. Distribution of Terms
6. DeMorgan's
7. Def Implication
8. Q.E.D.

## Example 3
* Convert sentence to propositional symbols, AND and OR
1. Def Implication
2. DeMorgan's
3. Regrouping
4. Def Implication
5. Resolution
6. def
7. Contrapositive of Implication 
8. Q.E.D







