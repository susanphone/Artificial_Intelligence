package BayesNetwork;

import java.util.HashMap;

public class BayesNet {
    String networkName;
    double[] properties;
    HashMap variables = new HashMap();

    public BayesNet(String network, double[] prop, HashMap var){
        this.networkName = network;
        this.properties = prop;
        this.variables = var;
    }

    public void setProperties(double[] properties){

    }
}
