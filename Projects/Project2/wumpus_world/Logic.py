from wumpus_world.Cell import Cell
from collections import defaultdict

'''
:param current_cell: the specified cell
:param board: the current board the explorer is on
:return: returns a list of the neighbor cells & their states in N, S, W, E
'''


def get_neighbors(current_cell, board):
    current_row = current_cell.y
    current_column = current_cell.x
    neighbors = []
    max_row = board.cells[-1].y  # the row for the last cell in the list
    max_column = board.cells[-1].x  # the col for the last cell in the list
    east_state, west_state, north_state, south_state = None, None, None, None

    for c in board.cells:
        if c.y == current_row + 1 and c.x == current_column:
            north_state = c.state
        if c.y == current_row - 1 and c.x == current_column:
            south_state = c.state
        if c.y == current_row and c.x == current_column - 1:
            west_state = c.state
        if c.y == current_row and c.x == current_column + 1:
            east_state = c.state

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

        if clauses:
            kb[curr_cell].append(clauses)
        else:
            if curr_cell not in kb.keys():
                kb[curr_cell] = []

        if len(kb) <= 1:
            cnt = 0

            for n in neighbors:
                if n != None:
                    if n.y == previous.y and n.x == previous.x:
                        pass
                    else:
                        return cnt
                cnt += 1

            else:
                safe_neighbor = False
                cnt = 0
                for cell in neighbors:
                    if cell is not None:
                        if cell in kb:
                            cell_values = kb[cell]
                            if not "stench" in cell_values and \
                                    not "breeze" in cell_values and \
                                    not 'O' in cell_values:
                                safe_neighbor = True
                                return cnt
                    cnt += 1

            if not safe_neighbor:
                # we need to check to see if neighbor's neighbors are in the kb
                cell_direction = 0
                for cell in neighbors:
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
                    if not breeze and not stench:
                        return cell_direction
                    breeze = True
                    stench = True
                    cell_direction += 1

                known_neighbors = 0
                unknown_neighbors = []

                for n in neighbors:
                    stench = True
                    breeze = True
                    if n in kb.keys:
                        known_neighbors += 1
                    else:
                        unknown_neighbors.append(n)
                    if known_neighbors == 3 and stench:
                        kb[unknown_neighbors[0]].append('W')
                    elif known_neighbors == 3 and breeze:
                        kb[unknown_neighbors[0]].append('P')

        return previous


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
