from wumpus_world.Board import Board
from wumpus_world.Explorer import Explorer
from wumpus_world.Logic import get_neighbors, Logic
from wumpus_world.Statistics import Statistics
from collections import defaultdict
import random

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
        r_statistics = Statistics(0)
        statistics = Statistics(0)

        # explorer starting position
        pos = Board.starting_position(board)
        previous = pos  # keep track of previous position

        # create a reactive explorer that in the starting position
        r_explorer = Explorer(pos, r_statistics)

        # reactive agent loop
        print("Reactive Agent: ")
        count = 0  # limit the number of loops if stuck
        while count < 1000000:
            # look at the neighbors at the current position
            n = get_neighbors(pos, board)
            clauses = []

            # go through the neighbors to see if there is a stench or breeze
            for neighbor in n:
                if neighbor is not None:
                    # if a Wumpus is in a neighboring cell, then stench is true
                    if neighbor.state == 'W':
                        clauses.append("stench")
                    # if a pit is in a neighboring cell, then breeze is true
                    if neighbor.state == 'P':
                        clauses.append("breeze")

            # if there is a stench or breeze present, then we do not know if the neighbor's are safe
            # the only place we do know that is safe is the previous spot
            # give a greater chance to returning to the only known safe spot (previous)
            if ("stench" in clauses or "breeze" in clauses) and pos != previous:
                possible_moves = [previous]
                for neighbor in n:
                    if neighbor != None:
                        possible_moves.append(neighbor)

            # if there is no stench or breeze, then we know all neighbor's are safe
            # or if we are at starting place, then we have to randomly pick from neighbors
            else:
                possible_moves = []
                for neighbor in n:
                    if neighbor != None:
                        possible_moves.append(neighbor)
            # randomly select from the possible moves
            r = random.randrange(0, len(possible_moves))
            previous_p = previous
            previous = pos
            pos = possible_moves[r]

            # find the direction of the selected neighbor
            direction = 0
            for i in range(len(n)):
                if n[i] != None:
                    if pos.y == n[i].y and pos.x == n[i].x:
                        direction = i

            # assign a cardinal direction
            if direction == 0:
                dest = 'n'
            elif direction == 1:
                dest = 's'
            elif direction == 2:
                dest = 'w'
            else:
                dest = 'e'

            # move the explorer to the randomly selected position
            r_explorer.move(dest, n)

            # if its an obstacle, bounce back to orignal spot
            if pos.state == 'O':
                pos = previous
                previous = previous_p

            # end if explorer runs into a wumpus, pit, or gold
            if pos.state == 'G':
                print("Winner")
                r_statistics.gold_found = r_statistics.update_gold_found()
                break
            if pos.state == 'W':
                print("Killed by Wumpus")
                r_statistics.deaths_by_wumps = r_statistics.death_by_wumpus()
                break
            if pos.state == 'P':
                print("Fell into Pit")
                r_statistics.deaths_by_pit = r_statistics.death_by_pit()
                break
            else:
                count += 1
        print("Score: ", r_statistics.moves)

        # explorer starting position
        pos = Board.starting_position(board)
        previous = pos  # keep track of previous position

        # initialize the knowledge base to be an empty dictionary
        kb = defaultdict(list)  # use this kind of dictionary to allow the value to be a list

        # create a logic object using current knowledge base
        logic = Logic(kb)

        # create an explorer that in the starting position
        explorer = Explorer(pos, statistics)

        # logic agent loop
        print("Logical Agent: ")
        count = 0
        while count < 1000000:
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
            explorer.move(dest, n)
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
