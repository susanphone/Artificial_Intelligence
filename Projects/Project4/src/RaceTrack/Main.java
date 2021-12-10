package RaceTrack;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){

        char[][] track = Reader.trackIn("L-track.txt");

        for(int i = 0; i < track.length; i++){
            for(int j = 0; j < track[i].length; j++){
                System.out.print(track[i][j]);
            }
            System.out.println();
        }

        //initialize the possible velocities
        ArrayList<int[]> velocities = new ArrayList<>();

        for(int x = -5; x < 6; x++){
            for(int y = -5; y < 6; y++){
                int [] velocity = {x, y};
                velocities.add(velocity);
            }
        }

        //create a new Markov Decision Process
        MDP currentTrack = new MDP();

        //add the coordinates for the possible states to the states ArrayList in the MDP
        for(int i = 0; i < track.length; i++){
            for(int j = 0; j < track[i].length; j++){

                //check if coordinates are a valid position (starting point, road, or finish line)
                if (track[i][j] == 'S' || track[i][j] == 'R' || track[i][j] == 'F'){
                    for(int[] v : velocities){
                        int[] state = {j, i, v[0], v[1]};
                        currentTrack.states.add(state);
                    }
                    if(track[i][j] == 'F'){
                        int[] finishPos = {j, i};
                        currentTrack.finishPositions.add(finishPos);
                    }
                }
            }
        }

        //set-up the action possibilities
        currentTrack.actions =  currentTrack.setActions();

        //track dimensions
        int[] trackDim = {track[0].length, track.length};

        /*
         * OUPUT FROM VALUE ITERATION
         */

        HashMap<int[], ActionValue>  optimalPolicy = ValueIteration.decision(trackDim, currentTrack, 0.001, 0.7);

    }

}
