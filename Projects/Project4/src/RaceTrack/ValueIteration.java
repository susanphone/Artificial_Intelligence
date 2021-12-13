package RaceTrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class ValueIteration {

    public ValueIteration(){
        // empty constructor
    }

    /**
     * perform value iteration to generate an optimal policy for the current track
     * @param m the MDP for the current track
     * @param epsilon a predetermined value used to determine when convergence is reached
     * @param discount the factor specified in order for the sum produced by the transition method to converge
     * @param crashType 0 for the first crash variant (mild crash) 1 for second crash variant (total start reset)
     * @return returns a hashmap that defines the policy, where the key is the state & the value is an
     * ActionValue object
     */
    public static HashMap<int[], ActionValue> decision( MDP m, double epsilon, double discount, int crashType){

        ArrayList<int[]>  states = m.states;
        ArrayList<int[]> actions = m.actions;
        ArrayList<int[]> startPositions = m.startPositions;

        //initializing the cost
        int cost = 0;

        //specified probability of acceleration at any given time step to ensure non-determinism
        double accel_probability = 0.8;

        //key is a state & value is the reward value of that state
        HashMap<int[], Double> V = new HashMap<>();

        //initialize the Q-values of each state to be 0
        for(int[] state : states){
            V.put(state, 0.0);
        }

        //will store the value function for the previous time step
        HashMap<int[], Double> VPrev = new HashMap<>();

        //key is a state and value is an ActionValue object & will store the optimal policy
        HashMap<int[], ActionValue> optimal = new HashMap<>();

        //keep track of current iteration
        int iteration = 0;

        boolean finished = false;

        while( !finished && iteration < 100){
            //initialize the bellman error
            double bellmanErr = 0.0;

            //copy the values of the old value function to be stored as the previous function for the next iteration
            for(int[] state : V.keySet()){
                VPrev.put(state, V.get(state));
            }

            System.out.println("Iteration: " + iteration);

            for(int[] state : states){

                //instantiate an ActionValue object for each state
                ActionValue nextActionValue = new ActionValue();

                double nextValue = 0.0;
                double qValue;

                for(int[] action : actions){
                    //increase cost for each loop
                    cost++;

                    //get the next the car would be in given the current action
                    int[] nextState = getNextState(states, state, action, crashType, startPositions);

                    //get the values for if the acceleration fails
                    int[] failedAccel = {0,0};
                    int[] nextStateAccelFails = getNextState(states, state, failedAccel, crashType, startPositions);

                    //calculate the q-value for the current state-action pair
                    qValue = m.reward(nextState) + (discount * m.transition(accel_probability, VPrev.get(nextState),
                            VPrev.get(nextStateAccelFails)));

                    //if its the first action-value pair for the state, or its the max q-value for the pair,
                    //make the next value the current q-value
                    if(nextValue == 0.0 || qValue > nextValue){
                        nextValue = qValue;
                        nextActionValue.action = action;
                        nextActionValue.value = nextValue;
                    }

                }

                //if the current state is actually a finish position, set the value to be 0
                if(m.finishPositions.contains(state)){
                    nextValue = 0.0;
                    nextActionValue.value = nextValue;
                }

                //put the current optimal action for a state in the policy hashmap
                optimal.put(state, nextActionValue);
                //update the value table
                V.put(state, nextValue);

                //calculate the bellman error
                double currBellmanErr = Math.abs(V.get(state) - VPrev.get(state));

                //find the max bellman error for the iteration
                if(currBellmanErr > bellmanErr){
                    bellmanErr = currBellmanErr;
                }
            }

            System.out.println("Bellman Error: " + bellmanErr);

            //finish once the max bellman error is less than the specified epsilon
            if(bellmanErr < epsilon){
                finished = true;
            }
            iteration++;
        }
        System.out.println("Cost: " + cost);
        return optimal;
    }

    /**
     * @param states array list storing all the possible states for the track
     * @param state current state we are moving from
     * @param action current action we are applying
     * @param crashType specified crash
     * @param startPositions array list of all the starting positions
     * @return returns the next state the car would be in
     */
    private static int[] getNextState(ArrayList<int[]> states, int[] state, int[] action, int crashType,
                                      ArrayList<int[]> startPositions) {

        //get the current values from the state and action
        int xCoord = state[0];
        int yCoord = state[1];
        int xVel = state[2];
        int yVel = state[3];
        int xAccel = action[0];
        int yAccel = action[1];

        //randomly decide if the car will actually accelerate
        Random rand = new Random();
        int r = rand.nextInt(10) + 1;

        if(r > 8){
            xAccel = 0;
            yAccel = 0;
        }

        //initialize the values for the new state
        int newXCoord;
        int newYCoord;
        int newXVel;
        int newYVel;

        //do not let the car exceed -5 or 5 velocities in either x or y direction
        if (xVel != -5 && xVel != 5){
            newXVel = xVel + xAccel;
            newXCoord = xCoord + newXVel;
        }else{
            newXVel = xVel;
            newXCoord = xCoord + newXVel;
        }

        if (yVel != -5 && yVel != 5){
            newYVel = yVel + yAccel;
            newYCoord = yCoord + newYVel;
        }else{
            newYVel = yVel;
            newYCoord = yCoord + newYVel;
        }
        int[] newState = {-1, -1, 0, 0};

        for (int[] s : states) {
            if (s[0] == newXCoord && s[1] == newYCoord && s[2] == newXVel && s[3] == newYVel) {
                newState = s;
            }
        }

        //if the new state is off the track then we crashed, respond based on crash type
        if(newState[0] == -1 && newState[1] == -1){
                if(crashType == 0){
                    //mild crash
                    int[] nearestState = getNearestPosition(newXCoord, newYCoord, states);
                    newState = nearestState;

                }else{
                    //bad crash
                    int[] newStart = startPositions.get(rand.nextInt(startPositions.size()));
                    newState = newStart;
                }
        }

        return newState;
    }

    /**
     * find the position on the track closest to the crash spot
     * @param newXCoord the position we end up on the 'track' outbounds
     * @param newYCoord the position we up on the 'track' outbounds
     * @param states array list of states
     * @return return the nearest state to the crash site
     */
    private static int[] getNearestPosition(int newXCoord, int newYCoord, ArrayList<int[]> states) {

        double minDist = 100;
        int[] nearest = {0, 0, 0, 0};

        for(int[] s: states){
            if(s[2] == 0 && s[3] == 0){
                double currDist = Math.sqrt((Math.pow((newXCoord - s[0]), 2.0) + (Math.pow((newYCoord - s[1]), 2.0))));

                if(currDist < minDist){
                    minDist = currDist;
                    nearest = s;
                }
            }

        }

        return nearest;
    }
}
