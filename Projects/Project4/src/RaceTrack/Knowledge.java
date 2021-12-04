package RaceTrack;

public class Knowledge {
    char[][] matrix;
    public Knowledge(char[][] matrix) {
        this.matrix = matrix;
    }

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
    public static char[][] updateKnowledge(char[][] matrix, double[] position, int action){
        double x = position[0];
        double y = position[1];
        for (double i = 0.0; i < matrix[0].length; i++){
            for (double j = 0.0; i < matrix[1].length; i++){
                if (matrix[(int) position[0]][(int) position[1]] == matrix[(int) i][(int) j]) {
                    matrix[(int) i][j] = new char[]{QLearning.updateState(), (char) action};
                }
            }
        }
        return matrix;
    }
}
