from wumpus_world.Cell import Cell
from wumpus_world.Explorer import Explorer
    
'''
:param currentCell: the specified cell
:param board: the current board the explorer is on
:return: returns a list of the neighbor cells & their states in N, S, W, E
'''

def get_neighbors(currentCell, board):

    currentRow = currentCell.y
    currentColumn = currentCell.x
    neighbors = []
    maxRow = board.cells[-1].y #the row for the last cell in the list
    maxColumn = board.cells[-1].x #the col for the last cell in the list


    for c in board.cells:
        if c.x == currentRow + 1 and c.y == currentColumn:
            northState = c.state
        if c.x == currentRow - 1 and c.y == currentColumn:
            southState = c.state
        if c.x == currentRow and c.y == currentColumn - 1:
            westState = c.state
        if c.x == currentRow and c.y == currentColumn + 1:
            eastState = c.state

    if currentRow + 1 <= maxRow:
        northNeighbor = Cell(currentRow + 1, currentColumn, northState)
        neighbors.append(northNeighbor)
    else:
        neighbors.append(None)

    if currentRow - 1 >= 0:
        southNeighbor = Cell(currentRow - 1, currentColumn, southState)
        neighbors.append(southNeighbor)
    else:
        neighbors.append(None)

    if currentColumn - 1 >= 0:
        westNeighbor = Cell(currentRow, currentColumn - 1, westState)
        neighbors.append(westNeighbor)
    else:
        neighbors.append(None)

    if currentColumn + 1 <= maxColumn:
        eastNeighbor = Cell(currentRow, currentColumn + 1, eastState)
        neighbors.append(eastNeighbor)
    else:
        neighbors.append(None)

    return neighbors

def getMap(board):
    c = Cell()
    # Create an empty Map
    knowledgeMap = {}
    # loop through board, key is position on board
    for c in board:
            knowledgeMap[c.row, c.column] = c.state
    return knowledgeMap
    # value will be the percepts from that cell



class Logic():

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
        # lets look at the neighbor in front of the explorer
        for cell in neighbors:
            if cell != None:
                # if a Wumpus is in a neighboring cell, then stench is true
                if cell.state == 'W':
                    clauses.append("stench")
                # if a pit is in a neighboring cell, then breeze is true
                if cell.state == 'P':
                    clauses.append("breeze")

        if not clauses:
            kb[curr_cell].append('S')

        kb[curr_cell].append(clauses)

        if len(kb) < 1:
            for n in neighbors:
                if None != n:
                    return n

        if 'S' in clauses:
            for n in neighbors:
                if n != None:
                    return n

        else:
            safe_neighbor = False
            for cell in neighbors:
                if cell != None:
                    if cell in kb.keys():
                        cell_vals = kb[cell]
                        if not "stench" in cell_vals and not "breeze" in cell_vals and not "O" in cell_vals:
                            safe_neighbor = True
                            return cell
            if not safe_neighbor:
                # we need to check to see if neighbor's neighbors are in the kb
                for cell in neighbors:
                    if cell != None:
                        new_neighbors = get_neighbors(cell, board)
                        for n in new_neighbors:
                            if n in kb.keys():
                                return n


('\n'
 '    #  the best choice for the explorer\n'
 '    def bestMove(self, cell):\n'
 '        kb = self.knowledge_base\n'
 '\n'
 '\n'
 '        for choice in clauses:\n'
 '            # if Wumpus in neighbor, give explorer chance to shoot\n'
 '            if choice.state == \'W\':\n'
 '                print("shoot")\n'
 '                Explorer.shootArrow()\n'
 '                hit = False\n'
 '                if Explorer.shootArrow():\n'
 '                    pass\n'
 '                    # if hit == True:\n'
 '                    #     Statistics.wumpusKilled()\n'
 '                    #     remainingArrows -= 1\n'
 '                    # else: \n'
 '                    #     remainingArrows -= 1\n'
 '                else:\n'
 '                    continue\n'
 '\n'
 '            #  if safe cell, in current direction, go forward\n'
 '            if choice == "S" or choice == "G":\n'
 '                print("move")\n'
 '                Explorer.move()\n'
 '                # else turn to nearest safe cell\n'
 '            else:\n'
 '                print("turn") \n'
 '                Explorer.turnRight() or Explorer.turnLeft()\n'
 '                continue\n'
 '        return\n')