package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class BayesNet {
    String networkName;
    double[] properties;
    ArrayList<NewVariable> variables = new ArrayList<NewVariable>();

    public BayesNet(String network, double[] prop, ArrayList var){
        this.networkName = network;
        this.properties = prop;
        this.variables = var;
    }
}
