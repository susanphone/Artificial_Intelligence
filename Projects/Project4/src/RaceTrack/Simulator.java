package RaceTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Simulator {

    Car car;

    public Simulator(char[][] track, boolean reset, HashMap<String, int[]> policy){
        //Get starting positions, randomly select one, initialize car there
        ArrayList<int[]> start_positions = new ArrayList<>();
        for(int i = 0; i < track.length; i++){
            for(int j = 0; j < track[0].length; j++){
                if(track[i][j] == 'S'){
                    int[] temp = {j, i};
                    start_positions.add(temp);
                }
            }
        }
        
        Random rand = new Random();
        int[] p = start_positions.get(rand.nextInt(start_positions.size()));
        int[] v = {0,0};
        car = new Car(p, v);
        
        boolean finished = false;
        
        while(!finished){
            String temp = "";
            temp = temp.concat(String.valueOf(car.position[0]) + " ");
            temp = temp.concat(String.valueOf(car.position[1]) + " ");
            temp = temp.concat(String.valueOf(car.velocity[0]) + " ");
            temp = temp.concat(String.valueOf(car.velocity[1]));
            
            System.out.println();
            System.out.println("Key: " + temp);
            
            //get acceleration
            int[] acceleration = policy.get(temp);

            System.out.println("Acceleration: " + acceleration[0] + "," + acceleration[1]);
            
            //try to accelerate
            int acceleration_results = car.accelerate(acceleration[0], acceleration[1], track);
            
            
            switch(acceleration_results){
                case 1:
                    System.out.println("crashed");
                    if(reset){
                        finished = true;
                        System.out.println("Car crashed, reseting");
                    }
                    break;
                case 2:
                    System.out.println("Car reached the finish");
                    finished = true;
            }
            

        }
    }
}