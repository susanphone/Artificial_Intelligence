package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

public class BayesNet {
    String networkName;
    Map properties;
    TreeMap variables = new TreeMap();

    public BayesNet(String network, Map prop, TreeMap var){
        this.networkName = network;
        this.properties = prop;
        this.variables = var;
    }

    public void setProperties(Map properties) throws FileNotFoundException {
        File file = new File("alarm.bif");
        variables = Reader.getVariables(file);
        Reader.getProbabilities(file, variables);
    }
}
