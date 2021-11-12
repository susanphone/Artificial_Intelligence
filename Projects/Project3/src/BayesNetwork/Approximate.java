package BayesNetwork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Approximate {

    static public void gibbs(BayesNet bn, int sample_size, ArrayList<String> evidence, ArrayList<String> evidenceStates, String[] queries) {
        //Sets up the first state
        String[] bn_state = new String[bn.variables.size()];
        Random rand = new Random();
        ArrayList<Integer> evidence_indexes = new ArrayList<Integer>();
        int evidence_index = 0; //Used to loop through evidence and evidenceStates ArrayList
        for (int i = 0; i < bn_state.length; i++) {
            Variable currentvar = bn.variables.get(i); //Gets the current variable

            //Checks to see if current variable is in the evidence list; If so, then it is added
            if (currentvar.name.equals(evidence.get(evidence_index))) {
                bn_state[i] = evidenceStates.get(evidence_index);
                evidence_index++;
                evidence_indexes.add(i);
            }

            //Randomizes a variable's state if it's not in the evidence
            else {
                bn_state[i] = currentvar.states[rand.nextInt(currentvar.states.length)];
            }
        }

        //Boolean array used to store all states created by sampling
        String[][] state_storage = new String[sample_size][bn_state.length];

        //Samples x times, with x = sample_size
        for (int i = 0; i < sample_size; i++) {
            int var;
            do {
                var = rand.nextInt(bn_state.length); //Gets index of state to change 
            } while (evidence_indexes.contains(var));

            //Gets the randomly selected variable to be randomized
            Variable current_var = bn.variables.get(var);

            //Gets the probability set of current_var given the current state
            double[] probs = probability(bn, current_var, bn_state);

            //Randomly changes the state of the current variable
            bn_state[var] = current_var.states[HelperFunctions.randomizer(probs)];

            //Saves the new state to the state storage
            System.arraycopy(bn_state, 0, state_storage[i], 0, bn_state.length);
        }

        //Counts the occurances of the queries, normalizes the values, then prints out the information for the query variable
        for (int i = 0; i < queries.length; i++) {
            //Gets position of current query variable in the BayesNet variables ArrayList
            int querie_position = 0;
            for (int j = 0; j < bn.variables.size(); j++) {
                if (bn.variables.get(j).name.equals(queries[i])) {
                    querie_position = j;
                }
            }

            //Gets the states of the current query variabled
            String[] states = bn.variables.get(querie_position).states;
            
            //Counts the occurances of each of the states of the query variables
            int[] counts = new int[states.length];
            for (String[] current_state : state_storage) {
                String current_querie = current_state[querie_position];

                for (int j = 0; j < states.length; j++) {
                    if (states[j].equals(current_querie)) {
                        counts[j]++;
                    }
                }

            }
            
            //Prints out the query name
            System.out.println(queries[i]);
            
            //Prints out each state of the query alongside their probabilities
            for (int j = 0; j < counts.length; j++) {
                System.out.println(states[j] + ": " + (((double) counts[j]) / ((double) sample_size)));
            }
            System.out.println();
        }

    }
    
    //Returns the probabilities of each state of a variable
    static public double[] probability(BayesNet bn, Variable var, String[] bn_state){
        //Used to calculate numerator of probability equation for each variable state
        Double[] numerators = new Double[var.states.length];

        //Calculates the numerator of the probability equation for each variable state
        for(int i = 0; i < numerators.length; i++){
            //Stores values of numerator to later be multiplied together
            ArrayList<Double> multiplicants = new ArrayList<Double>();
            
            //Self
            Variable[] var_parents;
            var_parents = HelperFunctions.getParents(bn, var);
            
            //If there's no parents of the current variable
            if(var_parents == null){
                double temp = var.probabilities.get(var.states[i]).get(0);
                multiplicants.add(temp);
            }
            
            //If there are parents of the current variable
            else{
                
                //Gets state of each parent, adds it to string
                String parent_states = "(";
                for(int j = 0; j < var_parents.length; j++){
                    for(int k = 0; k < bn.variables.size(); k++){
                        if(bn.variables.get(k).name.equals(var_parents[j].name)){
                            if(parent_states.equals("(")){
                                parent_states = parent_states.concat(bn_state[k]);
                            }
                            else{
                                parent_states = parent_states.concat("," + bn_state[k]);
                            }
                        }
                    }
                }
                parent_states = parent_states.concat(")");
                multiplicants.add(var.probabilities.get(parent_states).get(i));
//                System.out.println("  " + var.probabilities.get(parent_states).get(i));
            }
            
            //Children
            Variable[] var_children = HelperFunctions.getChildren(bn, var);
                
            //For each child, the multiplicant is calculated in the same fashion as the above variable
            for(Variable current_child : var_children){
                Variable[] child_parents = HelperFunctions.getParents(bn, current_child);
                
                String child_parent_states = "(";
                for(int j = 0; j < child_parents.length; j++){
                    for(int k = 0; k < bn.variables.size(); k++){
                        if(bn.variables.get(k).name.equals(var.name) && bn.variables.get(k).name.equals(child_parents[j].name)){
                            if(child_parent_states.equals("(")){
                                child_parent_states = child_parent_states.concat(var.states[i]);
                            }
                            else{
                                child_parent_states = child_parent_states.concat("," + var.states[i]);
                            }
                        }
                        else if(bn.variables.get(k).name.equals(child_parents[j].name)){
                            if(child_parent_states.equals("(")){
                                child_parent_states = child_parent_states.concat(bn_state[k]);
                            }
                            else{
                                child_parent_states = child_parent_states.concat("," + bn_state[k]);
                            }
                        }
                    }
                }
                child_parent_states = child_parent_states.concat(")");
                int current_child_index = 0;
                for(int j = 0; j < bn.variables.size(); j++){
                    if(bn.variables.get(j).name.equals(current_child.name)){
                        current_child_index = j;
                    }
                }
                
                int current_child_state_index = 0;
                for(int j = 0; j < current_child.states.length; j++){
                    if(current_child.states[j].equals(bn_state[current_child_index])){
                        current_child_state_index = j;
                    }
                }

                System.out.println(current_child.name);
                System.out.println(current_child.states[current_child_state_index]);
                System.out.println(child_parent_states);
                System.out.println(current_child.probabilities.get(child_parent_states));
                System.out.println(current_child.probabilities.get(child_parent_states).get(current_child_state_index));
                
                multiplicants.add(current_child.probabilities.get(child_parent_states).get(current_child_state_index)); 
            }
            //Calculates the numerator
            double product = 1;
            for(double num : multiplicants){
                product *= num;
            }
            numerators[i] = product;
        }
        
        //Calculates the probabilities for the given variable states
        double[] probabilities = new double[var.states.length];
        double sum = 0;
        for(double num : numerators){
            sum += num;
        }
        for(int i = 0; i < probabilities.length; i++){
            probabilities[i] = numerators[i]/sum;
        }
        
        return probabilities;
    }
}