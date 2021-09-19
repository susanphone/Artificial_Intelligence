package sudoku_solver;
import java.util.Random;

public class AgentTemp {
    //local search using a genetic algorithm with a penalty function and tournament selection
    public static Board genetic() {
        //generate 20 boards to create initial population
        //population is stored in an array of Boards
        Board[] population = new Board[20];
        Board solutionBoard = new Board();
        Memory memory = new Memory();
        Random rand = new Random();

        population = HelperFunctions.gernerateBoards(population);

        //check to see if solution exists
        boolean solutionFound = false;
        for(int b = 0; b < 20; b++) {
            if (Checker.checkRows(population[b].board) && Checker.checkCols(population[b].board) && Checker.checkMatrices(population[b].board)) {
                Board.printBoard(population[b].board);
                solutionFound = true;
                break;
            }
        }
        //int iteration = 2; //limit number of iterations
        int iteration = 0;

        do{

            Board[] new_population = new Board[population.length];
            int new_gen_pop_size = 0;
            while(new_gen_pop_size < new_population.length){
                //select two random candidates using tournament selection
                Board candidate1;
                Board candidate2;
                Board parent1 = new Board();
                Board parent2 = new Board();
                Board[] temp_population;
                //HelperFunctions.tournamentSelection(population, candidate1, candidate2, parent1, parent2);

                for( int l = 0; l < 2; l++){
                    int n1 = rand.nextInt(population.length);
                    candidate1 = population[n1];
                    temp_population = HelperFunctions.removeBoardArrayElem(population, n1);
                    int n2 = rand.nextInt(temp_population.length);
                    candidate2 = temp_population[n2];

                    //calculate the fitness of each candidate
                    float fitness1 = HelperFunctions.fitness(candidate1);
                    float fitness2 = HelperFunctions.fitness(candidate2);

                    //Board.printBoard(candidate1.board);
                    System.out.println("Fitness 1: " + fitness1 + "\n");
                    //Board.printBoard(candidate1.board);
                    System.out.println("Fitness 2: " + fitness2 + "\n");

                    if(fitness1 == 1){
                        Board.printBoard(candidate1.board);
                        solutionFound = true;
                        solutionBoard = candidate1;
                    }
                    if(fitness2 == 1){
                        Board.printBoard(candidate2.board);
                        solutionFound = true;
                        solutionBoard = candidate2;
                    }
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
                //crossover - one point (spot 60)
                Board tempBoard1 = new Board();
                Board tempBoard2 = new Board();
                boolean cross_point = false;
                for(int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if(i == 6 && j == 5){
                            cross_point = true;
                        }
                        if (memory.board.board[i][j] == 0 && cross_point) {
                            tempBoard1.board[i][j] = parent2.board[i][j];
                            tempBoard2.board[i][j] = parent1.board[i][j];
                        } else {
                            tempBoard1.board[i][j] = parent1.board[i][j];
                            tempBoard2.board[i][j] = parent2.board[i][j];
                        }
                    }
                }

                parent1 = tempBoard1;
                parent2 = tempBoard2;
                //random mutation
                parent1 = HelperFunctions.mutate(parent1);
                parent2 = HelperFunctions.mutate(parent2);

                new_population[new_gen_pop_size++] = parent1;
                new_population[new_gen_pop_size++] = parent2;
                //repeat entire process until a new 'generation' is reached and start over
                if(new_gen_pop_size == 19){
                    population = new_population;
                    new_population = new Board[20];
                }
                //finish when solution is found (fitness == 1) or number of iterations is reached

//                if(iteration == 0){
//                    float parent1_fitness = HelperFunctions.fitness(parent1);
//                    float parent2_fitness = HelperFunctions.fitness(parent2);
//
//                    if(parent1_fitness > parent2_fitness){
//                        solutionBoard = parent1;
//                        Board.printBoard(parent1.board);
//                    }else{
//                        solutionBoard = parent2;
//                        Board.printBoard(parent2.board);
//                    }
//                }
            }

            System.out.println("Generation: " + iteration + "\n");
            iteration++;
    }while (!solutionFound);

    return solutionBoard;
    }
    
}
