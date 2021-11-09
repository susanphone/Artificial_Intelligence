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
        data = Reader.cleanUpFile(file);


//        System.out.println(data);
        TreeMap<String, ArrayList<String>> dat = Reader.getVariables(data);
        System.out.println(dat);
        Map<String, ArrayList<String>> probs = Reader.getProbabilities(data);
        System.out.println(probs.values());
        Hashtable<String, Hashtable> p = new Hashtable<>();
        p = BayesNet.setProbabilities(dat, probs);
        System.out.println(p);


//        BayesNet earthquake = ExactTest.returnEarthquakeNet();
//
//        for (Variable v : earthquake.variables) {
//            System.out.println(v.name);
//            System.out.println("Parents: " + Arrays.toString(v.parents));
//            System.out.println("Children: " + Arrays.toString(v.children));
//            System.out.println("Probability Distribution: ");
//            for (Map.Entry<String, ArrayList<Double>> item: v.probabilities.entrySet()) {
//                System.out.print(item.getKey() + " ");
//                System.out.println(Arrays.toString(item.getValue().toArray()));
//            }
//            System.out.println("");
//        }
//        ArrayList<String> evidence = new ArrayList<>();
//        evidence.add("JohnCalls");
//        evidence.add("MaryCalls");
//
//        ArrayList<String> evidenceStates = new ArrayList<>();
//        evidenceStates.add("True");
//        evidenceStates.add("True");
//
//        HashMap<String, ArrayList<Double>> evidenceFactors = Exact.variableElimination(earthquake, "burglary", evidence, evidenceStates );

    }

}
