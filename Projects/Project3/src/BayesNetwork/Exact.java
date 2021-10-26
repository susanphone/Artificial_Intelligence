package BayesNetwork;

import java.util.HashMap;

public class Exact {
    String name;
    double[] prop;
    HashMap prob;
    BayesNet currentNet = new BayesNet(null, null, null);
    String query;
    String[] observations;
    public Exact(BayesNet currentNet, String query, String[] observed){
        this.currentNet = new BayesNet(name, prop, prob);
        this.query = query;
        this.observations = observed;
    }
//    Variable Elimination
    public void variableElimination(BayesNet currentNet, String query, String[] observed){
    // returns double
    }

    public void pointwiseProduct(double[] factors){
        //returns double
    }

    public void normalize(double c){
    // returns double
    }


}
