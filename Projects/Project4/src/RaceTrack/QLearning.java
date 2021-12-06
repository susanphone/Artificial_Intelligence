package RaceTrack;

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

    public char[][] initializeQTable(int xSize, int ySize) {
        char[][] matrix = new char[xSize][ySize];
        for (int x = 0; x < matrix[xSize].length; x++) {
            for (int y = 0; y < matrix[ySize].length; y++) {
                // set state as unknown and action as 0.
                char state = 'U';
                int action = 0;
                matrix = new char[state][action];
            }
        }
        return matrix;
    }

    // once position has been explored and car has not crashed, update knowledge
    public char updateQTable(char[][] matrix, int[] position, int action, char state){
        int x = position[0];
        int y = position[1];
        char m, n;
        m = state;
        n = (char) action;
        char matrix1 = matrix[m][n];
        return matrix1;
    }

    // if grid space is unknown, move to the space and update the state and optimal action for that position
    public void explore(int[] position, char[][] knowledge) {
        if (position[0] == 0.0 || position[1] == 0.0) {
            int action = decision(state, reward, position, knowledge);
            updateQTable(knowledge, position, action, state);

        }
    }


    public int costFunction(int moves) {
        int updatedMoves = moves + 1;
        return updatedMoves;
    }

    public int decision(char state, int reward, int[] position, char[][] knowledge) {
        int[] acts = getActions();
        // continue at current speed going straight
        int action = 0;
        // let the session continue as long as the current state is not 'W'
        boolean done = false;
        double currentMoveReward = 1.0;
        while (!done) {
            // while state is not terminal, continue on track
            while ((state != 'w' || state != 'g') && currentMoveReward <= 0.01) {
                explore(position, knowledge);
                currentMoveReward = reward - 0.001;
                if (currentMoveReward == 0.1) {
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
        QLearning ql = new QLearning('S', 1, p);
        char[][] matrix = ql.initializeQTable(x, y);
        ql.explore(p, matrix);
        ql.decision(ql.state, ql.reward, p, matrix);
        int r = ql.costFunction(moves);

    }
}
