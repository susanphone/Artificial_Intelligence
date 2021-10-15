from wumpus_world.Board import Board
from wumpus_world.Explorer import Explorer
from wumpus_world.Logic import get_neighbors, Logic
from wumpus_world.Statistics import Statistics
from collections import defaultdict

if __name__ == "__main__":

    '''
    the user will input the size of the board as two integers i.e. '5 5' will produce a 5x5 board
    the probabilities are an integer out of 100 i.e. 5 would be 5 out of 100 or 5%
    '''

    counter = 0  # for testing purposes
    while counter < 5:

        # board creation
        board_size1, board_size2 = input("Choose Board Size: \n").split()
        b1 = int(board_size1)
        b2 = int(board_size2)
        prob_pit = int(input("What is the probability of generating a pit?\n"))
        prob_obs = int(input("What is the probability of generating an obstacle?\n"))
        prob_wumpus = int(input("What is the probability of generating a wumpus?\n"))

        w = 0  # initialize number of wumpuses
        cells = []  # empty cells
        board = Board(cells, w)  # create an empty board
        Board.generate_board(board, b1, b2, prob_pit, prob_obs, prob_wumpus)  # fill the board appropriately
        Board.print_board(board)

        # initializing statistics
        remainingArrows = board.wumpus
        statistics = Statistics(0)

        # explorer starting position
        pos = Board.starting_position(board)
        previous = pos  # keep track of previous position

        count = 0  # limit the number of loops if stuck

        # initialize the knowledge base to be an empty dictionary
        kb = defaultdict(list)  # use this kind of dictionary to allow the value to be a list
        # create a logic object using current knowledge base
        logic = Logic(kb)
        # create an explorer that in the starting position
        explorer = Explorer(pos, statistics)

        while count < 1000:
            # get neighbors of the current cell the explorer is in
            n = get_neighbors(pos, board)

            # decide what the next best cell to move to is using first order logic
            bestCell = logic.decide(pos, n, board, previous)

            # move in the direction of the next best cell
            if bestCell == 0:
                dest = 'n'
            elif bestCell == 1:
                dest = 's'
            elif bestCell == 2:
                dest = 'w'
            else:
                dest = 'e'

            # move the explorer to the next best cell
            explorer.move(dest, kb, n)
            previous_p = previous  # the previous previous
            previous = pos  # set previous to be the last position before move
            pos = n[bestCell]  # set the explorer's position to the new cell

            # backtrack if the cell is an obstacle
            if pos.state == 'O':
                logic.knowledge_base[n[bestCell]].append('O')
                pos = previous
                previous = previous_p

            # end if explorer runs into a wumpus, pit, or gold
            if pos.state == 'G':
                print("Winner")
                statistics.gold_found = statistics.update_gold_found()
                break
            if pos.state == 'W':
                print("Killed by Wumpus")
                statistics.deaths_by_wumps = statistics.death_by_wumpus()
                break
            if pos.state == 'P':
                print("Fell into Pit")
                statistics.deaths_by_pit = statistics.death_by_pit()
                break
            else:
                count += 1

        # print the final score
        print("Score: ", statistics.moves)
        # reset moves
        statistics.moves = 0
        counter += 1  # update loop
