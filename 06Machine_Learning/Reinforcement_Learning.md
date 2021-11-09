# Reinforcement Learning
## Agent-Based Systems
* Environment -> Agent = State
* Agent -> Environment = Actions

## Sample Problems
* A simple navigation problem
* **The race-track problem**
* The evasive maneuvers problem

## Simple Navigation
* basic grid problem
* there are terminal cells
* each move has a cost, min cost max payoff
### Two Versions
* Deterministic Navigation
* Non-deterministic Navigation

## Race-Track Problem
* Set up as a grid 
* Running time trials
* Car begins in one of four spots and attempt to cross the finish line as soon as possible
* Actions are accelerations
* Non-determinism - oil slicks happening
* Can fall off the race track
* Goal: get the agent around the track
* If you hit the wall you either stop, or go back to the beginning (for the r track)
* Don't have to stop on the finish line
* Model-Based algorithm
* Simulator is the hardest coding
* single dot is first derivative, two dot is second derivative

## Evasive Maneuvers
* Differential Game Theory
* Missile Evasion

