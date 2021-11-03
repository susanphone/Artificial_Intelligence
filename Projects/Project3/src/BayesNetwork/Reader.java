package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader {

    //    cleans up file and strips away any unnecessary characters
    public static ArrayList cleanUpFile(File file) throws FileNotFoundException {
        Scanner bifScanner = new Scanner(file);
        ArrayList list = new ArrayList();
        while (bifScanner.hasNext()) {
            String bif = bifScanner.next();
            if (!Objects.equals(bif, "{") && !Objects.equals(bif, "}") && !Objects.equals(bif, "]")
                    && !Objects.equals(bif, "[") && !Objects.equals(bif, "(") && !Objects.equals(bif, ")")
                    && !Objects.equals(bif, "};") ) {
                list.add(bif);
            }
        }

        return list;
    }

    //    create a tree for the variables
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
                do {
                    v = (String) list.get(i);
                    vs.add(v);
                    i++;
                } while (!Objects.equals(list.get(i), "};"));

                variables.put(k, vs);
            }
        }
        return variables;
    }

    //    create a tree for the probabilities
    public static TreeMap<String, ArrayList> getProbabilities(File file) throws FileNotFoundException {
        TreeMap<String, ArrayList>probabilities = new TreeMap<>();
        String k = null;
        String v;
        Scanner bifScanner = new Scanner(file);
        ArrayList list = new ArrayList();
        while (bifScanner.hasNextLine()) {
            String line = bifScanner.nextLine();
            list.add(line);
        }
        String keys;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(list.contains("probability"))) {
                ArrayList ps = new ArrayList();
                k = (String) list.get(i);
                keys = k;
                if (!Objects.equals(list.get(i + 1), "}")) {
                    i = i + 1;
                    v = (String) list.get(i);
                    ps.add(v);
                }
                probabilities.put(keys, ps);
            }
        }
        return probabilities;
    }
}