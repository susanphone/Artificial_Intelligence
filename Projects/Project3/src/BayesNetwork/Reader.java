package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Reader {
    public HashMap readBIF(File file) throws FileNotFoundException {
        HashMap scenario = new HashMap();
        HashMap probabilities = new HashMap();
        Scanner bifScanner = new Scanner(file);
        while (bifScanner.hasNextLine()){
            String data = bifScanner.nextLine();
            data.split("}");
            if(bifScanner.hasNext("}")){
                continue;
            }
            if (bifScanner.hasNext("variable")) {
                String[] var = data.split(" ");
                scenario.get(var[1]);
                bifScanner.nextLine();
                if(bifScanner.hasNext("  tyoe")) {
                    String[] states = data.split(" ");
                    for (int i = 9; i < states.length; i++) {
                        scenario.replace(var, "", states[i]);
                        if (bifScanner.hasNext("}"));
                        break;
                    }

                }
            }
            if (bifScanner.hasNext("probability")) {
                String[] prob = data.split("\n");
                for (int i = 0; i < prob.length; i++){
                    if (i == scenario.containsKey()) {
                        probabilities.get(i);
                    }
                    if (i == scenario.containsValue()) {
                        String[] p = data.split(" ");
                        probabilities.values(p[i]);
                    }
                }
            }
            bifScanner.close();
        }
    /* TODO:
    1. read in file
    2. split file
        if line starts with variable
        if line starts with probability
    3a. for variable type discrete[i] = number of states
        and {i,j,k} are the states.
    3b. for probaility (var 1 given var 2)
        (state1) probability1, probabilityi
        (statei) probability1, probabilityi
    4. store we variable and corresponding probailities into a haspmap
     */

        //returns hashmap
        return scenario;
    }
}
