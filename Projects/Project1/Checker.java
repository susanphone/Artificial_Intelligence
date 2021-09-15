/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku_solver;

public class Checker {
    static public boolean checkRows(int[][] board){
        int[] checker; //Array of size nine. Each index represents a move. A 0 means the move has not been made in the row, a 1 means it has
        for(int i = 0; i < 9; i++){
            checker = new int[9]; //Resets the checker
            for(int j = 0; j < 9; j++)
            { 
                //Makes sure the board is not checking empty spaces
                if(board[i][j] != 0){
                    //If the value has not been used yet, its associated index is set to 1. Otherwise, the row is wrong.
                    if(checker[board[i][j]-1] == 0){
                        checker[board[i][j]-1] = 1;
                        
                    }
                    else{
                        return false;
                    }
                }
                
            }
        }
        return true;
    }

    static public boolean checkCols(int[][] board){
        int[] checker; //Array of size nine. Each index represents a move. A 0 means the move has not been made in the column, a 1 means it has
        for(int i = 0; i < 9; i++){
            checker = new int[9]; //Resets the checker
            
            for(int j = 0; j < 9; j++)
            { 
                //Makes sure the board is not checking empty spaces
                if(board[j][i] != 0){
                    //If the value has not been used yet, its associated index is set to 1. Otherwise, the column is wrong.
                    if(checker[board[j][i]-1] == 0){
                        checker[board[j][i]-1] = 1;
                    }
                    else{
                        return false;
                    }
                }
                
            }
        }
        return true;
    }

    static public boolean checkMatrices(int[][] board){
        //Arrays of size nine. Each index represents a move. A 0 means the move has not been made in the matrix, a 1 means it has
        int[] checker1;
        int[] checker2;
        int[] checker3;
        
        //Loops thro
        for(int num = 0; num < 9; num+=3){
            //Resets the checkers
            checker1 = new int[9];
            checker2 = new int[9];
            checker3 = new int[9];
            
            for(int i = num; i < num + 3; i++)
            { 
                //Checks matrices on left side
                for(int j = 0; j < 3; j++)
                { 
                    if(board[i][j] != 0){
                        if(checker1[board[i][j]-1] == 0){
                            checker1[board[i][j]-1] = 1;
                        }
                        else{
                            return false;
                        }
                    }
                }
                
                //Checks matrices in the middle
                for(int j = 3; j < 6; j++)
                { 
                    if(board[i][j] != 0){
                        if(checker2[board[i][j]-1] == 0){
                            checker2[board[i][j]-1] = 1;
                        }
                        else{
                            return false;
                        }
                    }
                }
                
                //Checks matrices on right side
                for(int j = 6; j < 9; j++)
                { 
                    if(board[i][j] != 0){
                        if(checker3[board[i][j]-1] == 0){
                            checker3[board[i][j]-1] = 1;
                        }
                        else{
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
