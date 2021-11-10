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

    //    cleans up file and strips away any unnecessary characters
    public void loadFile() throws FileNotFoundException {
        Scanner bifScanner = new Scanner(file);

        // For each "word"
        while (bifScanner.hasNext()) {
            String item = bifScanner.next();
            if (!disallowedItems.contains(item)) {
                rawDataset.add(item);
            }
        }
    }

    private String varKey = "variable";
    private String probKey = "probability";


    public TreeMap<String, Variable> getVariables() {
        TreeMap<String, Variable> variables = new TreeMap<>();

        for (int i = 0; i < rawDataset.size(); i++) {
            // Add variables
            if (rawDataset.get(i).equals(varKey)) {
                var name = rawDataset.get(i + 1);
                ArrayList<String> domains = new ArrayList<>();

                // Jog forward to the start of the domains
                i += 7;
                String curWord = rawDataset.get(i);
                while (!curWord.equals("};")) {
                    curWord = curWord.replace(",", "");
                    domains.add(curWord);

                    i++;
                    curWord = rawDataset.get(i);
                }

                String[] domainArray = new String[domains.size()];
                domainArray = domains.toArray(domainArray);

                variables.put(name, new Variable(name, domainArray, new String[0], new String[0]));
            }

            // adding in the probability and parent-child relationships
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
                    StringBuilder domainBuilder = new StringBuilder(curWord);
                    if (curWord.endsWith(",")) {
                        i++;
                        while(true) {
                            var word = rawDataset.get(i);
                            domainBuilder.append(word);
                            if (word.endsWith(")")) {
                                break;
                            }
                            i++;
                        }
                    }

                    var probabilities = new ArrayList<Double>();

                    while(true) {
                        i++;
                        var prob = rawDataset.get(i);
                        var endOfLine = false;
                        if (prob.contains(";")) {
                            endOfLine = true;
                        }


                        if (prob.contains(",")) {
                            prob = prob.replace(",", "");
                            prob = prob.replace(";", "");
                            probabilities.add(Double.parseDouble(prob));
                        }
                        if (endOfLine) {
                            break;
                        }
                    }
                    if (domainBuilder.toString().equals("table")) {
                        domainBuilder = new StringBuilder();
                        for (String v: childVariable.states) {
                            domainBuilder.append(v + " ");
                        }
                    }
                    childVariable.probabilities.put(domainBuilder.toString(), probabilities);

                    i++;
                    curWord = rawDataset.get(i);
                }
            }
        }
        return variables;
    }
}