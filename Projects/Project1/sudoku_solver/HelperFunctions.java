package sudoku_solver;
import java.util.Random;

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
    public static void crossover(Board parent1, Board parent2){
        Memory memory = new Memory();

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
}

