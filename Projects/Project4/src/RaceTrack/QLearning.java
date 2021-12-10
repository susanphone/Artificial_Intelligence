package RaceTrack;

import java.util.HashMap;

public class QLearning {

    int[] actions;
    char[] states;
    int reward;
    int[] position;

    public QLearning(char[] s, int[] p) {
        this.states = s;
        this.position = p;
    }

    public char getState(char[] states) {
        for (char state: states) {
            if (state == 'S') {
                return state;
            }
            if (state == 'R') {
                return state;
            }
            if (state == 'W') {
                return state;
            } else {
                return state;
            }
        }
        return states[0];
    }

    public HashMap initializeQTable(int xSize, int ySize) {
        HashMap<int[], char[]> knowledge = new HashMap<>();
        char state = 'U';
        int action = 0;
        char[] charArray = {state, (char)action};
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                int[] pos = {x,y};
                // set state as unknown and action as 0.
                knowledge.put(pos, charArray);
            }
        }
        return knowledge;
    }

    // once position has been explored and car has not crashed, update knowledge
    public void updateQTable(HashMap matrix, int[] position, int action, char state){
        char[] charArray = {state, (char) action};
        matrix.put(position, charArray);
    }

    // if grid space is unknown, move to the space and update the state and optimal action for that position
    public char explore(int[] position, HashMap knowledge) {
        char action = '0';
        char state = getState(states);
        if (state == 'R' || state == 'S') {
            action = '1';
        } else {
            action = '9';
        }
        char[] charArray = {state, (char) action};
        knowledge.put(position, charArray);
        updateQTable(knowledge, position, action, state);
        return action;
    }

    public char decision(char state, double currentMoveReward, HashMap knowledge) {
        int[] pos = this.position;
        // continue at current speed going straight
        char action = '0';
        int x = pos[0];
        int y = pos[1];
        // let the session continue as long as the current state is not 'W'
        boolean done = false;
        // while state is not terminal, continue on track
        if ((state == 'S' || state == 'R') && currentMoveReward >= 0.01) {
             action = explore(pos, knowledge);
            currentMoveReward = currentMoveReward - 0.001;
            if (currentMoveReward == 0.1 ) {
                System.out.println("Timeout");
                done = true;
            }
            if (done) {
                return state;
            }
        }
        pos[0] = x+1;
        pos[1] = y;
        return action;
    }

    public HashMap<int[], char[]> runLearning(char[] states, int reward, int[] size) {
        int x = size[0];
        int y = size[1];
        char state = getState(states);
        HashMap knowledgeMap = initializeQTable(x, y);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                char act = decision(state, reward, knowledgeMap);
            }
        }
        return knowledgeMap;
    }
}
