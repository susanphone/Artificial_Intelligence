package sudoku_solver;

import java.io.File;
import java.util.Random;
public class LocalSearchAgent {
    private static int row;
    int initialValues[] = new int[81];
    private int column;
    Memory memory = new Memory();

    public void LocalSearchAgent(int row, int column, int memory[][]) {
        this.row = row;
        this.column = column;
    }

    public static void simulatedAnnealing(File file, int intialValues) {
        int[] possibleValues = new int[81];
        possibleValues[intialValues] = [
                1,1,1,1,1,1,1,1,1,
                2,2,2,2,2,2,2,2,2,
                3,3,3,3,3,3,3,3,3,
                4,4,4,4,4,4,4,4,4,
                5,5,5,5,5,5,5,5,5,
                6,6,6,6,6,6,6,6,6,
                7,7,7,7,7,7,7,7,7,
                8,8,8,8,8,8,8,8,8,
                9,9,9,9,9,9,9,9,9];

//        Random randomValue = new Random();
        Memory memory = new Memory();
        Board.printBoard(memory.board.board);

        int position = memory.board.board[][];
        int initialValue;
        int remainingValues = 81 - initialValue;
        // remove intial Values from possibleValues
        if(initialValue != 0) {

        }

    }

    public int costFunction(int memory[][]) {
        int columnCost = 0;
        int rowCost = 0;
        int currentValue = 0;
        int neighborValue = 0;
        for (int row=1; row<10; row++) {
            for(int column=1; column<10;column++) {
                // if the value in the current spot is the same as the value as a neighbor
                // or the current value is equal to zero
                if (currentValue == neighborValue || currentValue == 0){
                    columnCost += 1;
                }
            }
        }
        for (column=1;column<10;column++) {
            for(row=1; row<10;row++) {
                if (currentValue == neighborValue || currentValue == 0) {
                    for (int num = 1; num <= 9; num++) {
                        memory.board.board[row][column] = num; //Sets the space at the current index (i,j) to num

                        rowCost += 1;
                }
            }
        }

        int currentTotalErrors = rowCost + columnCost;
        int possibleTotalErrors = 81;

        // if switching the values made the cost higher, then switch them back
        if (possibleTotalErrors > currentTotalErrors) {

        } else {
            //update the board
        }
        return currentTotalErrors;
    }

}
