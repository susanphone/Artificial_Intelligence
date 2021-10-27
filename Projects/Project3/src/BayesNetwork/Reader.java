package BayesNetwork;

import javax.sql.rowset.serial.SerialArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.sql.Array;
import java.util.*;

public class Reader {

    public static ArrayList cleanUpFile(File file) throws FileNotFoundException {
        Scanner bifScanner = new Scanner(file);
        ArrayList list = new ArrayList();
        while (bifScanner.hasNextLine()) {
            String data = bifScanner.nextLine();
//                System.out.println(data);

            String d = data.replaceAll("[^a-zA-Z0-9. ]", "");
//            System.out.println(d);
            list.add(d);

        }
        return list;
    }

    public HashMap<String, String> getVariables(ArrayList list) {
        Object k = null;
        Object val = null;
        HashMap<String, String> variables = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            if (list.get(i) == "variable") {
                k = list.get(i+1);
            }

            if (list.get(i) == "discrete") {
                val = list.get(i+1);
                variables.put((String) k, (String) val);
            }

        }
        return variables;
    }

    public HashMap<String, String> getProbabilities(ArrayList list) {
        Object k = null;
        Object val = null;
        HashMap<String, String> probabilities = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            if (list.get(i) == "probability") {
                k = list.get(i + 1);
            }
            if (list.get(i) == "[^0-9.]") {
                val = list.get(i + 1);
                probabilities.put((String)k, (String)val);
            }

        }
        return probabilities;
    }

}
