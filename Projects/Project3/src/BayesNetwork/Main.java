package BayesNetwork;


import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("alarm.bif");
        File file2 = new File("child.bif");
        File file3 = new File("hailfinder.bif");
        File file4 = new File("insurance.bif");
        File file5 = new File("win95pts.bif");

        // Opens a reader
        Reader reader =  new Reader(file);
        Reader reader2 = new Reader(file2);
        Reader reader3 = new Reader(file3);
        Reader reader4 = new Reader(file4);
        Reader reader5 = new Reader(file5);

        // Loads the file, and removes some unwanted characters
        reader.loadFile();
        reader2.loadFile();
        reader3.loadFile();
        reader4.loadFile();
        reader5.loadFile();

        // get an arraylist of variables
        var variables = reader.getVariables();
        var variables2 = reader2.getVariables();
        var variables3 = reader3.getVariables();
        var variables4 = reader4.getVariables();
        var variables5 = reader5.getVariables();




//      Alarm Network
        BayesNet alarm = new BayesNet("alarm", (ArrayList<Variable>) variables);
        ArrayList<String> evid1 = new ArrayList<>();
        evid1.add("HRBP");
        evid1.add("CO");
        evid1.add("BP");

        ArrayList<String> evidStates1 = new ArrayList<>();
        evidStates1.add("HIGH");
        evidStates1.add("LOW");
        evidStates1.add("HIGH");

        String [] queries1 = {"HYPOVOLEMIA", "LVFAILURE", "ERRLOWOUTPUT"};
        Approximate.gibbs(alarm, 10000, evid1, evidStates1, queries1);

        ArrayList<String> modEvid1 = new ArrayList<>();
        modEvid1.add("HRBP");
        modEvid1.add("CO");
        modEvid1.add("BP");
        modEvid1.add("HRSAT");
        modEvid1.add("HREKG");
        modEvid1.add("HISTORY");

        ArrayList<String> modEvidStates1 = new ArrayList<>();
        modEvidStates1.add("HIGH");
        modEvidStates1.add("LOW");
        modEvidStates1.add("HIGH");
        modEvidStates1.add("LOW");
        modEvidStates1.add("LOW");
        modEvidStates1.add("TRUE");

        Approximate.gibbs(alarm, 10000, modEvid1, modEvidStates1, queries1);


//        Child Network
        BayesNet child = new BayesNet("Child", variables2);
        ArrayList<String> evidC = new ArrayList<>();
        evidC.add("LowerBodyO2");
        evidC.add("RUQO2");
        evidC.add("CO2Report");
        evidC.add("XrayReport");

        ArrayList<String> evidStateC = new ArrayList<>();
        evidStateC.add("<5");
        evidStateC.add(">=12");
        evidStateC.add(">=7.5");
        evidStateC.add("Asy/Patchy");


        ArrayList<String> modEvidStateC = new ArrayList<>();
        modEvidStateC.add("<5;");
        modEvidStateC.add(">=12");
        modEvidStateC.add(">=7.5");
        modEvidStateC.add("Asy/Patchy");
        modEvidStateC.add("Yes");
        modEvidStateC.add("Yes");
        modEvidStateC.add("11-30 Days");

        ArrayList<String> modEvidC = new ArrayList<>();
        modEvidC.add("LowerBodyO2");
        modEvidC.add("RUQO2");
        modEvidC.add("CO2Report");
        modEvidC.add("XrayReport");
        modEvidC.add("GruntingReport");
        modEvidC.add("LVHReport");
        modEvidC.add("Age");

        String queries2[] = {"Disease"};
        Approximate.gibbs(child, 10000, evidC, evidStateC, queries2);
        Approximate.gibbs(child, 10000, modEvidC, modEvidStateC, queries2);


//        Hailfinder Network
        BayesNet hailfinder = new BayesNet("Hailfinder", variables3);

        ArrayList<String> evidH = new ArrayList<>();
        evidH.add("RSFcst");
        evidH.add("N32StarFcst");
        evidH.add("MountainFcst");
        evidH.add("AreaMoDryAir");

        ArrayList<String> modEvidH = new ArrayList<>();
        modEvidH.add("RSFcst");
        modEvidH.add("N32StarFcst");
        modEvidH.add("MountainFcst");
        modEvidH.add("CombVerMo");
        modEvidH.add("AreaMeso_ALS");
        modEvidH.add("CurPropConv");

        ArrayList<String> esH = new ArrayList<>();
        esH.add("XNIL");
        esH.add("XNIL");
        esH.add("XNIL");
        esH.add("VeryWet");

        ArrayList<String> modEsH = new ArrayList<>();
        modEsH.add("XNIL");
        modEsH.add("XNIL");
        modEsH.add("XNIL");
        modEsH.add("VeryWet");
        modEsH.add("Down");
        modEsH.add("Down");
        modEsH.add("Strong");

        String[] queries3 = {"SatContMoist", "LLIW"};
        Approximate.gibbs(hailfinder, 10000, evidH, esH, queries3);
        Approximate.gibbs(hailfinder, 10000, modEvidH, modEsH, queries3);




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
