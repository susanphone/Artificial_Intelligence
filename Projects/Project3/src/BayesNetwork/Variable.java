package BayesNetwork;

import java.util.HashMap;
import java.util.ArrayList;
public class Variable {
    public String name;
    public String[] states;
    public String[] parents;
    public String[] children;
    public HashMap<String, ArrayList<Double>> probabilities = new HashMap<>();

    public Variable(String name, String[] s, String[] p, String[]c){
        this.name = name;
        this.states = s;
        this.parents = p;
        this.children = c;
    }
}
