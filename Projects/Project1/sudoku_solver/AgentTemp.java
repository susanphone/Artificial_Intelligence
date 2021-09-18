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

        int iteration = 1000; //limit number of iterations

        do{
            Board[] new_population = new Board[population.length];
            //select two random candidates using tournament selection
            Board candidate1 = new Board();
            Board candidate2 = new Board();
            Board parent1 = new Board();
            Board parent2 = new Board();
            Board[] temp_population = new Board[population.length];
            //create two tournaments and select 'winners' from each to be parents

            for( int l = 0; l < 2; l++){
                int n1 = rand.nextInt(population.length);
                candidate1 = population[n1];
                temp_population = HelperFunctions.removeArrayElem(population, n1);
                int n2 = rand.nextInt(temp_population.length);
                candidate2 = temp_population[n2];

                //calculate the fitness of each candidate
                float fitness1 = HelperFunctions.fitness(candidate1);
                float fitness2 = HelperFunctions.fitness(candidate2);

                //use the two candidates with the highest fitness from each 'tournament'  to be parents
                if(fitness1 > fitness2){
                    if(l ==0){
                        parent1 = candidate1;
                    }else parent2 = candidate1;
                }else {
                    if(l == 0){
                        parent1 = candidate2;
                    }else parent2 = candidate2;
                }
            }

            //crossover and mutate to make a new population

            //repeat entire process until a new 'generation' is reached and start over
            //finish when solution is found (fitness == 1) or number of iterations is reached

        }while (!solutionFound && iteration > 0);

            //select the two best boards (based off of fitness function) and reproduce
            //repeat process



    }
    
}
