package sudoku_solver;

import java.util.Arrays;

public class Agent {

    public static void backtrackForwardCheck() {
        //Init of memory related items
        Memory memory = new Memory();
        int loops = 0;

        //Loops through i values
        for (int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                if (memory.board.board[i][j] == 0) {
                    //Loops through possible numbers for a space
                    for (int num = 1; num <= 9; num++) {
                        loops++;
                        memory.board.board[i][j] = num; //Sets the space at the current index (i,j) to num

                        //Checks to see if the board is valid
                        Boolean valid = Checker.checkRows(memory.board.board) && Checker.checkCols(memory.board.board) && Checker.checkMatrices(memory.board.board);

                        //If the board is valid and the move is possible, then the move is stored and other values are forward checked
                        if (memory.possible_moves[i][j][num - 1] == 0 && valid) {
                            memory.past_moves[memory.past_move_ind] = num; //Storage of move

                            //Sets a square as filled in
                            for (int x = 0; x < 9; x++) {
                                memory.possible_moves[i][x][num - 1] = 1;
                                memory.possible_moves[x][j][num - 1] = 1;
                            }
                            for (int x = (i / 3) * 3; x < (i / 3) * 3; x++) {
                                for (int y = (j / 3) * 3; x < (j / 3) * 3; y++) {
                                    memory.possible_moves[x][y][num - 1] = 1;
                                }
                            }

                            //Storage of move coordinates
                            memory.past_move_coords[memory.past_move_ind] = new int[2];
                            memory.past_move_coords[memory.past_move_ind][0] = i;
                            memory.past_move_coords[memory.past_move_ind][1] = j;

                            memory.past_move_ind++; //Increments the index for the next move

                            //Exits loop for current space
                            num = 10;
                        } //If the board is wrong and the last possible number is reached, backtracking starts
                        else if (num == 9) {
                            //Guarantess that, if a 9 is backtracked to, the algorithm will backtrack until a different number is reached
                            while (num == 9) {
                                memory.board.board[i][j] = 0; //Resets current space
                                i = memory.past_move_coords[memory.past_move_ind - 1][0]; //Sets current i to previous move's i value
                                j = memory.past_move_coords[memory.past_move_ind - 1][1]; //Sets current j to previous move's j value
                                num = memory.past_moves[memory.past_move_ind - 1]; //Sets the num to the past move's num
                                memory.past_move_ind--; //Sets memory index to previous move

                                //Removes notes previously taken
                                for (int x = 0; x < 9; x++) {
                                    memory.possible_moves[i][x][num - 1] = 0;
                                    memory.possible_moves[x][j][num - 1] = 0;
                                }

                                for (int x = (i / 3) * 3; x < (i / 3) * 3; x++) {
                                    for (int y = (j / 3) * 3; x < (j / 3) * 3; y++) {
                                        memory.possible_moves[x][y][num - 1] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //Print the (hopefully) succesful board
        Board.printBoard(memory.board.board);
        System.out.println("Loops: " + loops);
    }

    public static void backtrack() {
        //Init of memory related items
        Memory memory = new Memory();
        Board.printBoard(memory.board.board);

        //Loops through i values
        for (int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                if (memory.board.board[i][j] == 0) {

                    //Loops through possible numbers for a space
                    for (int num = 1; num <= 9; num++) {

                        //Sets the space at the current index (i,j) to num
                        memory.board.board[i][j] = num;

                        //If the number works, the move will be recorded
                        if (Checker.checkRows(memory.board.board) && Checker.checkCols(memory.board.board) && Checker.checkMatrices(memory.board.board)) {
                            memory.past_moves[memory.past_move_ind] = num; //Storage of move

                            //Storage of move coordinates
                            memory.past_move_coords[memory.past_move_ind] = new int[2];
                            memory.past_move_coords[memory.past_move_ind][0] = i;
                            memory.past_move_coords[memory.past_move_ind][1] = j;

                            memory.past_move_ind++; //Increments the index for the next move

                            //Exits loop for current space
                            num = 10;
                        } //If the board is wrong and the last possible number is reached, backtracking starts
                        else if (num == 9) {
                            //Guarantess that, if a 9 is backtracked to, the algorithm will backtrack until a different number is reached
                            while (num == 9) {
                                memory.board.board[i][j] = 0; //Resets current space
                                i = memory.past_move_coords[memory.past_move_ind - 1][0]; //Sets current i to previous move's i value
                                j = memory.past_move_coords[memory.past_move_ind - 1][1]; //Sets current j to previous move's j value
                                num = memory.past_moves[memory.past_move_ind - 1]; //Sets the num to the past move's num
                                memory.past_move_ind--; //Sets memory index to previous move

                            }
                        }
                    }
                }
            }
        }

        //Print the (hopefully) successful board
        Board.printBoard(memory.board.board);

    }

    public static void backtrackArcConsistency() {
        //Init of memory related items
        Memory memory = new Memory();

        //Loops through i values
        for (int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                if (memory.board.board[i][j] == 0) {
                    //Loops through possible numbers for a space
                    for (int num = 1; num <= 9; num++) {
                        memory.board.board[i][j] = num; //Sets the space at the current index (i,j) to num

                        //Checks to see if the board is valid
                        Boolean valid = Checker.checkRows(memory.board.board) && Checker.checkCols(memory.board.board) && Checker.checkMatrices(memory.board.board) && arcConsistent(memory);

                        //If the board is valid and the move is possible, then the move is stored and other values are forward checked
                        if (memory.possible_moves[i][j][num - 1] == 0 && valid) {
                            memory.past_moves[memory.past_move_ind] = num; //Storage of move

                            //Sets a square as filled in
                            for (int x = 0; x < 9; x++) {
                                memory.possible_moves[i][x][num - 1] = 1;
                                memory.possible_moves[x][j][num - 1] = 1;
                            }
                            for (int x = (i / 3) * 3; x < (i / 3) * 3; x++) {
                                for (int y = (j / 3) * 3; x < (j / 3) * 3; y++) {
                                    memory.possible_moves[x][y][num - 1] = 1;
                                }
                            }

                            //Storage of move coordinates
                            memory.past_move_coords[memory.past_move_ind] = new int[2];
                            memory.past_move_coords[memory.past_move_ind][0] = i;
                            memory.past_move_coords[memory.past_move_ind][1] = j;

                            memory.past_move_ind++; //Increments the index for the next move

                            //Exits loop for current space
                            num = 10;
                        } //If the board is wrong and the last possible number is reached, or the board isn't arc consistent, backtracking starts
                        else if (!arcConsistent(memory)  || num == 9) {
                            //Guarantess that, if a 9 is backtracked to, the algorithm will backtrack until a different number is reached
                            while (num == 9) {
                                memory.board.board[i][j] = 0; //Resets current space
                                i = memory.past_move_coords[memory.past_move_ind - 1][0]; //Sets current i to previous move's i value
                                j = memory.past_move_coords[memory.past_move_ind - 1][1]; //Sets current j to previous move's j value
                                num = memory.past_moves[memory.past_move_ind - 1]; //Sets the num to the past move's num
                                memory.past_move_ind--; //Sets memory index to previous move

                                //Removes notes previously taken
                                for (int x = 0; x < 9; x++) {
                                    memory.possible_moves[i][x][num - 1] = 0;
                                    memory.possible_moves[x][j][num - 1] = 0;
                                }

                                for (int x = (i / 3) * 3; x < (i / 3) * 3; x++) {
                                    for (int y = (j / 3) * 3; x < (j / 3) * 3; y++) {
                                        memory.possible_moves[x][y][num - 1] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //Print the (hopefully) succesful board
        Board.printBoard(memory.board.board);
    }

    //Checks to see if this board is arc consitent
    public static Boolean arcConsistent(Memory memory) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                //Checks to see if current space only has one possible move
                int count = 0;
                for (int x = 0; x < 9; x++) {
                    if(memory.possible_moves[i][j][x] == 0 && memory.board.board[i][j] == 0){
                        count++;
                    }
                }

                //If space has only one move, makes sure space is arc consistent
                if(count == 1){
                    //Loops through rows and cols
                    for (int x = 0; x < 9; x++) {
                        //Checks the current space against another
                        Boolean row = x != i && memory.board.board[x][j] == 0 && Arrays.equals(memory.possible_moves[i][j], memory.possible_moves[x][j]);
                        Boolean col =  x != j && memory.board.board[i][x] == 0 && Arrays.equals(memory.possible_moves[i][j], memory.possible_moves[i][x]);

                        if(row || col){
                            for(int a : memory.possible_moves[i][j]){
                                System.out.print(a);
                            }
                            System.out.println();
                            System.out.println("False");
                            Board.printBoard(memory.board.board);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
