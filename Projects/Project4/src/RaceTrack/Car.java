package RaceTrack;

import java.util.Random;
import java.util.ArrayList;

public class Car {

    int[] position; //{x, y}
    int[] velocity; // {x', y'}
    
    //Used to store timesteps of position and velocity
    ArrayList<int[]> position_history = new ArrayList<>();
    ArrayList<int[]> velocity_history = new ArrayList<>();

    public Car(int[] p, int[] v){
        this.position = p;
        this.velocity = v;
        position_history.add(this.position);
        velocity_history.add(this.position);
    }

    //Accelerates the car and updates velocity and position accordingly. Returns 0 for success, 1 for a crash, and 2 for a finish
    public int accelerate(int x, int y, char[][] track){
        int last_x = position[0];
        int last_y = position[1];
        
        //Checks to see if the car successfully accelerates, then updates velocity and position if true
        if(new Random().nextDouble() < 0.8){
            //Updates velocity based on current acceleration
            velocity[0] = x + velocity_history.get(velocity_history.size() - 1)[0];
            velocity[1] = x + velocity_history.get(velocity_history.size() - 1)[1];
            
            //Checks to see if either the x or y velocity has gone over 5 or under -5. If so, velocity is set back to the max or min
            if(velocity[0] > 5 || velocity[0] < -5){
                velocity[0] = 5 * (velocity[0]/Math.abs(velocity[0]));
            }
            if(velocity[1] > 5 || velocity[1] < -5){
                velocity[2] = 5 * (velocity[1]/Math.abs(velocity[1]));
            }
        
            //Updates position based on new velocity
            position[0] = velocity[0]+ position_history.get(position_history.size() - 1)[0];
            position[1] = velocity[1] + position_history.get(position_history.size() - 1)[1];
        }
        
        //Adds the velocity and position at the current timestep to the history list
        velocity_history.add(velocity);

        
        double m  = ((double)(position[1] - last_y)) / ((double)(position[0] - last_x));
        double b = ((double)last_y) - (m * ((double)last_x));
        
        
        int current_x = last_x;
        int current_y = last_y;
        
        ArrayList<int[]> checks = new ArrayList<>();
        
        if(last_x < position[0]){
            for(double i = last_x; i < position[0]; i += 0.1){
                int new_x = (int) i;
                int new_y = (int) (m*i + b);
            
                boolean diff_x = new_x != current_x;
                boolean diff_y = new_y != current_y;
            
                if(diff_x){
                    int[] temp = {new_x, current_y};
                    checks.add(temp);
                }
                if(diff_y){
                    int[] temp = {current_x, new_y};
                    checks.add(temp);
                }
            }
        }
        if(last_x > position[0]){
            for(double i = last_x; i > position[0]; i -= 0.1){
                int new_x = (int) i;
                int new_y = (int) (m*i + b);
            
                boolean diff_x = new_x != current_x;
                boolean diff_y = new_y != current_y;
            
                if(diff_x){
                    int[] temp = {new_x, current_y};
                    checks.add(temp);
                }
                if(diff_y){
                    int[] temp = {current_x, new_y};
                    checks.add(temp);
                }
            }
        }
        
        for(int i = 0; i < checks.size(); i++){
            int[] current = checks.get(i);
            if(track[current[0]][current[1]] == 'W'){
                if(checks.size()>0){
                    position = checks.get(i-1);
                    position_history.add(position);
                }
                else{
                    position = position_history.get(position_history.size()-1);
                    position_history.add(position);
                }
                return 1;
            }
            else if(track[current[0]][current[1]] == 'F'){
                position_history.add(position);
                return 2;
            }
        }
        position_history.add(position);
        return 0;
    }

}
