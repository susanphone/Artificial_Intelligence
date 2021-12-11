package RaceTrack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class QLearning2 {
    int[] trackDimensions;
    double discount;
    int[] actions;
    int[] states = new int[]{0,1,2,3,4};
    double reward;
    int[] position;
    ArrayList path = new ArrayList<>();

    public QLearning2(int[] td, double d, double r) {
        this.trackDimensions = td;
        this.discount = d;
        this.reward = r;
    }

    /* States Values
    0 = unexplored
    1 = start
    2 = road
    3 = wall
    4 = finish
     */
    // get all the possible states
    public int[] getStates() {
        return states;
    }

    // compute the action based on the possible state
    public HashMap<Integer, int[]> computeAction(int[] states) {
        HashMap computedActions = new HashMap<>();
        int[] action;
        for (int state : states) {
            if (state == 1) {
                action = new int[]{1, 0};
                action = new int[]{0, 1};
                action = new int[]{1, 1};
                computedActions.put(state, action);
            }
            if (state == 2) {
                action = new int[]{1, 0};
                action = new int[]{0, 1};
                action = new int[]{1, 1};
                computedActions.put(state, action);
            }
            if (state == 3) {
                action = new int[]{-1, 0};
                action = new int[]{0, -1};
                action = new int[]{-1, -1};
                computedActions.put(state, action);
            } else {
                state = 4;
                action = new int[]{1, 0};
                action = new int[]{0, 1};
                action = new int[]{1, 1};
                computedActions.put(state, action);
            }

        }
        return computedActions;
    }

    // set up a qtable that returns a Hashmap with the position as the key and a state and action as the value as zero
    public HashMap<int[], HashMap<int[], int[]>> initailizeQTable(int[] trackDimensions, int[] states, int[] actions) {
        HashMap<int[], HashMap<int[], int[]>> locationKnowledge = new HashMap<>();
        HashMap<int[], int[]> knowledge = new HashMap<>();
        for (int state: states) {
            state = 0;
            int statex = state;
            int statey = state;
            knowledge.put(new int[]{statex, statey}, new int[]{0, 0});
        }
        for (int x = 0; x < trackDimensions[0];x++) {
            for (int y = 0; y < trackDimensions[1]; y++) {
                locationKnowledge.put(new int[]{x, y}, knowledge);
            }
        }
        System.out.println("Initializing QTable");
        return locationKnowledge;
    }

    // update the values of the state and action that best suit the position
    public void updateQTable(HashMap<int[], HashMap<int[], int[]>> locationKnowledge, int[] state, HashMap action, int[] position) {
        locationKnowledge.get(position);
        HashMap sa = new HashMap();
        sa.put(state, action);
        locationKnowledge.put(position, sa);
        System.out.println("Updating QTable");
    }

    // get the current qtable
    public void getQTable(HashMap locationKnowledge, int[] position) {
        if (locationKnowledge.get(position) == position) {
            locationKnowledge.get(locationKnowledge.values());
        }
    }

    // if a state and action are unknown, the update the knowledge about the current position on the track.
    public HashMap<int[], HashMap<int[], int[]>> explore(int[] position, HashMap locationKnowledge) {
        System.out.println("Exploring Track");
        HashMap emptyKnowledge = new HashMap<>();
        emptyKnowledge.put(0,0);
        int[] state = getStates();
        HashMap action = computeAction(states);
        getQTable(locationKnowledge, position);
        if (locationKnowledge.containsValue(emptyKnowledge)) {
            updateQTable(locationKnowledge, state, action, position);
        } else {
            decision(locationKnowledge, reward, position, path);
        }
        return locationKnowledge;
    }

    // exploit the knowledge and decide which combination of actions will create the optimal path to drive.
    public ArrayList<int[]> decision (HashMap locationKnowledge, double reward, int[] position, ArrayList<int[]> currentPath) {
        System.out.println("Making Decisions");
        currentPath.add(0, new int[]{0, 0});
        int[] pos = (int[]) locationKnowledge.get(position);
        computeAction(states);
        reward = costFunction(reward);
        int end = currentPath.size();
        currentPath.add(end, pos);
        return currentPath;
    }

    // everytime the car moves to a new position, the reward decreases
    public Double costFunction(double reward) {
        reward = reward - 0.001;
        return reward;
    }
}

