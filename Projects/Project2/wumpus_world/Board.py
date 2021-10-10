from wumpus_world.Cell import Cell
import random
class Board():

    def __init__(self, cells):
        self.cells = cells

    def generateBoard(self, d1, d2, pit, obs, wumpus):
        #first randomly select a cell to hold the gold
        cells = []
        x = random.randrange(0, d1)
        y = random.randrange(0, d2)
        state = 'G'
        gold_cell = Cell(x, y, state)

        #generate the rest of the cells in the board
        pit_range = (0, pit)
        obs_range = (pit + 1, pit + obs)
        wumpus_range = (pit + obs + 1, pit + obs + wumpus)

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
                    else:
                        state = 'S'

                    curr_cell = Cell(j, i, state)
                    cells.append(curr_cell)

        self.cells = cells


    def printBoard(self):

        for c in self.cells:
            curr_row = 0
            print(curr_row, c.y)
            '''
            if c.y == curr_row:
                print(c.state + " ")
            else:
                print("\n")
                print(c.state + " ")
                curr_row = curr_row + 1
            '''
    def getStartingPosition(self):
        # Get random cell
        pass




        

