package sudoku_solver;
import java.util.Random;

public class HelperFunctions {

    public static float fitness(Board board){

        //find number of correct rows and divide by total rows
        //find number of correct columns and divide by total columns
        //find number of correct matrices and divide by total matrices
        // add up all three values and divide by 3


        int[] vals = new int[9];
        boolean isValidRow = true;
        float valid_rows = 9;
        for(int i = 0; i < 9; i++){
            isValidRow = true;
            vals = new int[9];
            for(int j = 0; j < 9; j++){
                if(vals[board.board[i][j]-1] == 0){
                    vals[board.board[i][j]-1] = 1;
                }
                else {
                    isValidRow = false;
                    break;
                }
            }
            if(!isValidRow){
                valid_rows--;
            }
        }
        float row_val = valid_rows / 9;

        boolean isValidCol = true;
        float valid_cols = 9;
        for(int i = 0; i < 9; i++){
            isValidCol = true;
            vals = new int[9];
            for(int j = 0; j < 9; j++){
                if(vals[board.board[j][i]-1] == 0){
                    vals[board.board[j][i]-1] = 1;
                }
                else{
                    isValidCol = false;
                    break;
                }
            }
            if(!isValidCol){
                valid_cols--;
            }
        }
        float col_val = valid_cols / 9;

        float valid_matrices = 9;
        boolean isValidMatrix = true;
        int[] vals1;
        int[] vals2;
        int[] vals3;
        for(int num = 0; num < 9; num+=3){
            //Resets the checkers
            vals1 = new int[9];
            vals2 = new int[9];
            vals3 = new int[9];

            for(int i = num; i < num + 3; i++)
            {
                isValidMatrix = true;
                //Checks matrices on left side
                for(int j = 0; j < 3; j++){
                    if(board.board[i][j] != 0){
                        if(vals1[board.board[i][j]-1] == 0){
                            vals1[board.board[i][j]-1] = 1;
                        }
                        else isValidMatrix = false;
                    }
                }
                //Checks matrices in the middle
                for(int j = 3; j < 6; j++)
                {
                    if(board.board[i][j] != 0){
                        if(vals2[board.board[i][j]-1] == 0){
                            vals2[board.board[i][j]-1] = 1;
                        }
                        else isValidMatrix = false;
                    }
                }

                //Checks matrices on right side
                for(int j = 6; j < 9; j++){
                    if(board.board[i][j] != 0){
                        if(vals3[board.board[i][j]-1] == 0){
                            vals3[board.board[i][j]-1] = 1;
                        }
                        else isValidMatrix = false;
                    }
                }
                if(!isValidMatrix){
                    valid_matrices--;
                }
            }
        }
        float matrix_val = valid_matrices / 9;

        float fitness_val = (row_val + col_val + matrix_val) / 3;
        return fitness_val;

    }
    public static int[] removeIntArrayElem(int[] arr, int index){
        int[] tempArray = new int[arr.length - 1];

        if(arr == null || index < 0 || index >= arr.length){
            return  arr;
        }
        for(int i = 0, j = 0; i < arr.length; i ++){
            if(i == index){
                continue;
            }else{
                tempArray[j++] = arr[i];
            }
        }
        return tempArray;
    }

    public static Board[] removeBoardArrayElem(Board[] boards, int index){
        Board[] tempArray = new Board[boards.length - 1];

        if(boards == null || index < 0 || index >= boards.length){
            return  boards;
        }
        for(int i = 0, j = 0; i < boards.length; i ++){
            if(i == index){
                continue;
            }else{
                tempArray[j++] = boards[i];
            }
        }
        return tempArray;
    }

    public static int[] generateRemainingValues(Board b){
        Memory memory = new Memory();
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

        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (memory.board.board[i][j] != 0) {
                    for (int val = 0; val < possibleValues.length; val++) {
                        if (possibleValues[val] == memory.board.board[i][j]) {
                            possibleValues = removeIntArrayElem(possibleValues, val);
                            break;
                        }
                    }
                }
            }
        }
        return possibleValues;
    }
  
    public static Board[] gernerateBoards(Board[] population){

        Memory memory = new Memory();
        Random rand = new Random();
        int[] possibleValues = generateRemainingValues(memory.board);
        //fill the empty spaces with a random value to create the initial population of boards
        for(int k = 0; k < 20; k++){
            Board tempBoard = new Board();
            possibleValues = generateRemainingValues(memory.board);
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(memory.board.board[i][j] == 0){
                        int n = rand.nextInt(possibleValues.length);
                        tempBoard.board[i][j] = possibleValues[n];
                        possibleValues = removeIntArrayElem(possibleValues, n);
                    }else{
                        tempBoard.board[i][j] = memory.board.board[i][j];
                    }
                }
            }
            Board.printBoard(tempBoard.board);
            population[k] = tempBoard;
        }
        return population;
    }

    public static Board mutate(Board parent){
        Memory memory = new Memory();
        Random rand = new Random();

        int mutationPoint_row = rand.nextInt(9);
        int mutationPoint_col = rand.nextInt(9);
        boolean mutationValid = false;

        while(!mutationValid){
            if( mutationPoint_col != 0 && mutationPoint_row != 0 && memory.board.null_positions[mutationPoint_row * mutationPoint_col] == 0){
                mutationPoint_col = rand.nextInt(9);
                mutationPoint_row = rand.nextInt(9);
            }else mutationValid = true;
        }
        int new_val = rand.nextInt(9) + 1;// add 1 to make the range 1-9

        parent.board[mutationPoint_row][mutationPoint_col] = new_val;
        return parent;
    }

    public static void tournamentSelection( Board[] population, Board candidate1, Board candidate2, Board parent1, Board parent2){
        //create two tournaments and select 'winners' from each to be parents
        Random rand = new Random();
    }

    // Cost function used to compare it switching the values would reduce the cost on the board.
    // Goal is to have the cost be 0.
    public static int costFunction ( int memory[][]){
        int columnCost = 0;
        int rowCost = 0;
        int currentValue = 0;
        int neighborValue = 0;
        int currentTotalErrors = 0;
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
        for (int column = 1; column < 10; column++) {
            for (int row = 1; row < 10; row++) {
                if (currentValue == neighborValue || currentValue == 0) {
                    for (int num = 1; num <= 9; num++) {
//                                memory[row][column] = num; //Sets the space at the current index (i,j) to num
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

