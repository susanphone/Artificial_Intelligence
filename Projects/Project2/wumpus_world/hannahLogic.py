from wumpus_world import Cell, Statistics, Explorer

'''

:param current_cell: the specified cell
:param board: the current board the explorer is on
:return: returns a list of the neighbor cells & their states in N, S, W, E
'''
def get_neighbors(current_cell, board):

    current_row = current_cell.y
    current_column = current_cell.x
    neighbors = []

    for c in board:
        if c.x == current_row + 1 and c.y == current_column:
            north_state = c.state
        if c.x == current_row - 1 and c.y == current_column:
            south_state = c.state
        if c.x == current_row and c.y == current_column - 1:
            west_state = c.state
        if c.x == current_row and c.y == current_column + 1:
            east_state = c.state

    north_neighbor = Cell(current_row + 1, current_column, north_state)
    neighbors.append(north_neighbor)

    south_neighbor = Cell(current_row - 1, current_column, south_state)
    neighbors.append(south_neighbor)

    west_neighbor = Cell(current_row, current_column - 1, west_state)
    neighbors.append(west_neighbor)

    east_neighbor = Cell(current_row, current_column + 1, east_state)
    neighbors.append(east_neighbor)

    return neighbors

class hannahLogic():

    def __init__(self, kb):
        self.knowledge_base = kb

    '''
    :param cell: current cell the explorer is in
    :param board: the board the explorer is on
    :return: cell for explorer to move to
    '''
    def decide(self, cell, board):
        # lets look at the neighbor in front of the explorer

