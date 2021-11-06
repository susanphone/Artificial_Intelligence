package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader2 {

    public static ArrayList<String> cleanUpFile(File file) throws FileNotFoundException {
        Scanner bifScanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<String>();
        String line;
        while (bifScanner.hasNextLine()) {
            line = bifScanner.nextLine();
            list.add(line);
        }

        return list;
    }

    //    create a tree for the variables
    public static TreeMap<String, ArrayList<String>> getVariables(File file) throws FileNotFoundException {
        TreeMap<String, ArrayList<String>> variables = new TreeMap<>();
        Scanner bifScanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<String>();
        while (bifScanner.hasNext()) {
            String bif = bifScanner.next();
            if (!Objects.equals(bif, "]") && !Objects.equals(bif, "[")) {
                list.add(bif);
            }
        }
        String k = null;
        String v;
        ArrayList<Object> keys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), "variable")) {
                if (!Objects.equals(list.get(i + 1), "")) {
                    k = list.get(i + 1);
                    keys.add(k);
                }
            }
            if (Objects.equals(list.get(i), "discrete")) {
                ArrayList<String> vs = new ArrayList<String>();
                i = i + 3;
                do {
                    v = list.get(i);
                    vs.add(v);
                    i++;
                } while (!Objects.equals(list.get(i), "};"));

                variables.put(k, vs);
            }
        }
        return variables;
    }

    public static Map<String, ArrayList<String>> getProbabilities(File file, TreeMap variable) throws FileNotFoundException {
        Map<String, ArrayList<String>> probabilities = new TreeMap<>();
        ArrayList<String> list = new ArrayList<String>();
        String keys = null;
        String num = null;
        Scanner bifScanner = new Scanner(file);
        while (bifScanner.hasNext()) {
            String item = bifScanner.next();
            if (!Objects.equals(item, ")") && !Objects.equals(item, "(")
                    && !Objects.equals(item, "|")) {
                list.add(item);
            }
        }
        String child = null;
        String parent = null;
        ArrayList<String> values = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), "probability")) {
                i = i + 1;
                child = list.get(i);
                if (variable.containsKey(list.get(i+1))) {
                    i = i + 1;
                    while (!Objects.equals(list.get(i), "{")) {
                        if (parent == null) {
                            parent = list.get(i);
                        } else {
                            parent = parent + " " + list.get(i);
                        }
                        i++;
                    }
                }
                String cp;
                if (parent != null) {
                    cp = child + " " + parent;
                } else {
                    cp = child;
                }
                if (Objects.equals(list.get(i), "{")) {
                    i = i + 1;
                    while (!Objects.equals(list.get(i), "}")) {
                        values.add(list.get(i));
                        i++;
                    }
                    probabilities.put(cp, values);
                    cp = null;
                    parent = null;
                    child = null;
                    values = new ArrayList<>();
                }
            }
        }

        return probabilities;
    }
}

