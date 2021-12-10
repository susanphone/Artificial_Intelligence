package RaceTrack;

import java.util.Arrays;
import java.util.HashMap;

public class QLearning {

    int[] actions;
    char state;
    int reward;
    int[] position;

    public QLearning(char s, int r, int[] p) {
        this.state = s;
        this.reward = r;
        this.position = p;
    }

    private int[] getActions() {
        return actions;
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
        if (this.state == 'R' || this.state == 'S') {
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
            if (done == true) {
                return state;
            }
        }
        pos[0] = x+1;
        pos[1] = y;
        return action;
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 15;
        int[] p = {0,0};
        QLearning ql = new QLearning('S', 1, p);
        HashMap matrix  = ql.initializeQTable(x, y);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++){
                p = new int[]{i, j};
                System.out.println("Something Is happening");
                char act = ql.decision(ql.state, ql.reward, matrix);
                System.out.println(Arrays.toString(p) + " " + act + ql.state);
            }
        }


    }
}
