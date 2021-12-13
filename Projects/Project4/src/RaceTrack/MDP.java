package RaceTrack;
import java.util.ArrayList;

/**
 *  MDP is a class that defines our Markov Decision Process objects
 *  stores all the possible states and actions, as well as reference to the finish position
 *  also defines the transition and reward functions
 */

public class MDP {

    ArrayList<int[]> states = new ArrayList<>();
    ArrayList<int[]> actions = new ArrayList<>();
    ArrayList<int[]> finishPositions = new ArrayList<>();
    ArrayList<int[]> startPositions = new ArrayList<>();

    public MDP(){
        // empty constructor
    }

    /**
     * @param accelProb the probability of acceleration (0.8)
     * @param nextStateValue the value for the state we would be in if the acceleration succeeds
     * @param nextStateValueAccelFails the value for the state we would be in if the acceleration fails
     * @return the sum of all possible outcomes from taking the current action at the current state
     * given the probability that that action succeeds
     */
    public double transition(double accelProb, double nextStateValue, double nextStateValueAccelFails){

        double val = (accelProb * nextStateValue) + ((1 - accelProb) * nextStateValueAccelFails);

        return val;
    }

    /**
     * @param state the state we are inquiring the reward for transitioning to
     * @return we return -1 for all states except the finish states where we return 0
     */
    public double reward(int[] state){

        if(finishPositions.contains(state)){
            return 0.0;
        }else{
            return -1;
        }
    }

    /**
     * setting up the list of possible actions
     * @return an ArrayList of all the possible actions for the race track
     */
    public ArrayList<int[]> setActions(){
        ArrayList<int[]> newActions = new ArrayList<>();

        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){

                // create an array list of all the possible acceleration actions
                int[] action = new int[2];
                action[0] = i; // acceleration in the x direction
                action[1] = j; // acceleration in the y direction
                newActions.add(action);
            }
        }
        return newActions;
    }

}
