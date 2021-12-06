package RaceTrack;

import java.util.ArrayList;

public class MDP {

    ArrayList<int[]> states = new ArrayList<>();
    ArrayList<int[]> actions = new ArrayList<>();
    ArrayList<int[]> finishPositions = new ArrayList<>();
    ArrayList<int[]> wallPositions;

    public MDP(){

    }

    public double transition(double accelProb, double nextStateValue, double nextStateValueAccelFails){

        double transitionVal = (accelProb * nextStateValue) + ((1 - accelProb) * nextStateValueAccelFails);

        return transitionVal;
    }

    public double reward(int[] state){

        if(finishPositions.contains(state)){
            return 0.0;
        }else{
            return -1;
        }
    }

    public ArrayList<int[]> setActions(){

        ArrayList<int[]> newActions = new ArrayList<>();

        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                //create an array list of all the possible acceleration actions
                int[] action = new int[2];
                action[0] = i; //acceleration in the x direction
                action[1] = j;
                newActions.add(action); //acceleration in the y direction
            }
        }
        return newActions;
    }

}
