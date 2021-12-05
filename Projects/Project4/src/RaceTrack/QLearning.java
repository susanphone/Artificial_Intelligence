package RaceTrack;

public class QLearning {

    Knowledge
    int[] actions;
    char state;
    int reward;
    double[] position;

    public QLearning(char s, int r, double[] p) {
        this.state = s;
        this.reward = r;
        this.position = p;
    }

    private int[] getActions() {
        return actions;
    }

    public char updateState() {
        state = this.state;
        return state;
    }


    public void explore(double[] position, char[][] knowledge) {
        if (position[0] == 0.0 || position[1] == 0.0) {
            int action = decision(state, reward, position, knowledge);

        }
    }


    public int costFunction(int moves) {
//        int t = 0;
//        return t;
        int updatedMoves = moves + 1;
        return updatedMoves;
    }

    public int decision(char state, int reward, double[] position, char[][] knowledge) {
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
                for (int a : acts) {
                    if (x + 1 != 'w' && y + 1 == 'w') {
                        // move east
                        Car.accelerate(1, 0);
                        Knowledge.updateKnowledge(knowledge, position, a);
                        return a;
                    }
                    if (x + 1 == 'w' && y + 1 != 'w') {
                        // move east, slight north
                        Car.accelerate(-1, 1);
                        Knowledge.updateKnowledge(knowledge, position, a);
                        return a;
                    }
                    if (y + 1 != 'w' && x + 1 != 'w') {
                        // move north-east
                        Car.accelerate(1, 1);
                        Knowledge.updateKnowledge(knowledge, position, a);
                        return a;
                    }
                    if (y + 1 == 'w' && x - 1 != 'w') {
                        // move west, slight south
                        Car.accelerate(1, -1);
                        Knowledge.updateKnowledge(knowledge, position, a);
                        return a;
                    }
                    if (y - 1 == 'w' && x - 1 == 'w') {
                        // move south-west
                        Car.accelerate(-1, -1);
                        Knowledge.updateKnowledge(knowledge, position, a);
                        return a;
                    }
                    if (y - 1 != 'w' && x - 1 == 'w') {
                        // move north, slight west
                        Car.accelerate(-1, 1);
                        Knowledge.updateKnowledge(knowledge, position, a);
                        return a;
                    }
                    if (y - 1 != 'w' && x - 1 != 'w') {
                        // move north-west
                        Car.accelerate(1, 1);
                        Knowledge.updateKnowledge(knowledge, position, a);
                        return a;
                    }
                    if (this.state == 'w' || this.state == 'g'){
                        done = true;
                        break;
                    }
                }
            }
        }
        return action;
    }

    public static void main(String[] args) {
        double[] p = {0,1};
        int moves = 0;
        QLearning ql = new QLearning('S', 1, p);
        Knowledge k = new Knowledge();
        char[][] matrix = k.initializeKnowledge();
        ql.explore(p, matrix);
        char s = ql.updateState();
        int r = ql.costFunction(moves);

    }
}
