package RaceTrack;

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
        HashMap<Character, Integer> knowledge = new HashMap();
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                // set state as unknown and action as 0.
                char state = 'U';
                int action = 0;
                knowledge.put(state, action);
            }
        }
        return knowledge;
    }

    // once position has been explored and car has not crashed, update knowledge
    public HashMap updateQTable(HashMap matrix, int[] position, int action, char state){
        int x = position[0];
        int y = position[1];
        matrix.put(state, action);
        return matrix;
    }

    // if grid space is unknown, move to the space and update the state and optimal action for that position
    public void explore(int[] position, HashMap knowledge) {
        int action = 0;
        if (position[0] == 0.0 || position[1] == 0.0) {
//            int action = decision(state, reward, position, knowledge);
            if (this.state == 'R' || this.state == 'S') {
                action = 1;
            } else {
                action = -1;
            }
            updateQTable(knowledge, position, action, state);
        }
    }


    public int costFunction(int moves) {
        int updatedMoves = moves + 1;
        return updatedMoves;
    }

    public int decision(char state, double currentMoveReward, int[] position, HashMap knowledge) {
        int[] acts = getActions();
        // continue at current speed going straight
        int action = 0;
        // let the session continue as long as the current state is not 'W'
        boolean done = false;
        int count = 0;
        while (!done) {
            // while state is not terminal, continue on track
            while ((state == 'S' || state == 'R') && currentMoveReward >= 0.01) {
                explore(position, knowledge);
                count = costFunction(count);
                currentMoveReward = currentMoveReward - 0.001;
                if (currentMoveReward == 0.1 ) {
                    break;
                }
                if (count == 100000) {
                    break;
                }

                double x = position[0];
                double y = position[1];
            }

        }
        return action;
    }

    public static void main(String[] args) {
        int[] p = {0,1};
        int moves = 0;
        int x = 10;
        int y = 15;
        System.out.println("Something Is happening");
        HashMap matrix;
        QLearning ql = new QLearning('S', 1, p);
        matrix = ql.initializeQTable(x, y);
//        ql.explore(p, matrix);
        ql.decision(ql.state, ql.reward, p, matrix);
        int r = ql.costFunction(moves);

    }
}
