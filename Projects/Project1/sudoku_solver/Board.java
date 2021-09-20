package sudoku_solver;
import java.io.*;  
import java.util.logging.Level;
import java.util.logging.Logger;


public class Board {
    //Stores the initial board
    public int[][] board = new int[9][9];

    public Board(){
        //constructor to have empty board objects
    }
    
    
    //Reads in a board from the file with the name passed in
    public Board(String name){
        //Try and catch to handle errors
        try {
            //In stores the current line
            //Delimiter is used to separate values within a line
            String in, delimiter = ",";
            
            //Stores the index of the current row
            int row = 0;
            int index = 0;
            
            //Initializes a buffered reader to read the csv file
            BufferedReader br = new BufferedReader(new FileReader(name));
            
            //Reads in the next line and stores it in 'in'
            while ((in = br.readLine()) != null){ 
                //Separates the line into an array, with commas separating values
                String[] line = in.split(delimiter);
                
                //Loops through array line and stores them in the board
                for(int col = 0; col < line.length; col++){
                    if(line[col].length() == 2){
                        if(line[col].charAt(1) == '?'){
                            board[row][col] = 0;
                        }
                            
                        else{
                            board[row][col] = Character.getNumericValue(line[col].charAt(1));
                        }
                    }
                    
                    //Checks to see
                    else if(line[col].equals("?")){
                        board[row][col] = 0;
                    }
                    else{
                        board[row][col] = Integer.parseInt(line[col]);
                    }
                    
                }
                row++;
            }
        }
        
        //Error catches
        catch (FileNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    static public void printBoard(int[][] board){
        for(int[] a : board){
            for(int b : a)
            {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
