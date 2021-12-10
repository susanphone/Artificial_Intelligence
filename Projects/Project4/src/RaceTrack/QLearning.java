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
        HashMap<Character, Integer> knowledge = new HashMap<>();
        char state = 'U';
        int action = 0;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                // set state as unknown and action as 0.
                knowledge.put(state, action);
                continue;
            }
        }
        return knowledge;
    }

    // once position has been explored and car has not crashed, update knowledge
    public HashMap updateQTable(HashMap matrix, int action, char state){
        matrix.put(state, action);
        return matrix;
    }

    // if grid space is unknown, move to the space and update the state and optimal action for that position
    public HashMap explore(int[] position, HashMap knowledge) {
        int action = 0;
        if (position[0] == 0.0 || position[1] == 0.0) {
//            int action = decision(state, reward, position, knowledge);
            if (this.state == 'R' || this.state == 'S') {
                action = 1;
            } else {
                action = -1;
            }
            knowledge = updateQTable(knowledge, action, state);
        }
        return knowledge;
    }


    public int costFunction(int moves) {
        int updatedMoves = moves + 1;
        return updatedMoves;
    }

    public int[] decision(char state, double currentMoveReward, HashMap knowledge) {
        int[] pos = this.position;
        int[] acts = getActions();
        // continue at current speed going straight
        int action = 0;
        int x = pos[0];
        int y = pos[1];
        // let the session continue as long as the current state is not 'W'
        boolean done = false;
        int count = 0;
        while (!done) {
            // while state is not terminal, continue on track
            while ((state == 'S' || state == 'R') && currentMoveReward >= 0.01) {
                knowledge = explore(pos, knowledge);
                count = costFunction(count);
                currentMoveReward = currentMoveReward - 0.001;
                if (currentMoveReward == 0.1 ) {
                    break;
                }
                if (count == 10) {
                    break;
                }


            }

        }
        pos[0] = x+1;
        pos[1] = y;
        return pos;
    }

    public static void main(String[] args) {
        int moves = 0;
        int x = 10;
        int y = 15;
        int[] p = {0, 0};
        System.out.println("Something Is happening");
        HashMap matrix;
        QLearning ql = new QLearning('S', 1, p);
        matrix = ql.initializeQTable(x, y);
//        ql.explore(p, matrix);
        p = ql.decision(ql.state, ql.reward, matrix);
        int r = ql.costFunction(moves);
        System.out.println(Arrays.toString(p));

    }
}
