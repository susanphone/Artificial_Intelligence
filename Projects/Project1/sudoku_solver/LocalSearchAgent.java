package sudoku_solver;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.Random;
public class LocalSearchAgent {
    public static void simulatedAnnealing() {
        Random randomValue = new Random();
        Memory memory = new Memory();
        Board.printBoard(memory.board.board);
        int initialProbability = 1;
        int currentProbability = 1;// number of remaining spaces


        //read in the board
        for(int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                if (memory.board.board[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        //Sets the space at the current index (i,j) to num
                        memory.board.board[i][j] = num;
                    }
                }
                // choose a random value between 1 and 9 at position (i,j)
                for(int currentValue = 0; currentValue < 10; currentProbability++) {

                    // assign a value to the random spot and keep that value there is the probability increases
                    if(initialProbability > currentProbability) {
                        memory.board.board[i][j] = currentValue;
                    }
                    // otherwise choose another random number and see if the next value is a better fit than the current value
                    int nextInt = randomValue.nextInt();
                    int deltaE = (currentProbability - initialProbability)/2;
                    if(deltaE > 1) {
                        currentValue = nextInt;
                    } else {
                        nextInt = currentValue;
                    }
            }
        }
        }
    }
}
