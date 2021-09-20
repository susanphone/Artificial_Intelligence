package sudoku_solver;
import java.util.Random;

public class GeneticAgent {
    //local search using a genetic algorithm with a penalty function and tournament selection
    public static Board genetic() {
        //generate 20000 boards to create initial population
        //population is stored in an array of Board objects
        Board[] population = new Board[20000];
        Board solutionBoard = new Board();
        Memory memory = new Memory(); //reference to the original board
        Random rand = new Random(); //using Java's random class in order to generate random values


        //initial population generation
        population = HelperFunctions.generateBoards(population);

        //check to see if solution exists in the initial generation
        boolean solutionFound = false;
        for(int b = 0; b < 20000; b++) {
            if (Checker.checkRows(population[b].board) && Checker.checkCols(population[b].board) && Checker.checkMatrices(population[b].board)) {
                Board.printBoard(population[b].board);
                solutionFound = true;
                break;
            }
        }

        //iteration keeps track of the generation number
        int iteration = 0;

        do{

            Board[] new_population = new Board[population.length]; //stores the children
            int new_gen_pop_size = 0;
            //loop until a new generation has been completed
            while(new_gen_pop_size < new_population.length){
                //select two random candidates using tournament selection
                Board candidate1;
                Board candidate2;
                //parents to store the winners from the tournaments
                Board parent1 = new Board();
                Board parent2 = new Board();
                Board[] temp_population; //used to ensure there is NO replacement during tournament selection

                //loop twice to get two parents from tournament selection
                for( int l = 0; l < 2; l++){
                    int n1 = rand.nextInt(population.length); //random int that represents the first selected board for tournament
                    candidate1 = population[n1];
                    temp_population = HelperFunctions.removeBoardArrayElem(population, n1); //adjust the population to not include candidate1
                    int n2 = rand.nextInt(temp_population.length); //random int that represents the second selected board for tournament
                    candidate2 = temp_population[n2];

                    //calculate the fitness of each candidate
                    float fitness1 = HelperFunctions.fitness(candidate1);
                    float fitness2 = HelperFunctions.fitness(candidate2);

                    //if the fitness score from either board equals one, then a solution board has been found
                    //print the solution board
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

                    //if candidate1's fitness is best & its the first loop assign parent1 to be candidate1
                    //else its the second loop so assign parent2 to candidate1
                    if(fitness1 > fitness2){
                        if(l ==0){
                            parent1 = candidate1;
                        }else parent2 = candidate1;

                        //if candidate2's fitness is best & its the first loop assign parent1 to be candidate2
                        //else its the second loop so assign parent2 to candidate2
                    }else {
                        if(l == 0){
                            parent1 = candidate2;
                        }else parent2 = candidate2;
                    }
                }

                //for video demonstration purposes
                if(iteration == 2){
                    System.out.println("Randomly selected parents (tournament winners): \n");
                    Board.printBoard(parent1.board);
                    Board.printBoard(parent2.board);
                }

                //crossover - random row crossover for the parents
                int rand_row = rand.nextInt(9); //random row to perform crossover on
                int temp;
                for (int j = 0; j < 9; j++) {
                    temp = parent1.board[rand_row][j];
                    parent1.board[rand_row][j] = parent2.board[rand_row][j];
                    parent2.board[rand_row][j] = temp;
                }

                //random mutation
                parent1 = HelperFunctions.mutate(parent1);
                parent2 = HelperFunctions.mutate(parent2);

                //add the children to the new generation
                new_population[new_gen_pop_size++] = parent1;
                new_population[new_gen_pop_size++] = parent2;

                //for video demonstration purposes
                if(iteration == 2){
                    System. out.println("Children Boards: \n");
                    Board.printBoard(parent1.board);
                    Board.printBoard(parent2.board);
                }

                //repeat entire process until a new 'generation' is reached and start over
                if(new_gen_pop_size == 20000){
                    population = new_population;
                    new_population = new Board[20000];
                }

            }

            System.out.println("Generation: " + iteration + "\n");
            iteration++;

    }while (!solutionFound & iteration < 3);

    return solutionBoard;
    }
    
}
