from wumpus_world.Cell import Cell

'''
:param current_cell: the specified cell
:param board: the current board the explorer is on
:return: returns a list of the neighbor cells & their states in N, S, W, E
'''


def get_neighbors(current_cell, board):
    current_row = current_cell.x
    current_column = current_cell.y
    neighbors = []
    max_row = board.cells[-1].x  # the row for the last cell in the list
    max_column = board.cells[-1].y  # the col for the last cell in the list
    east_state, west_state, north_state, south_state = None, None, None, None

    for c in board.cells:
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
        neighbors.append([north_neighbor.x, north_neighbor.y, north_neighbor.state])

    if current_row - 1 >= 0:
        south_neighbor = Cell(current_row - 1, current_column, south_state)
        neighbors.append([south_neighbor.x, south_neighbor.y, south_neighbor.state])

    if current_column - 1 >= 0:
        west_neighbor = Cell(current_row, current_column - 1, west_state)
        neighbors.append([west_neighbor.x, west_neighbor.y, west_neighbor.state])

    if current_column + 1 <= max_column:
        east_neighbor = Cell(current_row, current_column + 1, east_state)
        neighbors.append([east_neighbor.x, east_neighbor.y, east_neighbor.state])

    # if current_column - 1 < 0 or current_row -1 < 0:
    #     continue
    else:
        neighbors.append(None)
    # for n in neighbors:
    #     for i in n:
    #         print(i)
    return neighbors


class Logic:

    def __init__(self, kb):
        self.knowledge_base = kb

    '''
    :param cell: current cell the explorer is in
    :param board: the board the explorer is on
    :return: cell for explorer to move to
    '''

    def decide(self, curr_cell, neighbors, board):
        kb = self.knowledge_base
        clauses = []

        # first order logic
        for neighbor in neighbors:
            if neighbor is not None:
                for state in neighbor:
                    if state is not None:
                        # if a Wumpus is in a neighboring cell, then stench is true
                        if state == 'W':
                            clauses.append("stench")
                        # if a pit is in a neighboring cell, then breeze is true
                        if state == 'P':
                            clauses.append("breeze")
                        if state == 'S':
                            clauses.append(state)

        if not clauses:
            kb.update({curr_cell: 'S'})
            clauses.append(curr_cell)

        kb.update({curr_cell: clauses})

        if len(kb) < 1:
            for n in neighbors:
                if n is not None:
                    return n
        for clause in clauses:
            print(clauses)
            print(clause)

        for n in neighbors:
            if n is not None:

                return n

            else:
                safe_neighbor = False
                for cell in neighbors:
                    if cell is not None:
                        if cell in kb:
                            cell_values = kb[cell]
                            if "stench" not in cell_values and \
                                    "breeze" not in cell_values and \
                                    'O' not in cell_values:
                                safe_neighbor = True
                                return cell
            if not safe_neighbor:
                # we need to check to see if neighbor's neighbors are in the kb
                for cell in neighbors:
                    if cell is not None:
                        new_neighbors = get_neighbors(cell, board)
                        for n in new_neighbors:
                            if n is not None:
                                if n in kb:
                                    return n

#
# ('\n'
#  '    #  the best choice for the explorer\n'
#  '    def bestMove(self, cell):\n'
#  '        kb = self.knowledge_base\n'
#  '\n'
#  '\n'
#  '        for choice in clauses:\n'
#  '            # if Wumpus in neighbor, give explorer chance to shoot\n'
#  '            if choice.state == \'W\':\n'
#  '                print("shoot")\n'
#  '                Explorer.shootArrow()\n'
#  '                hit = False\n'
#  '                if Explorer.shootArrow():\n'
#  '                    pass\n'
#  '                    # if hit == True:\n'
#  '                    #     Statistics.wumpusKilled()\n'
#  '                    #     remainingArrows -= 1\n'
#  '                    # else: \n'
#  '                    #     remainingArrows -= 1\n'
#  '                else:\n'
#  '                    continue\n'
#  '\n'
#  '            #  if safe cell, in current direction, go forward\n'
#  '            if choice == "S" or choice == "G":\n'
#  '                print("move")\n'
#  '                Explorer.move()\n'
#  '                # else turn to nearest safe cell\n'
#  '            else:\n'
#  '                print("turn") \n'
#  '                Explorer.turnRight() or Explorer.turnLeft()\n'
#  '                continue\n'
#  '        return\n')
