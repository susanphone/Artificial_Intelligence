package BayesNetwork;

import com.sun.jdi.Value;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.*;

public class Reader implements Comparator<Key> {

//    cleans up file and strips away any unnecessary characters
    public static ArrayList cleanUpFile(File file) throws FileNotFoundException {
        Scanner bifScanner = new Scanner(file);
        ArrayList list = new ArrayList();
        while (bifScanner.hasNextLine()) {
            String data = bifScanner.nextLine();
//                System.out.println(data);

            String d = data.replaceAll("[^a-zA-Z0-9. ]", "");
//            System.out.println(d);
            list.add(d);

        }
        return list;
    }

//    create a tree for the variables
    public TreeMap<Key, ArrayList> getVariables(ArrayList list){
        TreeMap<Key, ArrayList> variables = new TreeMap<>();
        Key k = null;
        Value v = null;
        ArrayList <Key> keys = new ArrayList<>();
        for (int i=0; i < list.size(); i++) {
            if (list.get(i) == "variable") {
                k = (Key) list.get(i + 1);
                keys.add(k);
            }
            if (list.get(i) == "discrete") {
                ArrayList vs = new ArrayList();
                while (list.get(i) != "\n") {
                    v = (Value) list.get(i + 1);
                    vs.add((Value) v);
                }
                compare(k, keys.get(keys.indexOf(i-1)));
                variables.put(keys.get(keys.lastIndexOf(k)), vs);
            }
        }
        return  variables;
    }

//    create a tree for the probabilities
    public TreeMap<Key, ArrayList> getProbabilities(ArrayList list) {
        TreeMap<Key, ArrayList>probabilities = new TreeMap<>();
        Key k = null;
        Value v = null;
        ArrayList <Key> keys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == "probability") {
                k = (Key)list.get(i + 1);
                keys.add(k);
            }
            if (list.get(i) == "[^0-9.]") {
                ArrayList ps = new ArrayList();
                while (list.get(i) != "\n") {
                    v = (Value)list.get(i + 1);
                    ps.add(v);
                }
                compare(k, keys.get(keys.indexOf(i-1)));
                probabilities.put(k, ps);
            }

        }
        return probabilities;
    }

    @Override
//    need to compare the keys in order to place them in the tree
    public int compare(Key o1, Key o2) {
        return 0;
    }
}
