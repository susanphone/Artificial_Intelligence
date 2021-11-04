package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader {

    //    cleans up file and strips away any unnecessary characters
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
        ArrayList keys = new ArrayList<>();
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

    //    create a tree for the probabilities
    public static Map<String, ArrayList<String>> getProbabilities(File file, TreeMap<String, ArrayList<String>>
            variables) throws FileNotFoundException, IndexOutOfBoundsException, NullPointerException {
        Map<String, ArrayList<String>> probabilities = new TreeMap<>();
        ArrayList<String> list = new ArrayList<String>();
        String keys = null;
        String num = null;
        Scanner bifScanner = new Scanner(file);
        while (bifScanner.hasNext()) {
            String item = bifScanner.next();
            if (!Objects.equals(item, "(") && !Objects.equals(item, ")")) {
                list.add(item);
            }
        }

        int p = 0;
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), "probability")) {
                p = i + 1;
            }
        }
        String pc = null;
        for (int j = p; j < list.size(); j++) {
            ArrayList<String> prob = new ArrayList<String>();
            if (variables.containsKey(list.get(j)) || Objects.equals(list.get(j), "|")) {
                if (Objects.equals(list.get(j), "|") || !Objects.equals(list.get(j), "{")) {
                    keys = list.get(j);
                    if (pc == null) {
                        pc = keys;
                    } else {
                        pc = pc + " " + keys;
                        continue;
                    }
                }
            }
            if (!(Objects.equals(list.get(j), "}") || Objects.equals(list.get(j), "{"))) {
                if (!variables.containsKey(list.get(j))) {
                    do {
                        num = list.get(j);
                        prob.add(num);
                        j++;
                    } while (!Objects.equals(list.get(j), "}"));
                    probabilities.put(pc, prob);
                    pc = null;
                }
            }
        }
        return probabilities;
    }
}