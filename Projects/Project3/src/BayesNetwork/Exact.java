package BayesNetwork;

import java.util.HashMap;
import java.util.Arrays;
public class Exact {

    public Exact(BayesNet currNet, String q, String[] observed){
        BayesNet currentNet = currNet;
        String query = q;
        String[] observations = observed;
    }
//    Variable Elimination
    public double variableElimination(BayesNet currentNet, String query, String[] observed){
    // returns double
        double[] factors = new double[currentNet.variables.size()];
        String[] varNames = new String[currentNet.variables.size()];

        //fill in the array of variables
        int i = 0;
        for(String key : currentNet.variables.keySet()){
            varNames[i] = key;
            i++;
        }
        //sort the array
        Arrays.sort(varNames);

        for(String var: varNames){
            factors = makeFactors(var, observed, factors);

            if(!var.equals(query)){
                factors = sumOut(var, factors);
            }
        }
        double c = pointwiseProduct(factors);
        c = normalize(c);
        return c;
    }

    public double pointwiseProduct(double[] factors){
        //returns double
        double c = 0.0;
        return c;
    }

    public double normalize(double c){
        // returns double
        return c;

    }

    public double[] makeFactors(String var, String[] e, double[] f){
        return f;
    }

    public double[] sumOut(String v, double[] f){
        return f;
    }

}
