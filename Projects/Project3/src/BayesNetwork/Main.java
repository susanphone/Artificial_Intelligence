package BayesNetwork;

import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("alarm.bif");
        ArrayList data;
        data = Reader.cleanUpFile(file);


        System.out.println(data);
//        String d  = Arrays.toString(data);
//        String[] str = d.split(" ");
//        System.out.println(Arrays.toString(str));
        TreeMap<String, ArrayList<String>> dat = Reader.getVariables(file);
        System.out.println(dat);
        Map<String, ArrayList<String>> probs = Reader.getProbabilities(file, dat);
        System.out.println(probs);

    }

}
