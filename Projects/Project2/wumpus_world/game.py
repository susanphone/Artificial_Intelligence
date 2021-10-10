from wumpus_world.Board import Board
"""
1. Generate Board and Cells, probability based
 2. Build Logic - truth tables for ea cell
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
    board = Board(cells, w)
    Board.generate_board(board, b1, b2, prob_pit, prob_obs, prob_wumpus)
    Board.print_board(board)
