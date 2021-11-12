package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader {

    private ArrayList<String> disallowedItems = new ArrayList<>(Arrays.asList("|", "]", "[", ","));
    private File file;

    private ArrayList<String> rawDataset;
    private TreeMap<String, ArrayList<String>> variables;

    public Reader(File file) {
        this.file = file;
        this.rawDataset = new ArrayList<>();
        this.variables = new TreeMap<>();
    }

    // Reads in and parses the file
    public void loadFile() throws FileNotFoundException {
        Scanner bifScanner = new Scanner(file);

        // For each "word"
        while (bifScanner.hasNext()) {
            String word = bifScanner.next();
            if (!disallowedItems.contains(word)) {
                rawDataset.add(word);
            }
        }
    }

    private String varKey = "variable";
    private String probKey = "probability";


    public ArrayList<Variable> getVariables() {
        TreeMap<String, Variable> variables = new TreeMap<>();

        for (int i = 0; i < rawDataset.size(); i++) {
            // Adds variables
            if (rawDataset.get(i).equals(varKey)) {
                var name = rawDataset.get(i + 1);
                ArrayList<String> states = new ArrayList<>();

                // moves forward to the start of the states
                i += 7;
                String curWord = rawDataset.get(i);
                // while more states, remove commas and add to arraylist
                while (!curWord.equals("};")) {
                    curWord = curWord.replace(",", "");
                    states.add(curWord);
                    i++;
                    curWord = rawDataset.get(i);
                }

                String[] statesArray = new String[states.size()];
                // changes arraylist to string array
                statesArray = states.toArray(statesArray);

                // adds known contents to the variable
                variables.put(name, new Variable(name, statesArray, new String[0], new String[0]));
            }

            // adds in the probability and parent-child relationships
            if (rawDataset.get(i).equals(probKey)) {
                i += 2;
                var child = rawDataset.get(i);
                var parents = new ArrayList<String>();
                do {
                    i++;
                    var potentialParent = rawDataset.get(i);
                    potentialParent = potentialParent.replace(",", "");
                    // Catches the no parent case
                    if (potentialParent.equals(")")) {
                        i++;
                        break;
                    }

                    parents.add(potentialParent);
                } while (rawDataset.get(i) != ")");


                // Adds child relationship to parents
                for (String parentName : parents) {
                    var parent = variables.get(parentName);
                    parent.addChild(child);
                }


                // Adds parent relationships to child
                var childVariable = variables.get(child);
                childVariable.addParents(parents);

                // Adds probability to child
                i += 1;
                var curWord = rawDataset.get(i);
                while (!curWord.equals("}")) {
                    StringBuilder statesBuilder = new StringBuilder(curWord);
                    if (curWord.endsWith(",")) {
                        i++;
                        while(true) {
                            var word = rawDataset.get(i);
                            statesBuilder.append(word);
                            if (word.endsWith(")")) {
                                break;
                            }
                            i++;
                        }
                    }

                    var probabilities = new ArrayList<Double>();

                    // while data contains more probabilities
                    var endOfLine = false;

                    while(!endOfLine) {
                        i++;
                        var prob = rawDataset.get(i);
                        if (prob.contains(";")) {
                            endOfLine = true;
                            prob = prob.replace(";", "");
                            probabilities.add(Double.parseDouble(prob));
                        }

                        // removes commas and semi-colons and parses doubles
                        if (prob.contains(",")) {
                            prob = prob.replace(",", "");
                            probabilities.add(Double.parseDouble(prob));
                        }

                    }

                    // when the variable has no parents, get states of the variable
                    if (statesBuilder.toString().equals("table")) {
                        statesBuilder = new StringBuilder();
                        for (String v: childVariable.states) {
                            statesBuilder.append(v + " ");
                        }
                    }
                    // adds states and probabilities to variable
                    childVariable.probabilities.put(statesBuilder.toString(), probabilities);

                    i++;
                    curWord = rawDataset.get(i);
                }
            }
        }

        // change TreeMap to ArrayList
        ArrayList <Variable> variablesList = new ArrayList<>();
        // add variables to array list
        variablesList.addAll(variables.values());
        // returns arraylist of variables
        return variablesList;
    }
}