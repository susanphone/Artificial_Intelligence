package RaceTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class ValueIteration {

    public ValueIteration(){

    }

    public int costFunction(){
        return 0;
    }

    public static HashMap<int[], ActionValue> decision(int[] trackDim, MDP m, double epsilon, double discount){

        ArrayList<int[]>  states = m.states;
        ArrayList<int[]> actions = m.actions;

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

        int iteration = 0;

        boolean finished = false;
        double delta = 0.0;

        ArrayList<Integer> temp = new ArrayList<>();

        while(iteration < 1000000){

            //copy the values of the old value function to be stored as the previous function for the next iteration
            for(int[] state : V.keySet()){
                VPrev.put(state, V.get(state));
            }

            int currSt = 0;
            for(int[] state : states){
                currSt++;
                System.out.println("Iteration: " + iteration + " State: " + currSt );

                ActionValue nextActionValue = new ActionValue();
                double nextValue = 0.0;
                double qValue = 0.0;

                for(int[] action : actions){
                    int[] nextState = getNextState(states, state, action);

                    //if the next state doesn't exist, given the current action, move on to the next action
                    if(nextState[0] == -1 && nextState[1] == -1){
                        continue;
                    }

                    //get the values for if the acceleration fails
                    int[] failedAccel = {0,0};
                    int[] nextStateAccelFails = getNextState(states, state, failedAccel);

                    if(nextStateAccelFails[0] ==-1 && nextStateAccelFails[1] == -1){
                        continue;
                    }

                    qValue = m.reward(state) + (discount * m.transition(accel_probability, VPrev.get(nextState),
                            VPrev.get(nextStateAccelFails)));

                    if(nextValue == 0.0 || qValue > nextValue){
                        nextValue = qValue;
                        nextActionValue.action = action;
                        nextActionValue.value = nextValue;
                    }
                }
                //System.out.println("Next action value: " + nextActionValue.value);
                optimal.put(state, nextActionValue);
                V.put(state, nextValue);

            }
            iteration++;
            System.out.println("Final Size: " + temp.size());
        }

        return optimal;
    }

    private static int[] getNextState(ArrayList<int[]> states, int[] state, int[] action) {
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
            }
        }
        return newState;
    }
}