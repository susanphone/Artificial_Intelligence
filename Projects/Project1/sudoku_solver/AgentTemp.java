package sudoku_solver;
import java.util.Random;

public class AgentTemp {
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
    public static int[] removeArrayElem(int[] boards, int index){
        int[] tempArray = new int[boards.length - 1];

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
    //local search using a genetic algorithm with a penalty function and tournament selection
    public static void genetic() {
        //generate 20 boards to create initial population
        //population is stored in an array of Boards
        Board[] population = new Board[20];
        Memory memory = new Memory();
        Random rand = new Random();
        //fill the empty spaces with a random value to create the initial population of boards
        for(int k = 0; k < 20; k++){
            Board tempBoard = new Board();
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(memory.board.board[i][j] == 0){
                        int n = rand.nextInt(9) + 1;// add 1 to make the range 1-9
                        tempBoard.board[i][j] = n;
                    }else{
                        tempBoard.board[i][j] = memory.board.board[i][j];
                    }
                }
            }
            population[k] = tempBoard;
            Board.printBoard(tempBoard.board);
        }
        //check to see if solution exists
        boolean solutionFound = false;
        for(int b = 0; b < 20; b++){
            if(Checker.checkRows(population[b].board) && Checker.checkCols(population[b].board) && Checker.checkMatrices(population[b].board)){
                Board.printBoard(population[b].board);
                solutionFound = true;
                break;
            }
        }

        int iteration = 1000; //limit number of iterations

        do{

            //select two random candidates
            //calculate the fitness of each candidate
            //repeat
            //use the two candidates with the highest fitness from each 'tournament'  to be parents
            //crossover and mutate to make a new population
            //repeat entire process until a new 'generation' is reached and start over
            //finish when solution is found (fitness == 1) or number of iterations is reached

        }while (!solutionFound && iteration > 0);

            //select the two best boards (based off of fitness function) and reproduce
            //repeat process



    }
    
}
