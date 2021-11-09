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


        System.out.println(data);
//        String d  = Arrays.toString(data);
//        String[] str = d.split(" ");
//        System.out.println(Arrays.toString(str));
        TreeMap<String, ArrayList<String>> dat = Reader.getVariables(file);
        System.out.println(dat);
        Map<String, ArrayList<String>> probs = Reader.getProbabilities(file, dat);
        System.out.println(probs);

        //below is test code for variable elimination on the earthquake network
        BayesNet earthquake = ExactTest.returnEarthquakeNet();

        //given the evidence that John and Mary both called
        ArrayList<String> evidence = new ArrayList<>();
        evidence.add("JohnCalls");
        evidence.add("MaryCalls");

        ArrayList<String> evidenceStates = new ArrayList<>();
        evidenceStates.add("True");
        evidenceStates.add("True");

        HashMap<String, ArrayList<Double>> evidenceFactors = Exact.variableElimination(earthquake, "Burglary", evidence, evidenceStates );
        for (Map.Entry<String, ArrayList<Double>> item: evidenceFactors.entrySet()){
            System.out.print(item.getKey() + " ");
            for (double d : item.getValue()) {
                System.out.print(d);
            }
            System.out.println("");
        }
    }

}
