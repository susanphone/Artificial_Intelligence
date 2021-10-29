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

        for(String var: varNames){
            factors = makeFactors(var, observed, factors);

            if(!var.equals(query) && !isInEvidence(var, observed)){
                factors = sumOut(var, factors);
            }
        }
        double c = pointwiseProduct(factors);
        c = normalize(c);
        return c;
    }

    public double pointwiseProduct(double[] factors){
        //returns double
        double c = factors[0];
        for(int i = 1; i < factors.length; i++){
            c = c * factors[i];
        }
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
