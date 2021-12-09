package RaceTrack;
import java.util.ArrayList;
import java.util.Random;

public class Simulator {

    Car car;
    char[][] track;

    public Simulator(String file_name, boolean reset){
        track = Reader.trackIn(file_name);
        
        //Get starting positions, randomly select one, initialize car there
        ArrayList<int[]> start_positions = new ArrayList<>();
        for(int i = 0; i < track.length; i++){
            for(int j = 0; j < track[0].length; j++){
                if(track[i][j] == 'S'){
                    int[] temp = {i,j};
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
            //get acceleration
            int[] temp = {0,0};

            //try to accelerate
            int acceleration_results = car.accelerate(temp[0], temp[1], track);
            
            
            switch(acceleration_results){
                case 1:
                    if(reset){
                        finished = true;
                        System.out.println("Car crashed, reseting");
                    }
                    else{
                        int [] last_position = car.position_history.get(car.position_history.size() - 1);
                        car.position = last_position;
                        
                        int [] last_velocity = car.velocity_history.get(car.velocity_history.size() - 1);
                        car.velocity = last_velocity;
                    }
                    break;
                case 2:
                    System.out.println("Car reached the finish");
                    finished = true;
            }
            

        }
    }
}