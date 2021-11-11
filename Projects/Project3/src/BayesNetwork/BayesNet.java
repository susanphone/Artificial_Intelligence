package BayesNetwork;

import java.util.*;

public class BayesNet {
    public String networkName;
    public ArrayList<Variable> variables = new ArrayList<Variable>();

    public BayesNet(String network, ArrayList<Variable> var){
        this.networkName = network;
        this.variables = var;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public String getNetworkName() {
        return networkName;
    }


    }
