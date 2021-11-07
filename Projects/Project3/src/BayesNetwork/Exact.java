package BayesNetwork;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Exact {
    BayesNet currentNet;
    Variable query;
    ArrayList<Variable> observations;

    public Exact(BayesNet currNet, Variable q, ArrayList<Variable> observed){
        this.currentNet = currNet;
        this.query = q;
        this.observations = observed;
    }
//    Variable Elimination
    public ArrayList<Double> variableElimination(BayesNet currentNet, String query, ArrayList<String> evidence, ArrayList<String> evidenceStates){
    // returns double
        ArrayList<Variable> factors = new ArrayList<>();
        ArrayList<Variable> varOrder = new ArrayList<>(); //since these vars are coming from a treemap
        //we should be able to extract them so that they are topographically sorted

        //fill in the array of variables
        int i = 0;
        for(Variable v : currentNet.variables){
            varOrder.add(v);
        }

        //make new variables based on the evidence we receive
        ArrayList<Variable> observed = makeEvidenceFactors(evidence, evidenceStates, currentNet.variables);

        //every variable that is not an ancestor of the query variable or an evidence variable is
        //irrelevant to the query
        for(Variable var: varOrder){
            factors = makeFactors(var, factors, observed);
            if(!var.equals(query) && !observed.contains(var)){
                factors = sumOut(var, factors);
            }
        }
        ArrayList<Double> c = new ArrayList<>();
//        c = normalize(, observed);
        return c;
    }

    public static ArrayList<Variable> makeEvidenceFactors(ArrayList<String> evidence, ArrayList<String> evidenceStates, ArrayList<Variable> variables) {
        ArrayList<Variable> evidenceFactors = new ArrayList<>();

        for (int i = 0; i < evidence.size(); i++) {
            //get the given state
            String[] states = new String[1];
            states[0] = evidenceStates.get(i);

            //find the parents and children for the evidence variable
            //create the probability hashmap
            for (Variable v: variables) {
                if(v.name.equals(evidence.get(i))){
                    String[] parents = v.parents;
                    String[] children = v.children;

                    //find the position of the state in the variables original state array
                    int statePos = 0;
                    for (int j = 0; j < v.states.length; j++) {
                        if(v.states[j].equals(evidenceStates.get(i))){
                            statePos = j;
                        }
                    }
                    //create an empty hashmap to store the new probability distribution given the variables known states
                    HashMap<String, ArrayList> evidenceProbs = new HashMap<>();

                    for (Map.Entry<String, ArrayList> item: v.probabilities.entrySet()) {
                        ArrayList<Object> prob = new ArrayList<>();
                        prob.add(item.getValue().get(statePos));
                        evidenceProbs.put(item.getKey(), prob);
                    }
                }
            }

        }
        return evidenceFactors;
    }

    /*
     * recursively loop through the relevant factors until you reach the last two in the list and then find the
     * pointwise product of two factors as a time, eventually reaching the final factor that is the pointwise product
     * of all the factors
     */
    public static Variable pointwiseProduct(Variable currentFactor, ArrayList<Variable> factors, Variable v){
        //properties for the Variable that will be returned
        String name = v.name;
        //have to temporarily change states, parents, and children arrays to arraylists so we can easily add to them
        ArrayList<String> states = new ArrayList<>();
        Collections.addAll(states, v.states);

        ArrayList<String> parents = new ArrayList<>();
        Collections.addAll(parents, v.parents);

        ArrayList<String> children = new ArrayList<>();
        Collections.addAll(children, v.children);

        HashMap<String, ArrayList> newProbabilities = new HashMap<>();

        if(factors.size() == 1){
            Variable f1 = currentFactor;
            Variable f2 = factors.get(0);

            //update the states, parents and children to include everything from the two factors
            for (String state : f1.states) {
                if(!states.contains(state)){
                    states.add(state);
                }
            }
            for (String state : f2.states) {
                if(!states.contains(state)){
                    states.add(state);
                }
            }
            //updating the children
            for (String child : f1.children) {
                if(!children.contains(child)){
                    children.add(child);
                }
            }
            for (String child : f2.children) {
                if(!children.contains(child)){
                    children.add(child);
                }
            }
            //updating the parents
            for (String p : f1.parents) {
                if(!parents.contains(p)){
                    parents.add(p);
                }
            }
            for (String p : f2.parents) {
                if(!parents.contains(p)){
                    parents.add(p);
                }
            }

            //updating the name
            name = name + " " + f1.name + " " + f2.name + " ";

            //find the index of the shared variable in the parents list of each factor
            int index1 = 0;
            for (int i = 0; i < f1.parents.length; i++) {
                if(f1.parents[i].equals(v.name)){
                    index1 = i;
                }
            }
            int index2 = 0;
            for (int j = 0; j < f2.parents.length; j++) {
                if(f2.parents[j].equals(v.name)){
                    index2 = j;
                }
            }

            //get all the possible states for the shared variable
            String[] vStates = v.states;
            ArrayList<Double> currFactor1Probs = new ArrayList<>();
            ArrayList<Double> currFactor2Probs = new ArrayList<>();

            for (String vState : vStates) {
                //add all the probability distributions from the current factors, where the current variable
                //is in the current state that we are looking at
                for (Map.Entry<String, ArrayList> item: f1.probabilities.entrySet()) {
                    String[] stateLabels = item.getKey().split(" ");
                    if(stateLabels[index1].equals(vState)){
                        currFactor1Probs.addAll(item.getValue());
                    }
                }
                for (Map.Entry<String, ArrayList> item: f2.probabilities.entrySet()) {
                    String[] stateLabels = item.getKey().split(" ");
                    if(stateLabels[index2].equals(vState)){
                        currFactor2Probs.addAll(item.getValue());
                    }
                }

                for (int i = 0; i < currFactor1Probs.size(); i++) {
                    for (int j = 0; j < currFactor2Probs.size(); j++) {
                        double newProb = currFactor1Probs.get(i) * currFactor2Probs.get(j);
                        String newStates = vState + " " +f1.states[i] + " " + f2.states[j];

                        ArrayList<Double> newProbList = new ArrayList<>();
                        newProbList.add(newProb);

                        newProbabilities.put(newStates, newProbList);

                    }

                }
                currFactor1Probs.clear();
                currFactor2Probs.clear();
            }
        }
        else{
            Variable nextFactor = factors.remove(0);
            pointwiseProduct(nextFactor, factors, v);
        }

        //convert states, parents, and children back to arrays to create a new Variable
        String[] statesArray = new String[states.size()];
        statesArray = states.toArray(statesArray);

        String[] parentsArray = new String[parents.size()];
        parentsArray = parents.toArray(parentsArray);

        String[] childrenArray = new String[children.size()];
        childrenArray = children.toArray(childrenArray);

        Variable product = new Variable(name, statesArray, parentsArray, childrenArray);
        product.probabilities = newProbabilities;

        return product;
    }

    public HashMap<String, ArrayList<Double>> normalize(HashMap<String, ArrayList<Double>> probabilities){
        double sumP = 0.0;
        HashMap<String, ArrayList<Double>> normalizedProbabilities = new HashMap<>();

        for (Map.Entry<String, ArrayList<Double>> item: probabilities.entrySet()) {
            for(double p : item.getValue()){
                sumP += p;
            }
        }

        for (Map.Entry<String, ArrayList<Double>> item: probabilities.entrySet()) {
            ArrayList<Double> normalized_p = new ArrayList<>();
            for(double p : item.getValue()){
                normalized_p.add(p/sumP);
            }
            normalizedProbabilities.put(item.getKey(), normalized_p);
        }
        return normalizedProbabilities;
    }

    public ArrayList<Variable> makeFactors(Variable var, ArrayList<Variable> f, ArrayList<Variable> e){

        //add the probability distribution of var to the list of factors
        //if the var is evidence only add what we know about the var to the factors
        if(!e.contains(var)){
            f.add(var);
        }else{
            for (Variable variable : e) {
                //have to compare the names because the variables might not share a memory address
                if(variable.name.equals(var.name)){
                    f.add(variable);
                }
            }
        }
        return f;
    }

    public static ArrayList<Variable> sumOut(Variable v, ArrayList<Variable> f){
        //marginalization
        //go through the factors and marginalize based on the evidence and what is known about the factors
        //i.e. remove any irrelevant factors
        ArrayList<Variable> relevantFactors = new ArrayList<>();

        //check what factors interact (are in the Markov blanket) of the current factor we are summing out
        for (Variable factor : f) {
            if(Arrays.stream(v.parents).anyMatch(factor.name::equals) || Arrays.stream(v.children).anyMatch(factor.name::equals)){
                relevantFactors.add(factor);
            }
        }

        //remove the relevant factors that we will be combining from the list of all factors
        for(Variable rf: relevantFactors){
            f.remove(rf);
        }

        Variable firstFactor = relevantFactors.remove(0);
        Variable product = pointwiseProduct(firstFactor, relevantFactors,  v);
        HashMap<String, ArrayList> tempProbs = copy(product.probabilities);
        HashMap<String, ArrayList> newProbabilities = new HashMap<>();
        //add the rows where all variables (except the one summing out) are in the same state
        for (Map.Entry<String, ArrayList> item: product.probabilities.entrySet()) {
            //loop through all other items in hashmap to see where the other variables are in the same state

            //the first state in the string of states (the hashmap key) is the one for the variable we are summing out
            //so we can remove it and only compare the states of the other variables
            String[] state1Labels = item.getKey().split(" ");
            ArrayList<String> state1List = new ArrayList<>();
            Collections.addAll(state1List, state1Labels);
            state1List.remove(0);

            //remove the current item from tempProbs (copy of product.probabilities hashmap)
            tempProbs.remove(item.getKey(), item.getValue());
            for (Map.Entry<String, ArrayList> other: tempProbs.entrySet()) {

                String[] state2Labels = other.getKey().split(" ");
                ArrayList<String> state2List = new ArrayList<>();
                Collections.addAll(state2List, state2Labels);
                state2List.remove(0);

                if(state1List.equals(state2List)){
                    //add the array lists where the states of the vars are equal
                    ArrayList newProb = add(item.getValue(), other.getValue());
                    if(!newProbabilities.containsKey(item.getKey())){
                        String newState = "";
                        for(String s : state1List){
                            newState = newState + s + " ";
                        }
                        newProbabilities.put(newState, newProb);
                    }
                }
            }
        }
        product.probabilities = newProbabilities;
        f.add(product);
        return f;
    }

    //helper method to add two array lists together
    public static ArrayList<Double> add(ArrayList<Double> item, ArrayList<Double> other) {
        ArrayList<Double> newProb = new ArrayList<>();
        for (int i = 0; i < item.size(); i++) {
            newProb.add(item.get(i) + other.get(i));
        }
        return newProb;
    }

    //simple helper method to make a copy of a hashmap
    public static HashMap<String, ArrayList> copy (HashMap<String, ArrayList> original) {
        HashMap<String, ArrayList> copy = new HashMap<>();
        for (Map.Entry<String, ArrayList> entry: original.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }


}
