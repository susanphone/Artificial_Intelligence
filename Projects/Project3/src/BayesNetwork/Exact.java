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
    public double[] variableElimination(BayesNet currentNet, String query, String[] observed){
    // returns double
        double[] factors = new double[currentNet.variables.size()];
        String[] varNames = new String[currentNet.variables.size()]; //since these vars are coming from a treemap
        //we should be able to extract them so that they are topographically sorted

        //fill in the array of variables
        int i = 0;
        for(String key : currentNet.variables.keySet()){
            varNames[i] = key;
            i++;
        }

        //every variable that is not an ancestor of the query variable or an evidence variable is
        //irrelevant to the query
        for(String var: varNames){
            factors = makeFactors(var, observed, factors);

            if(!var.equals(query) && !isInEvidence(var, observed)){
                factors = sumOut(var, factors);
            }
        }
        double[] c = pointwiseProduct(factors);
        c = normalize(c, observed);
        return c;
    }

    public double[] pointwiseProduct(double[] factors){
        //returns double
        double[] c = factors;
        for(int i = 1; i < factors.length; i++){
        }
        return c;
    }

    public double[] normalize(double[] c, String[] e ){
        // returns double
        int sumP = 0;

        for(double p : c){
            sumP += p;
        }

        double[] normalized_c = new double[c.length];
        int i = 0;
        for(double p : c){
            normalized_c[i] = c[i] / sumP;
            i++;
        }
        return c;

    }

    public double[] makeFactors(String var, String[] e, double[] f){

        //add var to the list of factors
        //return the updated list of factors
        return f;
    }

    public double[] sumOut(String v, double[] f){
        //marginalization
        //go through the factors and marginalize based on the evidence and what is known about the factors
        //i.e. remove any irrelevant factors
        return f;
    }

    public boolean isInEvidence(String v, String[] e){
        boolean isEvidence = false;
        for(String s : e){
            if(s.equals(v)){
                isEvidence = true;
            }
        }
        return isEvidence;
    }
}
