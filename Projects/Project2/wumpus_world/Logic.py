from wumpus_world.Cell import Cell

"""
:param current_cell: the specified cell
:param board: the current board the explorer is on
:return: returns a list of the neighbor cells & their states in N, S, W, E
"""


def get_neighbors(current_cell, board):

    current_row = current_cell.y
    current_column = current_cell.x
    neighbors = []
    max_row = board.cells[-1].y  # the row for the last cell in the list
    max_column = board.cells[-1].x  # the col for the last cell in the list
    east_state, west_state, north_state, south_state = None, None, None, None

    # grab the state of the north, south, west , and east neighbors
    for c in board.cells:
        if c.y == current_row + 1 and c.x == current_column:
            north_state = c.state
        if c.y == current_row - 1 and c.x == current_column:
            south_state = c.state
        if c.y == current_row and c.x == current_column - 1:
            west_state = c.state
        if c.y == current_row and c.x == current_column + 1:
            east_state = c.state

    # if we are not at an edge, add the neighbor to the list
    # else append None in its place
    if current_row + 1 <= max_row:
        north_neighbor = Cell(current_column, current_row + 1, north_state)
        neighbors.append(north_neighbor)
    else:
        neighbors.append(None)

    if current_row - 1 >= 0:
        south_neighbor = Cell(current_column, current_row - 1, south_state)
        neighbors.append(south_neighbor)
    else:
        neighbors.append(None)

    if current_column - 1 >= 0:
        west_neighbor = Cell(current_column - 1, current_row, west_state)
        neighbors.append(west_neighbor)
    else:
        neighbors.append(None)

    if current_column + 1 <= max_column:
        east_neighbor = Cell(current_column + 1, current_row, east_state)
        neighbors.append(east_neighbor)
    else:
        neighbors.append(None)

    return neighbors


class Logic:

    """
    :param kb: the knowledge base that is a dictionary; keys are Cells; values are a list
    values can be "stench", "breeze", "O", "W", or "P"
    """

    def __init__(self, kb):

        self.knowledge_base = kb

    '''
    :param cell: current cell the explorer is in
    :param board: the board the explorer is on
    :return: cell for explorer to move to
    '''

    def decide(self, curr_cell, neighbors, board, previous):
        kb = self.knowledge_base
        clauses = []

        # first order logic
        for neighbor in neighbors:
            if neighbor is not None:

                # if a Wumpus is in a neighboring cell, then stench is true
                if neighbor.state == 'W':
                    clauses.append("stench")
                # if a pit is in a neighboring cell, then breeze is true
                if neighbor.state == 'P':
                    clauses.append("breeze")

        # append the clauses to the cell's values in the knowledge base
        if clauses:
            kb[curr_cell].append(clauses)
        # if there wasn't a stench or breeze & the current cell isn't already in the knowledge base
        # we add an empty list as the value
        else:
            if curr_cell not in kb.keys():
                kb[curr_cell] = []

        # if we don't have anything except the current cell if the knowledge base, then we just move north
        if len(kb) >= 1:
            cnt = 0
            # loop through the current cell's neighbors
            for n in neighbors:
                if n is not None:
                    # make sure selected neighbor isn't the previous cell
                    if n.y == previous.y and n.x == previous.x:
                        pass
                    else:
                        return cnt
                cnt += 1

        # when we have information in the knowledge base
        else:
            wumpus_cell = None
            pit_cell = None
            # check if we know where the stench or breeze is coming from
            if "stench" in clauses or "breeze" in clauses:
                for cell in neighbors:
                    if cell != None:
                        if cell in kb:
                            if 'W' in kb[cell]:
                                wumpus_cell = cell
                                # print("Cell with wumpus: ", cell.y, cell.x)
                            if 'P' in kb[cell]:
                                pit_cell = cell
                                # print("Cell with pit: ", cell.y, cell.x)
                # if we have already determined a neighbor has a pit or wumpus
                # we choose from any of the other neighbors
                if pit_cell != None and ("stench" not in clauses or wumpus_cell != None):
                    position = 0
                    for n in neighbors:
                        if n != pit_cell and n != wumpus_cell:
                            return position
                        position += 1

                if wumpus_cell != None and ("breeze" not in clauses or pit_cell != None):
                    position = 0
                    for n in neighbors:
                        if n != pit_cell and n != wumpus_cell:
                            return position
                        position += 1
            cnt = 0
            for cell in neighbors:
                if cell is not None:
                    if cell in kb:
                        cell_values = kb[cell]
                        if not "stench" in cell_values and \
                                not "breeze" in cell_values and \
                                not 'O' in cell_values:
                            return cnt
                    else:
                        cnt += 1
            # since we cannot determine if a neighbor is safe
            # we need to check to see if neighbor's neighbors are in the kb
            cell_direction = 0
            for cell in neighbors:
                # keep track of if there was a stench or breeze in the neighbor's neighbors
                breeze = True
                stench = True
                if cell is not None:
                    new_neighbors = get_neighbors(cell, board)
                    for n in new_neighbors:
                        if n is not None:
                            if "breeze" not in kb[n]:
                                breeze = False
                            if "stench" not in kb[n]:
                                stench = False

                # if a neighbor's neighbor didn't have a stench or breeze then we can move there
                if not breeze and not stench:
                    return cell_direction
                cell_direction += 1

            # logic for if we can determine a cell has a wumpus or pit
            known_neighbors = 0
            unknown_neighbors = []
            stench = False
            breeze = False

            if "stench" in clauses:
                stench = True
            if "breeze" in clauses:
                breeze = True

            for n in neighbors:
                if n != None:
                    # do not add another Wumpus or Pit if it has already been determined
                    # should avoid setting a neighbor to have both a pit and a wumpus
                    if 'W' in kb[n]:
                        stench = False
                    if 'P' in kb[n]:
                        breeze = False
                    # if neighbor in kb add to known_neighbors
                    if n in kb.keys:
                        known_neighbors += 1
                    else:
                        unknown_neighbors.append(n)
            # if we know three of our neighbors are safe, but there is still a stench,
            # then the 4th neighbor has a wumpus
            if known_neighbors == 3 and stench:
                kb[unknown_neighbors[0]].append('W')
            # if we know three of our neighbors are safe, buth there is still a breeze,
            # then the 4th neighbor has a pit
            elif known_neighbors == 3 and breeze:
                kb[unknown_neighbors[0]].append('P')

        return previous

