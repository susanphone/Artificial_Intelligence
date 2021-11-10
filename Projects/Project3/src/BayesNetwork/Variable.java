package BayesNetwork;

import java.util.HashMap;
import java.util.ArrayList;
public class Variable {
    public String name;
    public String[] states;
    public String[] parents;
    public String[] children;
    public HashMap<String, ArrayList<Double>> probabilities = new HashMap<>();

    public Variable(String name, String[] states, String[] parents, String[] children){
        this.name = name;
        this.states = states;
        this.parents = parents;
        this.children = children;
    }

    public void addChild(String child) {
        // create a new array of size n+1
        String newChildren[] = new String[children.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (int i = 0; i < children.length; i++)
            newChildren[i] = children[i];

        newChildren[children.length] = child;

        this.children = newChildren;
    }

    public void addParents(ArrayList<String> parentsToAdd) {
        var newLength = parents.length + parentsToAdd.size();

        // create a new array of size n+1
        String newParents[] = new String[newLength];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (int i = 0; i < parents.length; i++)
            newParents[i] = parents[i];

        for (int i = parents.length; i < newLength; i++)
            newParents[i] = parentsToAdd.get(i - parents.length);

        this.parents = newParents;
    }
}
