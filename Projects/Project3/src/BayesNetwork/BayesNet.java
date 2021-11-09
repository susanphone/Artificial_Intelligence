package BayesNetwork;

import java.util.*;

public class BayesNet {
    private static ArrayList<Variable> variables;
    public String networkName;
    public double[] properties;
//    public ArrayList<Variable> variables = new ArrayList<Variable>();

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

    public static Hashtable setProbabilities(TreeMap vars, Map probs) {
        HashMap<String, ArrayList> problemMap = new HashMap<>();
        Hashtable<String, HashMap<String, ArrayList>> probabilitiesMap = new Hashtable<>();
        ArrayList<String> probabilities = new ArrayList<>();
        System.out.println(probs.values());
        Iterator<Map.Entry<String, ArrayList<String>>> itr = probs.entrySet().iterator();
        Set k = probs.keySet();
        while (itr.hasNext()) {
            Object key = null;
            for (Object s: k) {
                key = s;
                System.out.println(key);
                Map.Entry<String, ArrayList<String>> entry = itr.next();
                ArrayList<String> j = entry.getValue();
                String state = null;
                for (int i = 0; i < j.size(); i++) {
                    System.out.println(j.get(i));
                    if (j.get(i).startsWith("(")) {
                        state = j.get(i);
                        i++;
                    } else {
                        probabilities.add(j.get(i));
                        i++;
                    }
                    problemMap.put(state, probabilities);
                    state = null;
                    probabilities = new ArrayList<>();
                    System.out.println(problemMap);
                }
                probabilitiesMap.put((String) key, problemMap);
                problemMap = new HashMap<>();

            }
        }
            return probabilitiesMap;
        }
    }