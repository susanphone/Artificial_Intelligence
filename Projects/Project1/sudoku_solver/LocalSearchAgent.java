package sudoku_solver;

import java.io.File;
import java.util.Random;
public class LocalSearchAgent {
    private static int row;
    int initialValues[] = new int[1]; // whatever value is not a 0 in the initial board
    private int column;
    Memory memory = new Memory();

    public void LocalSearchAgent(int row, int column, int memory[][]) {
        this.row = row;
        this.column = column;
    }

    public static void simulatedAnnealing(File file, int intialValues) {
        Random rand =  new Random();
        int[] possibleValues = new int[] {
                1, 1, 1, 1, 1, 1, 1, 1, 1,
                2, 2, 2, 2, 2, 2, 2, 2, 2,
                3, 3, 3, 3, 3, 3, 3, 3, 3,
                4, 4, 4, 4, 4, 4, 4, 4, 4,
                5, 5, 5, 5, 5, 5, 5, 5, 5,
                6, 6, 6, 6, 6, 6, 6, 6, 6,
                7, 7, 7, 7, 7, 7, 7, 7, 7,
                8, 8, 8, 8, 8, 8, 8, 8, 8,
                9, 9, 9, 9, 9, 9, 9, 9, 9};

//        Random randomValue = new Random();
        Memory memory = new Memory();
        Board.printBoard(memory.board.board);

        int position[][] = memory.board.board;
        int initialValue = 0;
        int remainingValues = 81 - initialValue;
        if (initialValue != 0) {
            // remove intial Values from possibleValues

        } else {
            // put remaining values into the spots whose value is 0, randomly
            int nRow = rand.nextInt(9) + 1;// add 1 to make the range 1-9
            int mColumn = rand.nextInt(9)+1;
            // pick a random spot with 0 and empty the array

        }

    }
    // COst function used to compare it switching the values would reduce the cost on the board.
    // Goal is to have the cost be 0.
    public int costFunction(int memory[][]) {
        int columnCost = 0;
        int rowCost = 0;
        int currentValue = 0;
        int neighborValue = 0;
        int currentTotalErrors =0;
        // check for duplicates in rows
        for (int row = 1; row < 10; row++) {
            for (int column = 1; column < 10; column++) {
                // if the value in the current spot is the same as the value as a neighbor
                // or the current value is equal to zero
                if (currentValue == neighborValue || currentValue == 0) {
                    columnCost += 1;
                }
            }
        }
        // check for duplicates in columns
        for (column = 1; column < 10; column++) {
            for (row = 1; row < 10; row++) {
                if (currentValue == neighborValue || currentValue == 0) {
                    for (int num = 1; num <= 9; num++) {
                        memory[row][column] = num; //Sets the space at the current index (i,j) to num
                        rowCost += 1;
                    }
                }
            }

            currentTotalErrors = rowCost + columnCost;
            int possibleTotalErrors = 81;

            // if switching the values made the cost higher, then switch them back
            if (possibleTotalErrors > currentTotalErrors) {

            } else {
                //update the board
            }
        }
        return currentTotalErrors;
    }
}
