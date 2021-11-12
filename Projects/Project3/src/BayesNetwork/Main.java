package BayesNetwork;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("alarm.bif");
        ArrayList data;

        // Opens a reader
        Reader reader =  new Reader(file);

        // Loads the file, and removes some unwanted characters
        reader.loadFile();

        // get an arraylist of variables
        var variables = reader.getVariables();
        System.out.println(variables);
        BayesNet alarm = new BayesNet("alarm", (ArrayList<Variable>) variables);
        ArrayList<String> evid = new ArrayList<>();
        evid.add("HRBP");
        evid.add("CO");
        evid.add("BP");

        ArrayList<String> evidStates = new ArrayList<>();
        evidStates.add("HIGH");
        evidStates.add("LOW");
        evidStates.add("HIGH");

        String [] queries = {"HYPOVOLEMIA", "LVFAILURE", "ERRLOWOUTPUT"};
//        Approximate.gibbs(alarm, 10000, evid, evidStates, queries);

//        // prints out the contents of each variable in the network
//        System.out.println(alarm.getNetworkName());
//        alarm.getVariables().forEach((Variable v) -> {
//            System.out.println(v);
//        });
//
//        //below is test code for variable elimination on the earthquake network
//        BayesNet earthquake = ExactTest.returnEarthquakeNet();
//
//        //given the evidence that John and Mary both called
//        //to test on other networks, update the below add() calls to whatever variables will be provided as evidence
//        ArrayList<String> evid = new ArrayList<>();
//        evidence.add("JohnCalls");
//        evidence.add("MaryCalls");
//
//        //add to the evidence states list the state of the variables in the order that corresponds to the evidence list
//        ArrayList<String> evidStates = new ArrayList<>();
//        evidenceStates.add("True");
//        evidenceStates.add("True");
//
//        //for the gibbs sampling change the name of the variable to query in the e[] array
//        String[] e = new String[1];
//        e[0] = "Earthquake";
//
//
//
//
//
//        //replace 'earthquake' with the name of the BayesNet object
//        Approximate.gibbs(earthquake, 100000, evidence, evidenceStates, e);
//
//        System.out.println("Running variable elimination: ");
//        System.out.println();
//        //replace 'earthquake' with the name of the BayesNet object and "Alarm" with the name of the query variable
//        HashMap<String, ArrayList<Double>> evidenceFactors = Exact.variableElimination(earthquake, "Earthquake", evidence, evidenceStates );
//        //this will print out the distribution of the query variable for the variable elimination algorithm
//        System.out.println("The resulting distribution from variable elimination for query variable " + e[0] + ":");
//        for (Map.Entry<String, ArrayList<Double>> item: evidenceFactors.entrySet()){
//            String[] stateLabels = item.getKey().split(" ");
//            System.out.print(stateLabels[0] + " ");
//            for (double d : item.getValue()) {
//                System.out.print(d);
//            }
//            System.out.println("");
//        }
    }

}
