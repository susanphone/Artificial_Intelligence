package sudoku_solver;

import com.sun.tools.javac.comp.Check;

import java.util.Random;
import java.lang.Math;
//import statistics
public class LocalSearchAgent {
    int row;
    int column;
    Memory memory = new Memory();
    int k =1;
    public void LocalSearchAgent(int row, int column, int memory) {
        this.row = row;
        this.column = column;
        memory.board.board = memory;
    }
    public static void simulatedAnnealing() {
        Random randomValue = new Random();
        Memory memory = new Memory();
        Board.printBoard(memory.board.board);
        int initialProbability = 1;
        int currentProbability = 1;// number of remaining spaces
        int i = 0, j = 0;
        int position = memory.board.board[i][j];

        //read in the board
        for(int row =0; i < 9; i++) {
            //Loops through j values
            for (int col= 0; j < 9; j++) {
                //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                if (memory.board.board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        //Sets the space at the current index (i,j) to num
                        memory.board.board[row][col] = num;
                    }
                }
                // choose a random value between 1 and 9 at position (i,j)
                for(position = 1; randomValue.nextInt() < 10; position++) {

                    // assign a value to the random spot and keep that value there is the probability increases
                    if(initialProbability > currentProbability) {
                        memory.board.board[i][j] = randomValue.nextInt();
                    }
                    // otherwise choose another random number and see if the next value is a better fit than the current value
                    int nextInt = randomValue.nextInt();
                    int deltaE = (currentProbability - initialProbability)/2;
                    if(deltaE > 1) {
                        position = randomValue.nextInt();
                    } else {
                        position = nextInt;
                    }
            }
        }
        }
    }
    // this is using minimum conflict
    public int costFunction(int row, int column, int memory) {
        int i;
        int numberOfErrorsCalculation = 0;
        int numberOfErrorsColAndRow = (9 - length(Checker.checkRows(this.row, this.column))) + (9 - length(Checker.checkCols([this.row][this.column])))
        ;
        numberOfErrorsCalculation += numberOfErrorsColAndRow;
        return numberOfErrorsCalculation;
    }
    public int[] randomGridSelection(int row, int column, int memory) {

        return [row1, column1], [row2, column2]
    }

}
