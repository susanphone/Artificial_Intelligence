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


    public static TreeMap<ArrayList, Map> getProbabilities(File file, TreeMap variables) throws FileNotFoundException {
        TreeMap<ArrayList, Map> probabilities = new TreeMap<>();
        Scanner bifScanner = new Scanner(file);
        ArrayList list = new ArrayList();
        while (bifScanner.hasNext()) {
            String item = bifScanner.next();
            list.add(item);
        }
        Map<ArrayList, ArrayList> probMap = new Map<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public ArrayList get(Object key) {
                return null;
            }

            @Override
            public ArrayList put(ArrayList key, ArrayList value) {
                return null;
            }

            @Override
            public ArrayList remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends ArrayList, ? extends ArrayList> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<ArrayList> keySet() {
                return null;
            }

            @Override
            public Collection<ArrayList> values() {
                return null;
            }

            @Override
            public Set<Entry<ArrayList, ArrayList>> entrySet() {
                return null;
            }
        };
        String[] parents = null;
        String child = null;

        for (int i = 515; i < list.size(); i++){
            ArrayList pc = new ArrayList();
            if (Objects.equals(list.get(i), "probability")){
                while (!Objects.equals(list.get(i), "}")) {
                    if (list.get(i).equals("|")) {
                        while (!list.get(i).equals("}")) {
                            parents = (String[]) list.get(i);
                            i++;
                        }
                    } else {
                        child = (String) list.get(i);
                    }
                    i++;
                }
                pc.add(parents);
                pc.add(child);
            }
            ArrayList states = new ArrayList();
            if (Objects.equals(list.get(i), variables.values())) {
                states.add(list.get(i));
            }
            ArrayList prob = new ArrayList();
            while (!Objects.equals(list.get(i), "}")) {
                prob.add(list);
            }

            probMap.put(states, prob);
            probabilities.put(pc, probMap);
        }
        return probabilities;
    }

}
