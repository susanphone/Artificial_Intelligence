package BayesNetwork;

import java.util.*;

//creation of the earthquake network for testing purposes
//reference the earthquake.bif file
public class ExactTest {

    public static void main(String[] args) {

        //create the properties array for the BayesNet
        //properties are not really needed
        double[] properties = new double[] {5.0, 4.0, 10.0, 1.60, 2.0, 2.0};

        //create an empty Array to store all the variable objects
        ArrayList<Variable> variables = new ArrayList<>();

        //manually create each variable

        //starting with the burglary variable
        String[] states = new String[] {"True", "False"};
        ArrayList<Double> probs1 = new ArrayList<>();
        ArrayList<Double> probs2 = new ArrayList<>();

        //create a hashmap for the first prob distribution
        //have to use a new hashmap per distribution because of how Java stores vars
        HashMap<String, ArrayList<Double>> probsHashMap1 = new HashMap<>();
        probs1.add(0.01);
        probsHashMap1.put("True", probs1);

        probs2.add(0.99);
        probsHashMap1.put("False", probs2);

        String[] bp = new String[] {};
        String[] bc = new String[] {"Alarm"};
        Variable burglary = new Variable("Burglary", states, bp, bc );
        burglary.probabilities = probsHashMap1;

        //repeat process for all variables
        ArrayList<Double> probs3 = new ArrayList<>();
        ArrayList<Double> probs4 = new ArrayList<>();
        HashMap<String, ArrayList<Double>> probsHashMap2 = new HashMap<>();
        probs3.add(0.02);
        probsHashMap2.put("True", probs3);

        probs4.add(0.98);
        probsHashMap2.put("False", probs4);

        String[] ep = new String[] {};
        String[] ec = new String[] {"Alarm"};
        Variable earthquake1 = new Variable("Earthquake", states, ep, ec );
        earthquake1.probabilities = probsHashMap2;

        ArrayList<Double> probs5 = new ArrayList<>();
        ArrayList<Double> probs6 = new ArrayList<>();
        ArrayList<Double> probs7 = new ArrayList<>();
        ArrayList<Double> probs8 = new ArrayList<>();
        HashMap<String, ArrayList<Double>> probsHashMap3 = new HashMap<>();

        probs5.add(0.95);
        probs5.add(0.05);
        probsHashMap3.put("True True", probs5);

        probs6.add(0.29);
        probs6.add(0.71);
        probsHashMap3.put("False True", probs6);

        probs7.add(0.94);
        probs7.add(0.06);
        probsHashMap3.put("True False", probs7);

        probs8.add(0.001);
        probs8.add(0.999);
        probsHashMap3.put("False False", probs8);

        String[] ap = new String[] {"Burglary", "Earthquake"};
        String[] ac = new String[] {"JohnCalls", "MaryCalls"};
        Variable alarm = new Variable("Alarm", states, ap, ac );
        alarm.probabilities = probsHashMap3;

        ArrayList<Double> probs9 = new ArrayList<>();
        ArrayList<Double> probs10 = new ArrayList<>();
        HashMap<String, ArrayList<Double>> probsHashMap4 = new HashMap<>();

        probs9.add(0.9);
        probs9.add(0.1);
        probsHashMap4.put("True", probs9);

        probs10.add(0.05);
        probs10.add(0.95);
        probsHashMap4.put("False", probs10);

        String[] jp = new String[] {"Alarm"};
        String[] jc = new String[] {};
        Variable johnCalls = new Variable("JohnCalls", states, jp, jc );
        johnCalls.probabilities = probsHashMap4;

        ArrayList<Double> probs11 = new ArrayList<>();
        ArrayList<Double> probs12 = new ArrayList<>();
        HashMap<String, ArrayList<Double>> probsHashMap5 = new HashMap<>();

        probs11.add(0.7);
        probs11.add(0.3);
        probsHashMap5.put("True", probs11);

        probs12.add(0.01);
        probs12.add(0.99);
        probsHashMap5.put("False", probs12);

        String[] mp = new String[] {"Alarm"};
        String[] mc = new String[] {};
        Variable maryCalls = new Variable("MaryCalls", states, mp, mc );
        maryCalls.probabilities = probsHashMap5;

        variables.add(burglary);
        variables.add(earthquake1);
        variables.add(alarm);
        variables.add(johnCalls);
        variables.add(maryCalls);

        //create a BayesNet object out of the variables
        BayesNet earthquake = new BayesNet("earthquake", properties, variables);
        System.out.println("Properties: " + Arrays.toString(properties));
        for (Variable v : variables) {
            System.out.println(v.name);
            System.out.println("Parents: " + Arrays.toString(v.parents));
            System.out.println("Children: " + Arrays.toString(v.children));
            System.out.println("Probability Distribution: ");
            for (Map.Entry<String, ArrayList<Double>> item: v.probabilities.entrySet()) {
                System.out.print(item.getKey() + " ");
                System.out.println(Arrays.toString(item.getValue().toArray()));
            }
            System.out.println("");
        }
        ArrayList<String> evidence = new ArrayList<>();
        evidence.add(johnCalls.name);
        evidence.add(maryCalls.name);

        ArrayList<String> evidenceStates = new ArrayList<>();
        evidenceStates.add("True");
        evidenceStates.add("True");

        HashMap<String, ArrayList<Double>> evidenceFactors = Exact.variableElimination(earthquake, burglary.name, evidence, evidenceStates );

//        ArrayList<Variable> factors = new ArrayList<>();
//        factors.add(johnCalls);
//        factors.add(maryCalls);
//        System.out.println("Summing Out Alarm");
//        ArrayList<Variable> newFactors = new ArrayList<>();
//        newFactors = Exact.sumOut(alarm, factors);
//        for(Variable f: newFactors){
//            for (Map.Entry<String, ArrayList<Double>> item: f.probabilities.entrySet()) {
//                System.out.print(item.getKey() + " ");
//                System.out.println(Arrays.toString(item.getValue().toArray()));
//            }
//        }
//        HashMap<String, ArrayList<Double>> normalizedFactors = Exact.normalize(newFactors);
//        System.out.println("Pointwise Product: ");
//        Variable product = Exact.pointwiseProduct(maryCalls, factors, alarm);
//        System.out.println(product.name);
//        for (Map.Entry<String, ArrayList> item: product.probabilities.entrySet()) {
//            System.out.print(item.getKey() + " ");
//            System.out.println(Arrays.toString(item.getValue().toArray()));
//        }
    }
}
