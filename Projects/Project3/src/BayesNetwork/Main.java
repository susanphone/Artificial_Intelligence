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

        BayesNet net = new BayesNet("alarm", new ArrayList<>(variables.values()));

        //below is test code for variable elimination on the earthquake network
        BayesNet earthquake = ExactTest.returnEarthquakeNet();

        //given the evidence that John and Mary both called
        ArrayList<String> evidence = new ArrayList<>();
        evidence.add("JohnCalls");
        evidence.add("MaryCalls");

        ArrayList<String> evidenceStates = new ArrayList<>();
        evidenceStates.add("False");
        evidenceStates.add("False");

        HashMap<String, ArrayList<Double>> evidenceFactors = Exact.variableElimination(earthquake, "Alarm", evidence, evidenceStates );
        for (Map.Entry<String, ArrayList<Double>> item: evidenceFactors.entrySet()){
            System.out.print(item.getKey() + " ");
            for (double d : item.getValue()) {
                System.out.print(d);
            }
            System.out.println("");
        }
    }

}
