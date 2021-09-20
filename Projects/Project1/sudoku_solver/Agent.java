package sudoku_solver;

import java.util.Arrays;
import java.util.Random;

public class Agent {

    public static void backtrackForwardCheck() {
        //Init of memory related items
        Memory memory = new Memory();
        int loops = 0; //Counts number of loops

        //Loops through i values
        for (int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                if (memory.board.board[i][j] == 0) {
                    boolean backtrack = false;
                    Boolean valid = true;
                    //Loops through possible numbers for a space
                    for (int num = 1; num <= 9; num++) {
                        if(memory.possible_moves[i][j][num - 1] == 0){
                            
                            loops++;
                            memory.board.board[i][j] = num; //Sets the space at the current index (i,j) to num
                            //Board.printBoard(memory.board.board);
                            //Checks to see if the board is valid
                            valid = Checker.checkRows(memory.board.board) && Checker.checkCols(memory.board.board) && Checker.checkMatrices(memory.board.board);

                            //If the board is valid and the move is possible, then the move is stored and other values are forward checked
                            if (valid) {
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
                            } 
                            else if(num == 9){
                                backtrack = true;
                            }
                        }
                        
                        //If the board is wrong and the last possible number is reached, backtracking starts
                        if (num == 9 && !valid || backtrack) {
                            //Guarantess that, if a 9 is backtracked to, the algorithm will backtrack until a different number is reached
                            while (num == 9) {
                                loops++;
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
        System.out.println("Loops: " + loops);
        Board.printBoard(memory.board.board);
        
    }

    public static void backtrack() {
        //Init of memory related items
        Memory memory = new Memory();
        int loops = 0; //Counts number of loops

        //Loops through i values
        for (int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                
                if (memory.board.board[i][j] == 0) {
                    //Loops through possible numbers for a space
                    for (int num = 1; num <= 9; num++) {
                        loops++; //Increments for every loop done
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
                                loops++;
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
<<<<<<< HEAD
=======

        //Print the (hopefully) successful board
        System.out.println("Loops: " + loops);
        Board.printBoard(memory.board.board);

    }
>>>>>>> 0db1a35636bd14da7dd43e8a277e249fed5e5b3b

    public static void backtrackArcConsistency() {
        //Init of memory related items
        Memory memory = new Memory();
        int loops = 0; //Counts number of loops

        //Loops through i values
        for (int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                //Checks for empty space; If it's empty, the algorithm changes it. Otherwise, it does not need to be changed
                if (memory.board.board[i][j] == 0) {
                    boolean backtrack = false;
                    Boolean valid = true;
                    //Loops through possible numbers for a space
       
                    for (int num = 1; num <= 9; num++) {
                        if(memory.possible_moves[i][j][num - 1] == 0){
                            loops++;
                             //Sets the space at the current index (i,j) to num
                            memory.board.board[i][j] = num;

                            //Checks to see if the board is valid
                            valid = Checker.checkRows(memory.board.board) && Checker.checkCols(memory.board.board) && Checker.checkMatrices(memory.board.board) && arcConsistent(memory);

                            //If the board is valid and the move is possible, then the move is stored and other values are forward checked
                            if (valid) {
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
                            } 
                            else if(num == 9){
                                backtrack = true;
                            }
                        }

                        //If the board is wrong and the last possible number is reached, or the board isn't arc consistent, backtracking starts
                        if (!arcConsistent(memory)  || (num == 9 && !valid) || backtrack) {
                            //Guarantess that, if a 9 is backtracked to, the algorithm will backtrack until a different number is reached
                            while (num == 9) {
                                loops++;
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
        System.out.println("Loops: " + loops);
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
                        Boolean row = false;
                        if(x != i && memory.board.board[x][j] == 0){
                            for(int a = 0; a < 9; a++){
                                if(memory.possible_moves[i][j][a] !=  memory.possible_moves[x][j][a]){
                                    row = true;
                                }
                            }
                        }
                        
                        Boolean col =  false;
                        if(x != j && memory.board.board[x][j] == 0){
                            for(int a = 0; a < 9; a++){
                                if(memory.possible_moves[i][j][a] !=  memory.possible_moves[i][x][a]){
                                    col = true;
                                }
                            }
                        }
                        if(row || col){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void simulatedAnnealing() throws InterruptedException {
        Random rand = new Random();
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

//        Random randomValue = new Random();
        Memory memory = new Memory();
        int currentCost = 81;
        int position[][] = memory.board.board;
<<<<<<< HEAD
        int initialValue[] =  new int[position.length];
        System.out.println(initialValue);
=======
        int initialValue[] = new int[9];
>>>>>>> 0db1a35636bd14da7dd43e8a277e249fed5e5b3b
        int randomValue = rand.nextInt(9) + 1;
        //Loops through i values
        for (int i = 0; i < 9; i++) {
            //Loops through j values
            for (int j = 0; j < 9; j++) {
                int randomRow = rand.nextInt(9) + 1;
                int randomColumn = rand.nextInt(9) + 1;
                while(currentCost != 0) {
                    if (initialValue[memory.board.board[i][j]] != 0) {
                        currentCost = currentCost - initialValue.length;
                        System.out.println(currentCost);
                        // remove initial Values from possibleValues
                        HelperFunctions.removeIntArrayElem(possibleValues, initialValue[position[i][j]]);

                    } else {
                        // put remaining values into the spots whose value is 0, randomly
                        position[i][j] = position[randomRow][randomColumn];

                        // pick a random spot with 0
                        // and empty the array by placing numbers in random positions where the value is 0.
                        HelperFunctions.removeIntArrayElem(possibleValues, randomValue);
                    }
                    // use the cost function to check if the switch reduced the current cost
                    int cost = HelperFunctions.costFunction(position);
                    if (cost < currentCost) {
                        currentCost = cost;
                    } else {
                        // add random value back to possible values list
                        HelperFunctions.addIntArrayElem(possibleValues, randomValue);
                        // switch the values back and pick another spot
                        position[i][j] = position[randomRow][randomColumn];
                        // then try again
                        System.out.println("Please Wait, System Processing");
//                        System.out.println(currentCost);
                    }
                }
<<<<<<< HEAD
                Board.printBoard(memory.board.board);
=======
                    Board.printBoard(memory.board.board);
>>>>>>> 0db1a35636bd14da7dd43e8a277e249fed5e5b3b
            }
        }
    }
}
