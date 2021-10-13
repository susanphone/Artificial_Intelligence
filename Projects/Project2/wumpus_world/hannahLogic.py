from wumpus_world.Cell import Cell

'''

:param current_cell: the specified cell
:param board: the current board the explorer is on
:return: returns a list of the neighbor cells & their states in N, S, W, E
'''


def get_neighbors(current_cell, board):
    current_row = current_cell.y
    current_column = current_cell.x
    neighbors = []
    max_row = board[-1].y  # the row for the last cell in the list
    max_col = board[-1].x  # the col for the last cell in the list

    for c in board:
        if c.x == current_row + 1 and c.y == current_column:
            north_state = c.state
        if c.x == current_row - 1 and c.y == current_column:
            south_state = c.state
        if c.x == current_row and c.y == current_column - 1:
            west_state = c.state
        if c.x == current_row and c.y == current_column + 1:
            east_state = c.state

    if current_row + 1 <= max_row:
        north_neighbor = Cell(current_row + 1, current_column, north_state)
        neighbors.append(north_neighbor)
    else:
        neighbors.append(None)

    if current_row - 1 >= 0:
        south_neighbor = Cell(current_row - 1, current_column, south_state)
        neighbors.append(south_neighbor)
    else:
        neighbors.append(None)

    if current_column - 1 >= 0:
        west_neighbor = Cell(current_row, current_column - 1, west_state)
        neighbors.append(west_neighbor)
    else:
        neighbors.append(None)

    if current_column + 1 <= max_col:
        east_neighbor = Cell(current_row, current_column + 1, east_state)
        neighbors.append(east_neighbor)
    else:
        neighbors.append(None)

    return neighbors





class hannahLogic():

    def __init__(self, kb):
        self.knowledge_base = kb

    '''
    :param cell: current cell the explorer is in
    :param neighbors: neighbors to the current cell
    :return: cell for explorer to move to
    '''

    def add_to_kb(self, n):
        kb = self.knowledge_base
        neighbors = get_neighbors(n)

        for i in neighbors:
            if i.state

    def decide(self, cell, neighbors):

        kb = self.knowledge_base

        for n in neighbors:

            if n in kb.keys():
                n_vals = kb[n]
            else:
                self.add_to_kb(n)
                n_vals = kb[n]

            if not ('stench' in n_vals) or not ('breeze' in n_vals):
                return n
