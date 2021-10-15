from wumpus_world.Board import Board
from wumpus_world.Explorer import Explorer
from wumpus_world.Logic import get_neighbors, Logic
from wumpus_world.Statistics import Statistics
from collections import defaultdict
"""
1. Generate Board and Cells, probability based
 2. Build Logic - truth tables for each cell
 3. Run Explorer
    - use Logic to solve the board
    - expands upon logic
"""
# this is where we will play the game


if __name__ == "__main__":
    '''
    the user will input the size of the board as two integers i.e. '5 5' will produce a 5x5 board
    the probabilities are an integer out of 100 i.e. 5 would be 5 out of 100 or 5%
    '''
    board_size1, board_size2 = input("Choose Board Size: \n").split()
    b1 = int(board_size1)
    b2 = int(board_size2)
    prob_pit = int(input("What is the probability of generating a pit?\n"))
    prob_obs = int(input("What is the probability of generating an obstacle?\n"))
    prob_wumpus = int(input("What is the probability of generating a wumpus?\n"))

    # empty board with no wumpus before generation
    w = 0
    cells = []
    score = 0
    board = Board(cells, w)
    fullBoard = Board.generate_board(board, b1, b2, prob_pit, prob_obs, prob_wumpus)
    Board.print_board(board)
    remainingArrows = board.wumpus
    statistics = Statistics(score)
    # testing decide()
    pos = Board.starting_position(board)
    previous = pos
    print("Position")
    count = 0
    kb = defaultdict(list)
    while count < 100:
        explorer = Explorer(pos)
        print(pos.y, pos.x, pos.state)
        n = get_neighbors(pos, board)
        logic = Logic(kb)
        bestCell = logic.decide(pos, n, board, previous)
        print(n[bestCell].y, n[bestCell].x, n[bestCell].state)
        if bestCell == 0:
            dest = 'n'
        elif bestCell == 1:
            dest = 's'
        elif bestCell == 2:
            dest = 'w'
        else:
            dest = 'e'
        
        print("Best Choice: direction, position, best")
        print(dest)
        if n[bestCell].state == 'O':
            pos = previous
        else:
            previous = pos
        # print(previous.y, previous.x)
        explorer.move(dest, kb, n)
        pos = n[bestCell]
        if pos.state == 'G':
            print("Winner")
            statistics.gold_found()
            break
        if pos.state == 'W':
            print("Killed by Wumpus")
            statistics.death_by_wumpus()
            break
        if pos.state == 'P':
            print("Fell into Pit")
            statistics.death_by_pit()
            break
        else:
            count += 1
            score += 1
    # best = logic.bestMove(bestCell)
    print("Score" + score)
    # print(best.state)
