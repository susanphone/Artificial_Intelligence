package sudoku_solver;

public class Sudoku_Solver {

    public static void main(String[] args) throws Exception {
        Agent.backtrack();
        Agent.backtrackForwardCheck();
        Agent.backtrackArcConsistency();
//        System.out.println(1/3*3);
//        System.out.println(8/3*3);
//<<<<<<< HEAD
//        AgentTemp.genetic();
//        Agent.simulatedAnnealing();
//=======
//        GeneticAgent.genetic();
//        LocalSearchAgent.simulatedAnnealing();
//>>>>>>> b38dcf8e8ec3d109a0b78bb9f8ac9a3515584e8f
    }
}

