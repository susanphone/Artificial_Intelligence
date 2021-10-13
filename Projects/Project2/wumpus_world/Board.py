from wumpus_world.Cell import Cell
import random


class Board:
    """
    :param List[] cells: a list of Cell objects that is grouped by rows, which are ordered by column
    i.e. all the values in row 0 make up the first part of the list, and all the values in row 1 make up the next part
    :param int wumpus: value used by the "scout" to inform the agents of the number of wumpus on a given board
    """
    def __init__(self):
        self.cells
        self.wumpus

    def generate_board(self, d1, d2, pit, obs, wumpus):
        # first randomly select a cell to hold the gold
        cells = []
        x = random.randrange(0, d1)
        y = random.randrange(0, d2)
        state = 'G'
        gold_cell = Cell(x, y, state)

        # generate the rest of the cells in the board using the appropriate probabilities
        pit_range = (0, pit)
        obs_range = (pit + 1, pit + obs)
        wumpus_range = (pit + obs + 1, pit + obs + wumpus)

    # assign states to the rest of the board spaces and store in a list of Cells
        for i in range(d1):
            for j in range(d2):
                if j == gold_cell.x and i == gold_cell.y:
                    cells.append(gold_cell)
                else:
                    # determine the state of the cell based on probabilities
                    r = random.randrange(0, 100)

                    if r in range(pit_range[0], pit_range[1]):
                        state = 'P'
                    elif r in range(obs_range[0], obs_range[1]):
                        state = 'O'
                    elif r in range(wumpus_range[0], wumpus_range[1]):
                        state = 'W'
                        self.wumpus += 1
                    else:
                        state = 'S'

                    # create a Cell to add to the list
                    curr_cell = Cell(j, i, state)
                    cells.append(curr_cell)

        # once finished assign to be the current boards cells
        self.cells = cells

    # print the state for each space on the board
    def print_board(self):
        curr_row = 0
        for c in self.cells:
            if c.y == curr_row:
                print(c.state + " ", end="")
            else:
                curr_row += 1
                print(" ")
                print(c.state + " ", end="")
        print("Total Wumpus: ", self.wumpus)

    def starting_position(self):
        # randomly select a safe cell to start and return it
        # generate list of safe cells to choose from
        safe_cells = []
        for cell in self.cells:
            if cell.state == 'S':
                safe_cells.append(cell)

        r = random.randrange(0, len(safe_cells))
        start = safe_cells[r]

        return start
