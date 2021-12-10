package RaceTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        char[][] track = Reader.trackIn("L-track.txt");

        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length; j++) {
                System.out.print(track[i][j]);
            }
            System.out.println();
        }

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
                    }
                    if (track[i][j] == 'F') {
                        int[] finishPos = {j, i};
                        currentTrack.finishPositions.add(finishPos);
                    }
                }
            }
        }

        //set-up the action possibilities
        currentTrack.actions = currentTrack.setActions();

        //track dimensions
        int[] trackDim = {track[0].length, track.length};

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

        HashMap<int[], ActionValue>  optimalPolicy = ValueIteration.decision(trackDim, currentTrack, 0.001, 0.7);

        /* QLearning starts with initializing a map of the knowledge, the states are all unknown
         * and the action is set as zero, which means no change to speed. */
//        HashMap<int[], char[]> knowledge = new HashMap<>();
//        int x = trackDim[0];
//        int y = trackDim[1];
//        int[] xy = {x, y};
//        char[] states = {'S', 'R', 'W', 'F'};
//        QLearning ql = new QLearning(states, xy);
//        HashMap<int[], char[]> growingKnowledge = ql.runLearning(states, 1, xy);
//        for (int[] gk : growingKnowledge.keySet()) {
//            String key = gk.toString();
//            System.out.println(key);
//        }
//        for (char[] sa : growingKnowledge.values()) {
//            String value = sa.toString();
//            System.out.println(value);
//        }
    }

}
