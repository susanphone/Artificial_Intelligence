package RaceTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class ValueIteration {

    public ValueIteration(){

    }

    public static HashMap<int[], ActionValue> decision(int[] trackDim, MDP m, double epsilon, double discount, int crashType){

        ArrayList<int[]>  states = m.states;
        ArrayList<int[]> actions = m.actions;

        //initializing the cost
        int cost = 0;

        //specified probability of acceleration at any given time step to ensure non-determinism
        double accel_probability = 0.8;
        int[] bellmanErrState = new int[4];

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

        int iteration = 0;

        boolean finished = false;

        while( iteration < 2 ){
            double bellmanErr = 0.0;

            //copy the values of the old value function to be stored as the previous function for the next iteration
            for(int[] state : V.keySet()){
                VPrev.put(state, V.get(state));
            }

            System.out.println("Iteration: " + iteration);

            int stateNum = 0;

            for(int[] state : states){

                stateNum++;

                ActionValue nextActionValue = new ActionValue();
                double nextValue = 0.0;
                double qValue = 0.0;

                for(int[] action : actions){
                    cost++;

                    int[] nextState = getNextState(states, state, action, crashType);

                    //if the current action causes the car to crash into a wall move on to the next action
                    if(nextState[0] == -1 && nextState[1] == -1){
                        nextValue = -10;
                        continue;
                    }

                    //get the values for if the acceleration fails
                    int[] failedAccel = {0,0};
                    int[] nextStateAccelFails = getNextState(states, state, failedAccel, crashType);


                    qValue = m.reward(state) + (discount * m.transition(accel_probability, VPrev.get(nextState),
                            VPrev.get(nextStateAccelFails)));

                    if(nextValue == 0.0 || qValue > nextValue){
                        nextValue = qValue;
                        nextActionValue.action = action;
                        nextActionValue.value = nextValue;
                    }
                }

                //if (stateNum == 105 || stateNum == 3500 || stateNum == 10000){
//                System.out.println("Next Value for state: " + state[0] + " " + state[1] + " " + state[2] + " " +
//                        state[3] + ": \n" + nextValue);
                //}


                optimal.put(state, nextActionValue);
                V.put(state, nextValue);

                double currBellmanErr = Math.abs(V.get(state) - VPrev.get(state));

                if(currBellmanErr > bellmanErr){
                    bellmanErr = currBellmanErr;
                }
            }
            System.out.println("Bellman Error: " + bellmanErr);
            if(bellmanErr < epsilon){
                finished = true;
            }
            iteration++;
        }
        System.out.println("Cost: " + cost);
        return optimal;
    }

    private static int[] getNextState(ArrayList<int[]> states, int[] state, int[] action, int crashType) {
        int xCoord = state[0];
        int yCoord = state[1];
        int xVel = state[2];
        int yVel = state[3];
        int xAccel = action[0];
        int yAccel = action[1];

        //non-determinism
        Random rand = new Random();
        int r = rand.nextInt(10) + 1;

        if(r > 8){
            xAccel = 0;
            yAccel = 0;
        }

        int newXCoord;
        int newYCoord;
        int newXVel;
        int newYVel;

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
            if(s[0] == newXCoord && s[1] == newYCoord && s[2] == newXVel && s[3] == newYVel){
                newState = s;
            }else{

                if(crashType == 0){
                    //mild crash

                }else{
                    //bad crash
                }
            }
        }
        return newState;
    }
}
