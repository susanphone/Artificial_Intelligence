package BayesNetwork;

import java.util.HashMap;

public class Variable {
    public String states;
    public HashMap probabilities;

    public Variable(String s, HashMap prob){
        this.states = s;
        this.probabilities = prob;
    }
}
