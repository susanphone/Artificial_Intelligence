
package sudoku_solver;

public class Memory {
    Board board = new Board("Easy-P5.csv");
    //+ currentBoard: Board
    
    
    int past_move_ind = 0;
    int[] past_moves = new int[81];
    int[][] past_move_coords = new int[81][2];
    int[][][] possible_moves = new int[9][9][9];

    //+ potentialBoards: Board[]
}
