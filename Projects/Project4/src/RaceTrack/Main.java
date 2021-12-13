package RaceTrack;

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        char[][] track = Reader.trackIn("L-track.txt");

        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length; j++) {
                System.out.print(track[i][j]);
            }
            System.out.println();
        }
        
        /*

        //initialize the possible velocities
        ArrayList<int[]> velocities = new ArrayList<>();

        for (int x = -5; x < 6; x++) {
            for (int y = -5; y < 6; y++) {
                int[] velocity = {x, y};
                velocities.add(velocity);
            }
        }

        //create a new Markov Decision Process
        MDP currentTrack = new MDP();

        //add the coordinates for the possible states to the states ArrayList in the MDP
        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length; j++) {

                //check if coordinates are a valid position (starting point, road, or finish line)
                if (track[i][j] == 'S' || track[i][j] == 'R' || track[i][j] == 'F') {
                    for (int[] v : velocities) {
                        int[] state = {j, i, v[0], v[1]};
                        currentTrack.states.add(state);
                        if (track[i][j] == 'F') {
                            currentTrack.finishPositions.add(state);
                        }
                        if (track[i][j] == 'S') {
                            currentTrack.startPositions.add(state);
                        }
                    }
                }
            }
        }

        //set-up the action possibilities
        currentTrack.actions = currentTrack.setActions();

        //track dimensions
        int[] trackDim = {track[0].length, track.length}; */

        /*
         * OUTPUT FROM VALUE ITERATION
         * value iteration produces a HashMap for the entire policy of the track, where the key is the state,
         * stored as an integer array where arr[0] is the x position, arr[1] is the y position, arr[2] is the x
         * velocity, and arr[3] is the y velocity
         * the value is a ActionValue object, which has the corresponding optimal action and reward value for
         * any given state, where the action is an integer array where arr[0] is the x acceleration and arr[1] is the
         * y acceleration
         * Given the starting position you should be able to use my optimalPolicy to help the car decide what is the
         * optimal action the take (the action attribute in the ActionValue object)
         */

<<<<<<< HEAD
        HashMap<int[], ActionValue>  optimalPolicy = ValueIteration.decision(currentTrack, 0.001, 0.7, 0);
=======
        HashMap<int[], ActionValue>  optimalPolicy = ValueIteration.decision(currentTrack, 0.0000001, 0.7, 0);

>>>>>>> ba3a44cbc54f0203506d64e804c2eb1571a91d78
        System.out.println(optimalPolicy.size());

        try{
            FileWriter myFile = new FileWriter("VI-L-track-Policy.txt");

            for(int[] key: optimalPolicy.keySet()){
                myFile.write(key[0] + " " + key[1] + " " + key[2] + " " + key[3] + " " +
                        optimalPolicy.get(key).action[0] + " " + optimalPolicy.get(key).action[1]);
                myFile.write("\n");
            }

            myFile.close();

        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        */
        
        
        
        

        HashMap<String, int[]>  optimalPolicy = new HashMap<>();

        try {
            File myObj = new File("VI-L-track-Policy.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int[] fileData = new int[6];
                int i = 0;

                for(String s: data.split(" ")){
                    //System.out.println("String: " + s);
                    int sInt = Integer.parseInt(s);
                    //System.out.println(sInt);
                    fileData[i] = sInt;
                    i++;
                }

                int[] states = new int[4];
                int[] action = new int[2];

                states[0] = fileData[0];
                states[1] = fileData[1];
                states[2] = fileData[2];
                states[3] = fileData[3];

                action[0] = fileData[4];
                action[1] = fileData[5];

                String temp = "";
                temp = temp.concat(String.valueOf(states[0]) + " ");
                temp = temp.concat(String.valueOf(states[1]) + " ");
                temp = temp.concat(String.valueOf(states[2]) + " ");
                temp = temp.concat(String.valueOf(states[3]));
                
                optimalPolicy.put(temp, action);

                if(states[0] == 1 && states[1] == 6 && states[2] == 0 && states[3] == 0){
                    System.out.println("X Action: " + action[0]);
                    System.out.println("Y Action: " + action[1]);
                }


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //System.out.println(optimalPolicy.size());
        
        new Simulator(track, false, optimalPolicy);

    }

}
