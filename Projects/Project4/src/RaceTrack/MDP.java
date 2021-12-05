package RaceTrack;

import java.util.ArrayList;

public class MDP {

    ArrayList<int[]> states = new ArrayList<>();
    ArrayList<Integer> actions = new ArrayList<>();
    ArrayList<Integer> discount = new ArrayList<>();

    public MDP(){

    }

    public String transition(String state, String action){

        return state;
    }

    public int reward(String state, String action){
        int temp = 0;
        return temp;
    }
}
