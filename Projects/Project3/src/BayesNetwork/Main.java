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
////        String d  = Arrays.toString(data);
////        String[] str = d.split(" ");
////        System.out.println(Arrays.toString(str));
//        TreeMap<String, ArrayList<String>> dat = Reader.getVariables(file);
//        System.out.println(dat);
//        Map<String, ArrayList<String>> probs = Reader.getProbabilities(file, dat);
//        System.out.println(probs);

//        HashMap<String, ArrayList> testMap = BayesNet.setProbabilities(file);
//        for (Map.Entry<String, ArrayList> item: testMap.entrySet()){
//            System.out.println("key");
//            System.out.println(item.getKey());
//            for(Object o: item.getValue()){
//                System.out.println(o);
//            }
//        }
//        TreeMap<String, ArrayList<String>> dat = Reader.getVariables(data);
//        System.out.println(dat);
//        Map<String, ArrayList<String>> probs = Reader.getProbabilities(data);
//        System.out.println(probs.values());
//        Hashtable<String, Hashtable> p = new Hashtable<>();
//        p = BayesNet.setProbabilities(dat, probs);
//        System.out.println(p);
//
//        Map<String, ArrayList<String>> prob = Reader.getProbabilities(data);
//        System.out.println(prob);

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
