//package BayesNetwork;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class ReaderTest {
//
//    private String word;
//
//    public static void main(String[] args) throws FileNotFoundException {
//        try {
//            // Scanner object is constructed with new File() for file input
//            File file = new File("test.bif");
//            Scanner bifScanner = new Scanner(file);
//            HashMap<String, String> scenario = new HashMap<String, String>();
//            HashMap<String, String> probabilities = new HashMap<>();
//
//            while (bifScanner.hasNextLine()) {
//                String data = bifScanner.nextLine();
////                System.out.println(data);
//
//                String d = data.replaceAll("[^a-zA-Z0-9. ]", "");
//                System.out.println(d);
//
//                String var = null;
//                String val = null;
//                String prob = null;
//                String p = null;
//
////                // for keys
////                if (d.equals("variable") || d.equals("probability")) {
////                    for (int i = 0; i < d.length(); i++) {
//
//                        String[] word = d.split("null");
//                         System.out.println(Arrays.toString(word));
////                        System.out.println(word[i]);
//                        if (Objects.equals(word, "variable")) {
//                            var = this.word;
//                             System.out.println(var);
//                        }
////
////                        if (Objects.equals(word[i], "probability")) {
////                            while (i < d.length()) {
////                                if (Objects.equals(word[i], "probability")) {
////                                } else {
////                                    //                            probabilities.put(prob, null);
////                                    prob = word[i];
////                                    i++;
////                                }
////                            }
////                        }
////                    }
////                }else {
////                    // for values
////                    for (int i = 0; i < d.length(); i++) {
////                        String[] word = d.split(" ");
////                            if (Objects.equals(word[i], "type")) {
////                                System.out.println(word[i]);
////                                word[i] = word[i+1];
////
////                                if (Objects.equals(word[i], "[^0-9]")) {
////                                    word[i] = word[i + 1];
////                                    while (!Objects.equals(word[i], "\n")) {
////                                        val = word[i];
////                                        System.out.println(var + " " + val);
//////                                        scenario.put(var, val);
////                                        i++;
////                                    }
////                                }
////                            }
////                            if (Objects.equals(word[i], "[^0-9.]")) {
////                                while (i < d.length()) {
////                                    p = word[i];
//////                                    probabilities.put(prob, p);
////                                    i++;
////                                }
////                            }
////                    }
////
////                }
//            }
//            bifScanner.close();
////            System.out.println(scenario.keySet());
////            System.out.println(scenario.values());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
