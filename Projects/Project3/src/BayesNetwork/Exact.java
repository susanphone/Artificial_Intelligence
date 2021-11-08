package BayesNetwork;
import java.util.*;

public class Exact {
    BayesNet currentNet;
    Variable query;
    ArrayList<Variable> observations;

    public Exact(BayesNet currNet, Variable q, ArrayList<Variable> observed){
        this.currentNet = currNet;
        this.query = q;
        this.observations = observed;
    }

    public static HashMap<String, ArrayList<Double>> variableElimination(BayesNet currentNet, String query, ArrayList<String> evidence, ArrayList<String> evidenceStates){

        ArrayList<Variable> factors = new ArrayList<>();
        ArrayList<Variable> varOrder = new ArrayList<>();

        //initialize the query variable, which will be assigned appropriately later
        String[] temp = new String[1];
        Variable queryVar = new Variable(query, temp, temp, temp);

        //because of the format of the bif files the current list of variables are in topological order
        //so we can fill in a new arraylist in reverse to get reverse topological order
        //if we were given different files, or a different order to begin with, we could not guarantee this would
        //produce a list in reverse topological order, so it would become more computationally expensive
        for (int i = currentNet.variables.size() - 1; i >= 0 ; i--) {
            varOrder.add(currentNet.variables.get(i));
        }

        //make factors based on the evidence variables we receive, which will later be added to the factors list
        ArrayList<Variable> observed = makeEvidenceFactors(evidence, evidenceStates, currentNet.variables);

        //every variable that is not an ancestor of the query variable or an evidence variable is
        //irrelevant to the query
        for(Variable var: varOrder){
            if(var.name.equals(query)){
                queryVar = var;
            }else{
                factors = makeFactors(var, factors, observed);
                if(!var.name.equals(query) && !observed.contains(var)){
                    factors = sumOut(var, factors);
                }
            }


        }
        Variable result = finalpointwiseProduct(queryVar, factors);
        HashMap<String, ArrayList<Double>>  normalizedResult = new HashMap<>();
        normalizedResult = normalize(result);
        return normalizedResult;
    }

    public static ArrayList<Variable> makeFactors(Variable var, ArrayList<Variable> f, ArrayList<Variable> e){

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
            if(Arrays.stream(factor.parents).anyMatch(v.name::equals) || Arrays.stream(factor.children).anyMatch(v.name::equals)){
                relevantFactors.add(factor);
            }
        }

        //remove the relevant factors that we will be combining from the list of all factors
        for(Variable rf: relevantFactors){
            f.remove(rf);
        }

        Variable firstFactor = relevantFactors.remove(0);
        Variable product = pointwiseProduct(firstFactor, relevantFactors,  v);
        HashMap<String, ArrayList<Double>> tempProbs = copy(product.probabilities);
        HashMap<String, ArrayList<Double>> newProbabilities = new HashMap<>();
        //add the rows where all variables (except the one summing out) are in the same state
        for (Map.Entry<String, ArrayList<Double>> item: product.probabilities.entrySet()) {
            //loop through all other items in hashmap to see where the other variables are in the same state

            //the first state in the string of states (the hashmap key) is the one for the variable we are summing out
            //so we can remove it and only compare the states of the other variables
            String[] state1Labels = item.getKey().split(" ");
            ArrayList<String> state1List = new ArrayList<>();
            Collections.addAll(state1List, state1Labels);
            state1List.remove(0);

            //remove the current item from tempProbs (copy of product.probabilities hashmap)
            tempProbs.remove(item.getKey(), item.getValue());
            for (Map.Entry<String, ArrayList<Double>> other: tempProbs.entrySet()) {

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
        if(!newProbabilities.isEmpty()){
            product.probabilities = newProbabilities;
        }
        f.add(product);
        //and remove the factor for the variable we summed out
        f.remove(v);
        return f;
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
                    HashMap<String, ArrayList<Double>> evidenceProbs = new HashMap<>();

                    for (Map.Entry<String, ArrayList<Double>> item: v.probabilities.entrySet()) {
                        ArrayList<Double> prob = new ArrayList<>();
                        prob.add(item.getValue().get(statePos));
                        evidenceProbs.put(item.getKey(), prob);
                    }
                    Variable currVar = v;
                    currVar.probabilities = evidenceProbs;
                    currVar.states = states;
                    evidenceFactors.add(currVar);
                }
            }

        }
        return evidenceFactors;
    }

    private static Variable finalpointwiseProduct(Variable queryVar, ArrayList<Variable> factors) {

        HashMap<String, ArrayList<Double>> newProbabilities = new HashMap<>();

        for (String state : queryVar.states){

            double queryProb = 0.0;

            for (Map.Entry<String, ArrayList<Double>> item: queryVar.probabilities.entrySet()) {
                String[] stateLabels = item.getKey().split(" ");
                if(stateLabels[0].equals(state)) {
                    queryProb = item.getValue().get(0);
                }
            }
            for(Variable f : factors){
                for (Map.Entry<String, ArrayList<Double>> item: queryVar.probabilities.entrySet()) {
                    ArrayList<Double> newProbsList = new ArrayList<>();
                    for(double d : item.getValue()){
                        double newProb = queryProb * d;
                        newProbsList.add(newProb);
                    }
                    newProbabilities.put(item.getKey(), newProbsList);
                }
            }
        }
        Variable finalResult = new Variable(queryVar.name, queryVar.states, queryVar.parents, queryVar.children);
        finalResult.probabilities = newProbabilities;
        return finalResult;
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

        HashMap<String, ArrayList<Double>> newProbabilities = new HashMap<>();

        //if there is only one factor and the variable itself to sum out,
        //then take pointwise product of factor and variable
        //else take the pointwise product between all the variables in the factor list
        if(factors.size() == 0){
            Variable f1 = currentFactor;

            //update the states, parents and children to include everything from the two factors
            for (String state : f1.states) {
                if(!states.contains(state)){
                    states.add(state);
                }
            }

            //updating the name
            name = name + " " + f1.name + " ";

            //find the index of the variable in the parents list of f1
            int index1 = 0;
            for (int i = 0; i < f1.parents.length; i++) {
                if(f1.parents[i].equals(v.name)){
                    index1 = i;
                }
            }

            //get all the possible states for the shared variable
            String[] vStates = v.states;
            ArrayList<Double> currFactor1Probs = new ArrayList<>();
            ArrayList<Double> currVarProbs = new ArrayList<>();

            for (int s = 0; s < v.states.length; s++) {
                String vState = v.states[s];
                //add all the probability distributions from the current factors, where the current variable
                //is in the current state that we are looking at
                for (Map.Entry<String, ArrayList<Double>> item: f1.probabilities.entrySet()) {
                    String[] stateLabels = item.getKey().split(" ");
                    if(stateLabels[index1].equals(vState)){
                        currFactor1Probs.addAll(item.getValue());
                    }
                }
                for (Map.Entry<String, ArrayList<Double>> item: v.probabilities.entrySet()) {
                    String[] stateLabels = item.getKey().split(" ");
                    if(stateLabels[0].equals(vState)){
                        currVarProbs.addAll(item.getValue());
                    }
                }

                for (int i = 0; i < currFactor1Probs.size(); i++) {
                    for (int j = 0; j < currVarProbs.size(); j++) {
                        double newProb = currFactor1Probs.get(i) * currVarProbs.get(j);
                        String newStates = vState + " " +f1.states[i] + " " + v.states[j];

                        ArrayList<Double> newProbList = new ArrayList<>();
                        newProbList.add(newProb);

                        newProbabilities.put(newStates, newProbList);

                    }

                }
                currFactor1Probs.clear();
                currVarProbs.clear();
            }
        }
        else if(factors.size() == 1){
            Variable f1 = currentFactor;
            Variable f2 = factors.get(0);

            //update the states
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
                for (Map.Entry<String, ArrayList<Double>> item: f1.probabilities.entrySet()) {
                    String[] stateLabels = item.getKey().split(" ");
                    if(stateLabels[index1].equals(vState)){
                        currFactor1Probs.addAll(item.getValue());
                    }
                }
                for (Map.Entry<String, ArrayList<Double>> item: f2.probabilities.entrySet()) {
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

    public static HashMap<String, ArrayList<Double>> normalize(Variable factor){
        HashMap<String, ArrayList<Double>> normalizedProbabilities = new HashMap<>();

        double sumP = 0.0;

        for (Map.Entry<String, ArrayList<Double>> item: factor.probabilities.entrySet()) {
            for(double p : item.getValue()){
                sumP += p;
            }
        }

        for (Map.Entry<String, ArrayList<Double>> item: factor.probabilities.entrySet()) {
            ArrayList<Double> normalized_p = new ArrayList<>();
            for(double p : item.getValue()){
                normalized_p.add(p/sumP);
            }
            normalizedProbabilities.put(item.getKey(), normalized_p);
        }
        return normalizedProbabilities;
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
    public static HashMap<String, ArrayList<Double>> copy (HashMap<String, ArrayList<Double>> original) {
        HashMap<String, ArrayList<Double>> copy = new HashMap<>();
        for (Map.Entry<String, ArrayList<Double>> entry: original.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }


}
