package sudoku_solver;

public class Agent {
    public static void backtrack(){
        //Init of memory related items
        Memory memory = new Memory();
        
        //Loops through i values
        for(int i = 0; i < 9; i++){
            //Loops through j values
             for(int j = 0; j < 9; j++)
             {
                 //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                 if(memory.board.init_board[i][j] == 0){
                     
                     //Loops through possible numbers for a space
                     for(int num = 1; num <= 9; num++){
                         
                        //Sets the space at the current index (i,j) to num
                        memory.board.init_board[i][j] = num;
                        
                        //If the number works, the move will be recorded
                        if(Checker.checkRows(memory.board.init_board) && Checker.checkCols(memory.board.init_board) && Checker.checkMatrices(memory.board.init_board)){
                            memory.past_moves[memory.past_move_ind] = num; //Storage of move
                            
                            //Storage of move coordinates
                            memory.past_move_coords[memory.past_move_ind] = new int[2];
                            memory.past_move_coords[memory.past_move_ind][0] = i;
                            memory.past_move_coords[memory.past_move_ind][1] = j;
                           
                            memory.past_move_ind++; //Increments the index for the next move
                            
                            //Exits loop for current space
                            num = 10;
                        }
                        
                        //If the board is wrong and the last possible number is reached, backtracking starts
                        else if(num == 9){
                            memory.board.init_board[i][j] = 0; //Resets current space
                            i = memory.past_move_coords[memory.past_move_ind - 1][0]; //Sets current i to previous move's i value
                            j = memory.past_move_coords[memory.past_move_ind - 1][1]; //Sets current j to previous move's j value
                            num = memory.past_moves[memory.past_move_ind - 1]; //Sets the num to the past move's num
                            memory.past_move_ind--; //Sets memory index to previous move
                            
                        }
                    }
                }
            }
        }
       
        Board.printBoard(memory.board.init_board);
        
    }
}
