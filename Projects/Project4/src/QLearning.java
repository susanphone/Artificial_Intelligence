public class QLearning {

    int[] actions;
    char state;
    int reward;
    double[] position;

    public QLearning(char s, int r, double[] p){
        this.state = s;
        this.reward = r;
        this.position = p;
    }
    private int[] getActions(){
        return actions;
    }

    public int costFunction(int moves){
//        int t = 0;
//        return t;
        int updatedMoves = moves + 1;
        return updatedMoves;
    }

    public double[] decision(int[] actions, char[] states, int reward, double[] position){
        int r = states.length * actions.length;
        double learningRatio = 0.1;
        double discount = 0.99;
        QLearning q = new QLearning(state, reward, position);
        boolean converges = false;
        while (!converges) {
            for (char state : states) {
                double currentMoveReward = 1.0;
                // while state is not terminal, continue on track
                while ((!state.equals("w") || state != "g") && currentMoveReward <= 0.01) {
                    // max_0.1 Q(state, action)
                    currentMoveReward -= 0.001;

                }
            }
        }
        // return q;
        return position;
    }

}
