
package sudoku_solver;

public class Memory {
    Board board = new Board("/Users/hannah/Fall21/AI/Artificial_Intelligence/Projects/Project1/test_files/Med-P3.csv");
    //+ currentBoard: Board
    
    
    int past_move_ind = 0;
    int[] past_moves = new int[81];
    int[][] past_move_coords = new int[81][2];
    //+ potentialBoards: Board[]
}
