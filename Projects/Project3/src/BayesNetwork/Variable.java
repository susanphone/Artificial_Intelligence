package BayesNetwork;

import java.util.ArrayList;
import java.util.HashMap;

public class Variable {
    public String name;
    public String[] states;
    public String[] parents;
    public String[] children;
    public HashMap<String, ArrayList<Double>> probabilities = new HashMap<>();

    public Variable(String name, String[] s, String[] p, String[] c){
        this.states = s;
        this.name = name;
        this.parents = p;
        this.children = c;
    }
}
