package BayesNetwork;

import java.util.*;

public class BayesNet {
    public String networkName;
    public ArrayList<Variable> variables = new ArrayList<Variable>();


    public BayesNet(String network, ArrayList<Variable> var){
        this.networkName = network;
        this.variables = var;
    }

    // get the contents of all the variables in the network
    public ArrayList<Variable> getVariables() {
        return variables;
    }

    // gets the name of the network
    public String getNetworkName() {
        return networkName;
    }

}