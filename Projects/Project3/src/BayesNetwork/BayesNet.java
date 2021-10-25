package BayesNetwork;

import java.util.HashMap;

public class BayesNet {
    String networkName;
    double[] properties;
    HashMap probabilities = new HashMap();

    public BayesNet(String network, double[] prop, HashMap prob){
        this.networkName = network;
        this.properties = prop;
        this.probabilities = prob;
    }

    public void setProperties(double[] properties){

    }
}
