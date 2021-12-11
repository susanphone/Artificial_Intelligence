package RaceTrack;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class QLearning2 {
    int[] trackDimensions;
    double discount;
    int[] actions;
    int[] states;
    double reward;
    int[] position;

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
    public int[] getStates() {
        return states;
    }

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

    public HashMap<int[], HashMap<int[], int[]>> initailizeQTable(int[] trackDimensions, int[] states, int[] actions) {
        reward = 0;
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

    public void updateQTable(HashMap<int[], HashMap<int[], int[]>> locationKnowledge, int[] state, HashMap action, int[] position) {
        locationKnowledge.get(position);
        HashMap sa = new HashMap();
        sa.put(state, action);
        locationKnowledge.put(position, sa);
        System.out.println("Updating QTable");
    }

    public void getQTable(HashMap locationKnowledge, int[] position) {
        if (locationKnowledge.get(position) == position) {
            locationKnowledge.get(locationKnowledge.values());
        }
    }

    public HashMap<int[], HashMap<int[], int[]>> explore(int[] position, HashMap locationKnowledge) {
        System.out.println("Exploring Track");
        HashMap emptyKnowledge = new HashMap<>();
        emptyKnowledge.put(0,0);
        int[] state = getStates();
        HashMap action = computeAction(states);
        if (locationKnowledge.containsValue(emptyKnowledge)) {
            updateQTable(locationKnowledge, state, action, position);
        } else {
            decision(locationKnowledge, reward, position);
        }
        return locationKnowledge;
    }
    public HashMap<int[], HashMap<int[], int[]>> decision (HashMap locationKnowledge, double reward, int[] position) {
        System.out.println("Making Decisions");
        int[] pos = (int[]) locationKnowledge.get(position);
        int[] state = getStates();
        if (locationKnowledge.containsValue(state)) {
            computeAction(states);
            reward = costFunction(reward);
        }
        return locationKnowledge;
    }

    public Double costFunction(double reward) {
        reward = reward - 0.001;
        return reward;
    }

}

