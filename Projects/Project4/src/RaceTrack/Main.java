package RaceTrack;

public class Main {
    public static void main(String[] args){

        char[][] track = Reader.trackIn("L-track.txt");

        for(int i = 0; i < track.length; i++){
            for(int j = 0; j < track[i].length; j++){
                System.out.print(track[i][j]);
            }
            System.out.println();
        }

    }
}
