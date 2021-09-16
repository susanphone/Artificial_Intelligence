package sudoku_solver;
import java.util.Random;

public class AgentTemp {
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

        if (!solutionFound){
            //select the two best boards (based off of fitness function) and reproduce
            //repeat process
        }



    }
    
}
