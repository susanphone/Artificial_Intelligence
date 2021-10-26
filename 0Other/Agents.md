# Agents
An agent is anything that can perceive its environment through a set of sensors and act upon that environment through a set of actuators.
    pereceptors
    sensors
    actions
    actuators

## What makes sudoku an agent problem?
    perceive the board
    assign value to a square in the board
    As you add values, you change the state of the board

# A Rational Agent
* Definition 1: An agent is said to be rational if it "does the right thing"
* Definition 2: For each possible percept sequence, a rational agent should select an action that is expected to maximize its performance measure, given the edivence provided by the percept sequence and whatever built-in knowledge the agent has
### Key elements
* Performance measures (to be maximized)
* Environment (and knowledge about the environment)
* Available Actions (determined by actuators)
* Percept sequence (sensor information)

## PEAS Model
* Peformance Measure
    distance traveled, patient outcome, experience points
* Evironment
    city streets, hospital or clinic, oribos
* Actuators
    Velocity, turn angle, diagnostic tests, pyroblast/ice block
* Sensors
    distance to lead vehicle, biopsy image, proximity alarm

## Properties of an environment:
* Accessible vs Inaccessible
    * Does the agent's sensors provide sufficient information to fully capture the state of the environment, or is state information only partially captured?
* Deterministic cd Nondeterministic
    * Are successor states fully determined by the current state and the action taken by the agent, or can successors be selected "at random" from a set of possible next states?
    * NOTE: Partial observability(i.e. inaccessibility) may make the environment appear to be nondeterministic.
* Episodic vs Nonepisodic
    * Is the utility of the agent's actions dependent only upon the action taken in the current state, or is look-ahead required to determine utility?
    * Immediate vs delayed "reward"
* Static vs Dynamic
    * Does the environment's state remain constant while the agent is deliberating, or can the environment's state change "on its own"?
    * If the environment is static but the utility changes with time, then the environment is semidynamic.
* Discrete vs Continuous
    * Are the number of percepts and actions clearly enumable, or do either percepts or actions lie within a range of possible values?
    * All the problems in this class will be discrete

### Simple Agent
    |Agent| sensor<-----------------percepts------|Environment|
                |
                V
                ?
                |
                V
            Actuators ------------actions------->|Environment|

### Lookup Table

### Simple Reflex Agent
1. gets the environment
2. immediately makes a decision
3. acts on that action
Code is very simple

### Agent with Internal State
    Internal state allows the agent to "think"
    Knowledge can be used before a decision is made

### Goal-Based Agent
    Milestones to reach a goal

### Utility-Based Agents
    Use of utilities

