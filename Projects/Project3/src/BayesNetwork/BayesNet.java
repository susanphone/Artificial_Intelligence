package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    public static HashMap setProbabilities(File file) throws FileNotFoundException {
        HashMap<String, ArrayList> probabilitiesMap = new HashMap<>();
        ArrayList<String> probabilities = new ArrayList<>();
        TreeMap vars = Reader.getVariables(file);
        Map probs = Reader.getProbabilities(file, vars);
        System.out.println(probs.values());
        Iterator<Map.Entry<String, ArrayList<String>>> itr = probs.entrySet().iterator();

        while(itr.hasNext()) {
            Map.Entry<String, ArrayList<String>> entry = itr.next();
            System.out.println("Value = " + entry.getValue());
            ArrayList<String> j = entry.getValue();
            String state = null;
            for (int i = 0; i < j.size(); i++) {
                System.out.println(j.get(i));
                state = j.get(0);
                while (j.get(i).contains("0.*")) {
                    probabilities.add(j.get(i));
                    i++;
                }
            }
        }
        return probabilitiesMap;
    }
}