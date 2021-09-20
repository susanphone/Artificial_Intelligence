package sudoku_solver;

public class Sudoku_Solver {

    public static void main(String[] args) throws Exception {
        Agent.backtrack();
//        Agent.backtrackForwardCheck();
//        Agent.backtrackArcConsistency();
//        System.out.println(1/3*3);
//        System.out.println(8/3*3);

//        AgentTemp.genetic();
        Agent.simulatedAnnealing();
//        GeneticAgent.genetic();
    }
}

