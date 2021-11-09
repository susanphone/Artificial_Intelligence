package BayesNetwork;
import java.util.Random;

public class HelperFunctions {
    
    //Returns index of selected value; each probability is expected to be a double less than 1, with the array adding up to 1
    static public int randomizer(double[] probs){
        Random rand = new Random();
        
        double randnum = rand.nextDouble();
        int index = 0;
        
        double count = 0;
        while(count<1){
            if(probs[index] != 0 && randnum < probs[index]){
                return index;
            }
            else if(index == probs.length-1){
                return index;
            }
            else{
                count += probs[index];
                index++;
            }
        }
        
        return index;
    }
    
    static public double[] normalize(int size, int[] count){
      double prob[] = new double[count.length];
      for(int i = 0; i < count.length; i++){
          prob[i] = ((double) count[i])/((double) size) *100.0;
      }
        return prob;
    }
    
    static public Variable[] getParents(BayesNet bn, Variable var){
        //Used for testing
        String[] parentin = var.parents;
        
        //Returns null if there are no parents
        if(parentin.length == 0){
            return null;
        }
        
        //Used to store the parent variables
        Variable[] parents = new Variable[parentin.length];
        
        //Used for indexing the parents array
        int parentcount = 0;
        
        //Loops through all variables in the BayesNet and tests to see if it's a parent of the current node
        for(int i = 0; i < bn.variables.size(); i++){
            Variable currentvar = bn.variables.get(i); //Gets a variable in the bayesnet
            
            //Checks to see if the variable is a parent and adds it to the correct variable array
            for(int j = 0; j < parentin.length; j++){
                if(currentvar.name.equals(parentin[j])){
                    parents[parentcount] = currentvar;
                    parentcount++;
                }
            }
        }
        
        return parents;
    }
    
    
        
    static public Variable[] getChildren(BayesNet bn, Variable var){
        //Used for testing
        String[] childin = var.children;
        
        //Returns null if there are no parents
        if(childin.length == 0){
            return null;
        }
        
        //Used to store the children and parent variables
        Variable[] children = new Variable[childin.length];
        
        //Used for indexing the variable arrays
        int childcount = 0;
        
        //Loops through all variables in the BayesNet and tests to see if it's a child of the current node
        for(int i = 0; i < bn.variables.size(); i++){
            Variable currentvar = bn.variables.get(i); //Gets a variable in the bayesnet
            //Checks to see if the variable is a child and adds it to the correct variable array
            for(int j = 0; j < childin.length; j++){
                if(currentvar.name.equals(childin[j])){
                    children[childcount] = currentvar;
                    childcount++;
                }
            }
        }
        
        return children;
    }
}
