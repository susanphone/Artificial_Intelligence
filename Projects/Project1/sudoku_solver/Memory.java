
package sudoku_solver;

public class Memory {
    int[][] init_board = 
        {{0,0,8,0,5,6,0,0,0},
         {7,0,4,0,0,0,6,1,9},
         {0,0,0,0,0,0,8,5,0},
         {6,0,7,0,2,9,5,0,0},
         {0,0,9,0,6,0,1,0,0},
         {0,0,2,3,1,0,9,0,4},
         {0,3,5,0,0,0,0,0,0},
         {4,2,1,0,0,0,3,0,6},
         {0,0,0,8,3,0,4,0,0}};
    
    Board board = new Board();
    //+ currentBoard: Board
    
    
    int past_move_ind = 0;
    int[] past_moves = new int[81];
    int[][] past_move_coords = new int[81][2];
    //+ potentialBoards: Board[]
}
