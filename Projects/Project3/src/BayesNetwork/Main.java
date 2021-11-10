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

        // get a treemap of variables, keyed by name
        var variables = reader.getVariables();

        BayesNet alarm = new BayesNet("alarm", new ArrayList<Variable>(variables.values()));
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
        e[0] = "Alarm";

        //replace 'earthquake' with the name of the BayesNet object
        Approximate.gibbs(earthquake, 100000, evidence, evidenceStates, e);

        //replace 'earthquake' with the name of the BayesNet object and "Alarm" with the name of the query variable
        HashMap<String, ArrayList<Double>> evidenceFactors = Exact.variableElimination(earthquake, "Alarm", evidence, evidenceStates );
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
    }

}
