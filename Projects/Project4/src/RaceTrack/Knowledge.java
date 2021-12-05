package RaceTrack;

public class Knowledge {

    public char[][] initializeKnowledge() {
        char[][] matrix = Reader.trackIn("l");
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix[1].length; y++) {
                // set state as unknown and action as 0.
                char state = 'U';
                int action = 0;
            }
        }
        return matrix;
    }
    public char[][] updateKnowledge(char[][] matrix, double[] position, int action, char state){
        double x = position[0];
        double y = position[1];
        for (double i = 0.0; i < matrix[0].length; i++){
            for (double j = 0.0; i < matrix[1].length; i++){
                if (matrix[(int) position[0]][(int) position[1]] == matrix[(int) i][(int) j]) {
                    matrix[(int) i][j] = new char[]{state, (char) action};
                }
            }
        }
        return matrix;
    }
}
