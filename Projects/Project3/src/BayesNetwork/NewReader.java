package BayesNetwork;

import com.sun.source.tree.Tree;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class NewReader {

    public static TreeMap<String, ArrayList> getVariables(File file) throws FileNotFoundException {
        TreeMap<String, ArrayList> variables = new TreeMap<>();
        Scanner bifScanner = new Scanner(file);
        ArrayList list = new ArrayList();
        while (bifScanner.hasNext()) {
            String bif = bifScanner.next();
            if (!Objects.equals(bif, "]") && !Objects.equals(bif, "[")) {
                list.add(bif);
            }
        }
        String k = null;
        String v;
        ArrayList keys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), "variable")) {
                if (!Objects.equals(list.get(i + 1), "")) {
                    k = (String) list.get(i + 1);
                    keys.add(k);
                }
            }
            if (Objects.equals(list.get(i), "discrete")) {
                ArrayList vs = new ArrayList();
                i = i + 3;
                do{v = (String) list.get(i);
                vs.add(v);
                i++;
                } while (!Objects.equals(list.get(i), "};"));

                variables.put(k, vs);
            }
        }
        return variables;
    }


    public static TreeMap<ArrayList, ArrayList> getProbabilities(File file, TreeMap variables) throws FileNotFoundException {
        TreeMap<ArrayList, ArrayList> probabilities = new TreeMap<>();
        Scanner bifScanner = new Scanner(file);
        ArrayList list = new ArrayList();
        while (bifScanner.hasNext()) {
            String item = bifScanner.next();
            if (!Objects.equals(item, "(") && !Objects.equals(item, ")")) {
                list.add(item);
            }
        }
        String keys = null;
        String num = null;
        int p = 0;
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), "probability")) {
                p = i + 1;
                for (int j = p; j < list.size(); j++) {
                    ArrayList pc = new ArrayList();
                    ArrayList prob = new ArrayList();
                    if (variables.containsKey(list.get(j)) || Objects.equals(list.get(j), "|")) {
                        if (Objects.equals(list.get(j), "|") || !Objects.equals(list.get(j), "{")) {
                            keys = (String) list.get(j);
                            pc.add(keys);
                            continue;
                        }
                    }
                    if (variables.containsValue(list.get(j))  || !Objects.equals(list.get(j), "}")) {
                        num = (String) list.get(j);
                        prob.add(num);
                        continue;
                    }
                    probabilities.put(pc, prob);
                    continue;
                }
            }
        }
        return probabilities;
    }

}
