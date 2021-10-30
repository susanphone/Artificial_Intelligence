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
    public static TreeMap<String, ArrayList> getVariables(ArrayList list) {
        TreeMap<String, ArrayList> variables = new TreeMap<>();
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
                i = i + 1;
                if (!Objects.equals(list.get(i), "variable") && !Objects.equals(list.get(i), " probability")) {
                    v = (String) list.get(i + 1);
                    vs.add(v);
                }

                if (!Objects.equals(list.get(i), "variable") && !Objects.equals(list.get(i), " probability")) {
                    v = (String) list.get(i + 2);
                    vs.add(v);
                }
                if (!Objects.equals(list.get(i), "variable") && !Objects.equals(list.get(i), " probability")) {
                    v = (String) list.get(i + 3);
                    vs.add(v);
                }
                if (!Objects.equals(list.get(i), "variable") && !Objects.equals(list.get(i), " probability")) {
                    v = (String) list.get(i + 4);
                    vs.add(v);
                }

                vs.remove(vs.contains(keys));
                vs.remove("variable");
                vs.remove("probability");

                    variables.put(k, vs);

            }
        }
            return variables;
    }

    //    create a tree for the probabilities
    public static TreeMap<ArrayList, ArrayList> getProbabilities(ArrayList list) {
        TreeMap<ArrayList, ArrayList>probabilities = new TreeMap<>();
        String k = null;
        String v = null;
        ArrayList keys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), "probability")) {
                i = i + 1;
                if (!Objects.equals(list.get(i), "")) {
                    k = (String) list.get(i);
                    keys.add(k);
                    k = (String) list.get(i + 1);
                    keys.add(k);
                } if (!Objects.equals(list.get(i), "(\\W)")) {
                    k = (String) list.get(i);
                    keys.add(k);
                } else {
                    keys.add(1);
                }
            }
            if (Objects.equals(list.get(i), "[*0-9]")) {
                i = i + 1;
                ArrayList ps = new ArrayList();
                String state = (String) list.get(i - 1);
                ps.add(state);

                if (!Objects.equals(list.get(i), ";")) {
                    v = (String) list.get(i);
                    ps.add(v);
                }
                if (!Objects.equals(list.get(i), "")){
                    v = (String) list.get(i + 1);
                    ps.add(v);
                } else {
                    ps.add(0);
                }

                probabilities.put(keys, ps);
            }

        }
        return probabilities;
    }

//    @Override
////    need to compare the keys in order to place them in the tree
//    public int compare(Key o1, Key o2) {
//        return 0;
//    }
}