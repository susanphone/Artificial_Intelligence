package BayesNetwork;

import com.sun.jdi.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BayesNet {
    public String networkName;
    public double[] properties;
    public ArrayList<Variable> variables = new ArrayList<Variable>();

    public BayesNet(String network, double[] prop, ArrayList var){
        this.networkName = network;
        this.properties = prop;
        this.variables = var;
    }

    public double[] getProperties() {
        return properties;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public String getNetworkName() {
        return networkName;
    }
    public HashMap setProbabilities(File file) throws FileNotFoundException {
        HashMap<String, double[]> probabilitiesMap = new HashMap<>();
        double [] probabilities = new double[0];
        TreeMap vars = Reader.getVariables(file);
        Map probs = Reader.getProbabilities(file, vars);
        for (Object v: probs.values()) {
            while (v) {
                String state = v[0];
                if (v.equals(type(double))) {
                    probabilities = probabilities + v;
                }
                probabilitiesMap.put(state, probabilities);
            }
        }
        return probabilitiesMap;
    }
}