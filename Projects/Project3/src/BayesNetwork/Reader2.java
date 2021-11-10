package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;


public class Reader2 {
    public static ArrayList<String> cleanUpFile(File file) throws FileNotFoundException {
        Scanner bifScanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<String>();
        while (bifScanner.hasNext()) {
            String item = bifScanner.next();
            if (!Objects.equals(item, ")") && !Objects.equals(item, "(")
                    && !Objects.equals(item, "|") && !Objects.equals(item, "]")
                    && !Objects.equals(item, "[")) {
                list.add(item);
            }
        }
        return list;
    }

    public static TreeMap<String, ArrayList<String>> getVariables(ArrayList list) {
        TreeMap<String, ArrayList<String>> variables = new TreeMap<>();
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
                ArrayList<String> vs = new ArrayList<String>();
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

    //    Probabilities (Key: (List:Child and Parents), Value: (HashMap: (Key: State, Value: Doubles))
    public static Map<String, HashMap<ArrayList<String>, ArrayList<Double>>> getProbabilities(ArrayList list) {
        TreeMap variable = getVariables(list);
        String child = null;
        HashMap<ArrayList<String>, ArrayList<Double>> problem = new HashMap<>();
        Map<String, HashMap<ArrayList<String>, ArrayList<Double>>> probabilities = new Map<>() {
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
            public HashMap<ArrayList<String>, ArrayList<Double>> get(Object key) {
                return null;
            }

            @Override
            public HashMap<ArrayList<String>, ArrayList<Double>> put(String key, HashMap<ArrayList<String>, ArrayList<Double>> value) {
                return null;
            }

            @Override
            public HashMap<ArrayList<String>, ArrayList<Double>> remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends HashMap<ArrayList<String>, ArrayList<Double>>> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<HashMap<ArrayList<String>, ArrayList<Double>>> values() {
                return null;
            }

            @Override
            public Set<Entry<String, HashMap<ArrayList<String>, ArrayList<Double>>>> entrySet() {
                return null;
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        };
        ArrayList pc = new ArrayList();
        ArrayList<String> states = new ArrayList<>();
        ArrayList<Double> numbers = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), "probability")) {
                i = i + 1;
                if (variable.containsKey(list.get(i))) {
                    child = (String) list.get(i);
                    Object parent1 = null;
                    Object parent2 = null;
                    Object parent3 = null;
                    Object parent4 = null;
                    ArrayList parents = new ArrayList();
                    i = i + 1;
                    if (!Objects.equals(list.get(i), "{")) {
                        parent1 = list.get(i);
                        parents.add(parent1);
                        i = i + 1;
                    }
                    if (!variable.containsValue(list.get(i)) && !Objects.equals(list.get(i), "{")) {
                        parent2 = list.get(i);
                        parents.add(parent2);
                        i = i + 1;
                    }
                    if (!variable.containsValue(list.get(i)) && !Objects.equals(list.get(i), "{")) {
                        parent3 = list.get(i);
                        parents.add(parent3);
                        i = i + 1;
                    }
                    if (!variable.containsValue(list.get(i)) && !Objects.equals(list.get(i), "{")) {
                        parent4 = list.get(i);
                        parents.add(parent4);
                    }
                    pc.add(child);
                    pc.add(parents);
                    pc.toString();
                    System.out.println(pc);
                    i= i + 1;
                }
                String state1 = null;
                String state2 = null;
                String state3 = null;
                String state4 = null;
                if (!Objects.equals(list.get(i), "}")) {
                    state1 = (String) list.get(i);
                    states.add(state1);
                    i = i + 1;
                }
                if (!Objects.equals(list.get(i), "}") && list.get(i) != "^[0-9].[0-9][0-9]") {
                    state2 = (String) list.get(i);
                    states.add(state2);
                    i = i + 1;
                }
                if (Objects.equals(list.get(i), "[^A-Za-z]")) {
                    state3 = (String) list.get(i);
                    states.add(state3);
                    i = i + 1;
                }
                if (Objects.equals(list.get(i), "[(^A-Za-z)]")) {
                    state4 = (String) list.get(i);
                    states.add(state4);
                    i = i + 1;
                }
                if (!Objects.equals(list.get(i), "}") && !Objects.equals(list.get(i), "[^A-Za-z]")) {
                    double p1 = 0.0;
                    double p2 = 0.0;
                    double p3 = 0.0;
                    double p4 = 0.0;
                    if (!Objects.equals(list.get(i), "}")) {
                        p1 = (Double) list.get(i);
                        numbers.add(p1);
                        i = i + 1;
                    }
                    if (!Objects.equals(list.get(i), "}")) {
                        p2 = (Double) list.get(i);
                        numbers.add(p2);
                        i = i + 1;
                    }
                    if (!Objects.equals(list.get(i), "}")) {
                        p3 = (Double) list.get(i);
                        numbers.add(p3);
                        i = i + 1;
                    }
                    if (!Objects.equals(list.get(i), "}")) {
                        p4 = (Double) list.get(i);
                        numbers.add(p4);
                    }
                    System.out.println(states);
                    System.out.println(numbers);
                    problem.put(states, numbers);
                    states = new ArrayList<>();
                    numbers = new ArrayList<>();

                }
                probabilities.put(pc.toString(), problem);
                pc = new ArrayList();
                problem = new HashMap<>();
            }
        }
        return probabilities;
    }
}
