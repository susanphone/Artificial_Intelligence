package sudoku_solver;

import java.util.Random;
public class LocalSearchAgent {
    int initialValues[] = new int[1]; // whatever value is not a 0 in the initial board
    Memory memory = new Memory();

    public void LocalSearchAgent(int memory[][]) {
        this.memory.board.board = memory;
    }

    public static void simulatedAnnealing() {
        Random rand = new Random();
        int[] possibleValues = new int[]{
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
        int currentCost = 81;
        int position[][] = memory.board.board;
        int initialValue[] = new int[1];
        int remainingValues = 81 - initialValue[1];
        //Loops through i values
        for (int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                if (initialValue[j] != 0) {
                    // remove initial Values from possibleValues
                    HelperFunctions.removeArrayElem(possibleValues);

                } else {
                    // put remaining values into the spots whose value is 0, randomly
                    int nRow = rand.nextInt(9) + 1;// add 1 to make the range 1-9
                    int mColumn = rand.nextInt(9) + 1;

                    // pick a random spot with 0
                    Board randomPos[][] = new Board[nRow][mColumn];
                    // and empty the array by placing numbers in random positions where the value is 0.
                    HelperFunctions.removeArrayElem(randomPos);
                }
                // use the cost function to check if the switch reduced the current cost
                int cost = HelperFunctions.costFunction(position);
                if (cost < currentCost) {
                    currentCost = cost;
                } else {
                    // switch the values back and pick another spot
                }
                if (cost == 0) {
                    Board.printBoard(memory.board.board);
                }

            }
        }
    }
        }