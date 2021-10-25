package BayesNetwork;

import java.util.HashMap;

public class Approximate {
    String name;
    double[] prop;
    HashMap prob;
    BayesNet currentNet = new BayesNet(null, null, null);
    String query;
    String[] observations;


    public Approximate(BayesNet currentNet, String query, String[] observed){
        this.currentNet = new BayesNet(name, prop, prob);
        this.query = query;
        this.observations = observed;
    }

    public void gibbs(BayesNet currentNet, String query, String[] observed){

    }
}
