package RaceTrack;

// class used by ValueIteration to make an object that stores the reward the
// agent gets if it takes the action from its current state

public class ActionValue {
    int[] action = {0,0}; // initialize the action to be no acceleration
    double value;

    public ActionValue(){
        // empty constructor
    }
}
