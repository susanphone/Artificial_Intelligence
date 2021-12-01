public class QLearning {

    char state;
    int reward;
    double[] position;

    public QLearning(char s, int r, double[] p){
        this.state = s;
        this.reward = r;
        this.position = p;
    }

    public int costFunction(){
        int t = 0;
        return t;
    }

    public double[] decision(char state, int reward, double[] position){
        return position;
    }

}
