package BayesNetwork;

import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("alarm.bif");
        ArrayList data;

        // Open a reader
        Reader reader =  new Reader(file);

        // Load the file, and remove some unwanted cruft
        reader.loadFile();
        ArrayList list = new ArrayList<>();
        ArrayList states = new ArrayList();
        // get a treemap of variables, keyed by name
        var variables = reader.getVariables();
        for (String l: variables.keySet()){
            list.add(l);
        }
        for (Variable m: variables.values()){
            states.add(m);
        }
        String[] a = new String[1];

        BayesNet alarm = new BayesNet("alarm", new ArrayList<Variable>(variables.values()));
<<<<<<< HEAD
//        Exact test = new Exact(alarm, "alarm", list, states);
        Approximate test2 = new Approximate();
//        Approximate.gibbs(alarm, variables.size(), list, states, a);
        //below is test code for variable elimination on the earthquake network
//        BayesNet earthquake = ExactTest.returnEarthquakeNet();
//
//        //given the evidence that John and Mary both called
//        ArrayList<String> evidence = new ArrayList<>();
//        evidence.add("JohnCalls");
//        evidence.add("MaryCalls");
//
//        ArrayList<String> evidenceStates = new ArrayList<>();
//        evidenceStates.add("True");
//        evidenceStates.add("True");
//
//        HashMap<String, ArrayList<Double>> evidenceFactors = Exact.variableElimination(earthquake, "Alarm", evidence, evidenceStates );
//        for (Map.Entry<String, ArrayList<Double>> item: evidenceFactors.entrySet()){
//            System.out.print(item.getKey() + " ");
//            for (double d : item.getValue()) {
//                System.out.print(d);
//            }
//            System.out.println("");
//        }
=======
//        Exact test = new Exact(alarm, "alarm", variables.keySet(), variables.values());

        //below is test code for variable elimination on the earthquake network
        BayesNet earthquake = ExactTest.returnEarthquakeNet();

        //given the evidence that John and Mary both called
        //to test on other networks, update the below add() calls to whatever variables will be provided as evidence
        ArrayList<String> evidence = new ArrayList<>();
        evidence.add("JohnCalls");
        evidence.add("MaryCalls");

        //add to the evidence states list the state of the variables in the order that corresponds to the evidence list
        ArrayList<String> evidenceStates = new ArrayList<>();
        evidenceStates.add("True");
        evidenceStates.add("True");

        //for the gibbs sampling change the name of the variable to query in the e[] array
        String[] e = new String[1];
        e[0] = "Earthquake";

        //replace 'earthquake' with the name of the BayesNet object
        Approximate.gibbs(earthquake, 100000, evidence, evidenceStates, e);

        System.out.println("Running variable elimination: ");
        System.out.println();
        //replace 'earthquake' with the name of the BayesNet object and "Alarm" with the name of the query variable
        HashMap<String, ArrayList<Double>> evidenceFactors = Exact.variableElimination(earthquake, "Earthquake", evidence, evidenceStates );
        //this will print out the distribution of the query variable for the variable elimination algorithm
        System.out.println("The resulting distribution from variable elimination for query variable " + e[0] + ":");
        for (Map.Entry<String, ArrayList<Double>> item: evidenceFactors.entrySet()){
            String[] stateLabels = item.getKey().split(" ");
            System.out.print(stateLabels[0] + " ");
            for (double d : item.getValue()) {
                System.out.print(d);
            }
            System.out.println("");
        }
>>>>>>> aadb55ec2b71b1d98af58b3d3a6815bf72ca1048
    }

}
