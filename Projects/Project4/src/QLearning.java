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

    public int decision(char[] states, int reward, double[] position){
        int[] acts = getActions();
        int action = 0;
        int possibleReward = states.length * acts.length;
        double learningRatio = 0.1;
        double discount = 0.99;
        QLearning q = new QLearning(this.state, reward, position);
        boolean converges = false;
        while (!converges) {
            for (char state : states) {
                double currentMoveReward = 1.0;
                // while state is not terminal, continue on track
                while ((state != 'w' || state != 'g') && currentMoveReward <= 0.01) {

                    // max_0.1 Q(state, action)
                    currentMoveReward -= 0.001;
                    double x = position[0];
                    double y = position[1];
                    for (int a : acts) {
                        if (x + 1 != 'w' && y+1 == 'w'){
                            // move east
//                            Car.accelerate(1, 0);
                            return a;
                        }
                        if (x + 1 == 'w' && y + 1 != 'w') {
                            // move east, slight north
//                            Car.accelerate(-1, 1);
                            return a;
                        }
                        if (y + 1 != 'w' && x + 1 != 'w') {
                            // move north-east
//                            Car.accelerate(1, 1);
                            return a;
                        }
                        if (y + 1 == 'w' && x - 1 != 'w') {
                            // move west, slight south
//                            Car.accelerate(1,-1);
                            return a;
                        }
                        if (y-1 == 'w' && x - 1 == 'w') {
                            // move south-west
//                            Car.accelerate(-1, -1);
                            return a;
                        }
                        if (y-1 != 'w' && x-1 == 'w') {
                            // move north, slight west
//                            Car.accelerate(-1, 1);
                            return a;
                        }
                        if (y-1 != 'w' && x-1 != 'w') {
                            // move north-west
//                            Car.accelerate(1, 1);
                            return a;
                        }



                    }
                }
            }
        }
        return action;
    }

}
