package sudoku_solver;

public class HelperFunctions {
    public static float  fitness(Board board){

        //find number of correct rows and divide by total rows
        //find number of correct columns and divide by total columns
        //find number of correct matrices and divide by total matrices
        // add up all three values and divide by 3


        int[] vals = new int[9];

        float valid_rows = 9;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(vals[board.board[i][j]-1] == 0){
                    vals[board.board[i][j]-1] = 1;
                }
                else valid_rows -= 1;
            }
        }
        float row_val = valid_rows / 9;

        float valid_cols = 9;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(vals[board.board[j][i]-1] == 0){
                    vals[board.board[j][i]-1] = 1;
                }
                else valid_cols -= 1;
            }
        }
        float col_val = valid_cols / 9;

        float valid_matrices = 0;
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
                //Checks matrices on left side
                for(int j = 0; j < 3; j++)
                {
                    if(board.board[i][j] != 0){
                        if(vals1[board.board[i][j]-1] == 0){
                            vals1[board.board[i][j]-1] = 1;
                        }
                        else valid_matrices -= 1;
                    }
                }

                //Checks matrices in the middle
                for(int j = 3; j < 6; j++)
                {
                    if(board.board[i][j] != 0){
                        if(vals2[board.board[i][j]-1] == 0){
                            vals2[board.board[i][j]-1] = 1;
                        }
                        else valid_matrices -= 1;
                    }
                }

                //Checks matrices on right side
                for(int j = 6; j < 9; j++)
                {
                    if(board.board[i][j] != 0){
                        if(vals3[board.board[i][j]-1] == 0){
                            vals3[board.board[i][j]-1] = 1;
                        }
                        else valid_matrices -= 1;
                    }
                }
            }
        }
        float matrix_val = valid_matrices / 9;

        float fitness_val = (row_val + col_val + matrix_val) / 3;
        return fitness_val;

    }
    public static Board[] removeArrayElem(Board[] boards, int index){
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

