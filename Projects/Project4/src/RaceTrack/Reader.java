

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args) {
        char[][] track = trackIn("L-track.txt");
        
        for(int i = 0; i < track.length; i++){
                for(int j = 0; j < track[i].length; j++){
                    System.out.print(track[i][j]);
                }
                System.out.println();
            }
    }


    //Given a file name for a track, returns a char array with each character in a subarray representing a space on the track
    //‘W’ = wall, ‘R’ = road, ‘S’ = start, and ‘F’ = finish
    public static char[][] trackIn(String file_name) {
        try {
            //Creates a scanner to read the file
            File track_file = new File(file_name);
            Scanner scan = new Scanner(track_file);
            
            //Gets the size of the track, given by the first line in the file
            String size = scan.nextLine();
            int x = Integer.parseInt(size.substring(3));
            int y = Integer.parseInt(size.substring(0,2));
            
            //Creates a temporary storage for the strings of the file
            String[] file_lines = new String[x];
            
            
            int count = 0;
            while (scan.hasNextLine()) {
                file_lines[count] = scan.nextLine();
                count++;
            }
            
            //Seperates the characters within each string of the track and switches them to our preferred notation
            char[][] track = new char[y][x];
            for(int i = 0; i < y; i++){
                String current = file_lines[i];
                for(int j = 0; j < current.length(); j++){
                    switch(current.charAt(j)){
                        case '#':
                            track[i][j] = 'W';
                            break;
                        case '.':
                            track[i][j] = 'R';
                            break;
                        case '\n':
                            break;
                        default:
                            track[i][j] = current.charAt(j); 
                    }
                }
            }
            
            //Closes the file and returns the 2D track array
            scan.close();
            return track;
        } 
        
        //Catches errors
        catch (Exception e) {
            System.out.println("Error reading in track, returning null");
        }
        return null;
    }
}
