package BayesNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("alarm.bif");
        ArrayList data;
        data = Reader.cleanUpFile(file);


        System.out.println(data);
//        String d  = Arrays.toString(data);
//        String[] str = d.split(" ");
//        System.out.println(Arrays.toString(str));
        TreeMap<String, ArrayList> dat = Reader.getVariables(data);
        System.out.println(dat);
        TreeMap<ArrayList, ArrayList> probs = Reader.getProbabilities(data);
        System.out.println(probs);

    }

}
