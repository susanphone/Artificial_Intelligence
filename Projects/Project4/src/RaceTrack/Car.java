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
        position = p;
        velocity = v;
        int[] temp1 = {p[0], p[1]};
        position_history.add(temp1);
        int[] temp2 = {v[0], v[1]};
        velocity_history.add(temp2);
    }

    //Accelerates the car and updates velocity and position accordingly. Returns 0 for success, 1 for a crash, and 2 for a finish
    public int accelerate(int x, int y, char[][] track){
        int last_x = position[0];
        int last_y = position[1];
        
        //Checks to see if the car successfully accelerates, then updates velocity and position if true
        if(new Random().nextDouble() < 0.8){
            //Updates velocity based on current acceleration
            velocity[0] = x + velocity[0];
            velocity[1] = y + velocity[1];
            
            //Checks to see if either the x or y velocity has gone over 5 or under -5. If so, velocity is set back to the max or min
            if(velocity[0] > 5 || velocity[0] < -5){
                velocity[0] = 5 * (velocity[0]/Math.abs(velocity[0]));
            }
            if(velocity[1] > 5 || velocity[1] < -5){
                velocity[1] = 5 * (velocity[1]/Math.abs(velocity[1]));
            }
            //Updates position based on new velocity
            position[0] = velocity[0]+ position[0];
            position[1] = velocity[1] + position[1];
        }
        else{
            System.out.println("Car slipped");
            //Updates position based on old velocity
            position[0] = velocity[0]+ position[0];
            position[1] = velocity[1] + position[1];
        }
        
        double m  = ((double)(position[1] - last_y)) / ((double)(position[0] - last_x));
        double b = ((double)last_y) - (m * ((double)last_x));
        System.out.println("Old position: " + last_x + "," + last_y);
        System.out.println("Temporary position: " + position[0] + "," + position[1]);
        int current_x = last_x;
        int current_y = last_y;
        
        ArrayList<int[]> checks = new ArrayList<>();
        
        if(last_x < position[0]){
            for(double i = last_x; i <= (double) position[0] + 0.1; i += 0.1){
                int new_x = (int) i;
                int new_y = (int) (m*(double)i + b);
                boolean diff_x = new_x != current_x;
                boolean diff_y = new_y != current_y;
            
                if(diff_x){
                    int[] temp = {new_x, current_y};
                    checks.add(temp);
                    //System.out.println("Check at " + temp[0] + "," + temp[1]);
                }
                if(diff_y){
                    int[] temp = {current_x, new_y};
                    checks.add(temp);
                    //System.out.println("Check at " + temp[0] + "," + temp[1]);
                }
            }
        }
        else if(last_x > position[0]){
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
        else if(last_x == position[0]){
            if(last_y < position[1]){
                for(double i = last_y; i < position[1]; i += 0.1){
                    int new_x = (int) position[0];
                    int new_y = (int) i;
            
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
            if(last_y > position[1]){
                for(double i = last_y; i > position[1]; i -= 0.1){
                    int new_x = position[0];
                    int new_y = (int) i;
            
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
        }
        
        for(int i = 0; i < checks.size(); i++){
            int[] current = checks.get(i);
            if(track[current[1]][current[0]] == 'W'){
                if(checks.size()>0 && i > 0){
                    position[0] = checks.get(i-1)[0];
                    position[1] = checks.get(i-1)[1];
                }
                else{
                    position[0] = position_history.get(position_history.size()-1)[0];
                    position[1] = position_history.get(position_history.size()-1)[1];
                }
                
                int[] p = {position[0], position[1]};
                position_history.add(p);
                int[] v = {0, 0};
                velocity = v;
                velocity_history.add(v);
                System.out.println("Ending velocity: " + velocity[0] + " " + velocity[1]);
                System.out.println("Ending position: " + position[0] + "," + position[1]);
                return 1;
            }
            else if(track[current[1]][current[0]] == 'F'){
                position_history.add(position);
                velocity_history.add(velocity);
                System.out.println("Ending velocity: " + velocity[0] + " " + velocity[1]);
                System.out.println("Ending position: " + position[0] + "," + position[1]);
                return 2;
            }
        }
        
        System.out.println("Ending velocity: " + velocity[0] + " " + velocity[1]);
        System.out.println("Ending position: " + position[0] + "," + position[1]);
        int[] p = {position[0], position[1]};
        position_history.add(p);
        int[] v = {velocity[0], velocity[1]};
        velocity_history.add(v);
        return 0;
    }

}
