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
    }

}
