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
    max_row = board.cells[-1].y #the row for the last cell in the list
    max_col = board.cells[-1].x #the col for the last cell in the list

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
        north_neighbor = Cell( current_column, current_row + 1, north_state)
        neighbors.append(north_neighbor)
    else:
        neighbors.append(None)

    if current_row - 1 >= 0:
        south_neighbor = Cell(current_column, current_row - 1,  south_state)
        neighbors.append(south_neighbor)
    else:
        neighbors.append(None)

    if current_column - 1 >= 0:
        west_neighbor = Cell( current_column - 1, current_row, west_state)
        neighbors.append(west_neighbor)
    else:
        neighbors.append(None)

    if current_column + 1 <= max_col:
        east_neighbor = Cell( current_column + 1, current_row, east_state)
        neighbors.append(east_neighbor)
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
    def decide(self, neighbors):
        # lets look at the neighbor in front of the explorer
        bestMove = [neighbors]
        for cell in neighbors:
            if cell.state == "W":
                bestMove.remove(cell)
            if cell.state == "P":
                bestMove.remove(cell)
            else:
                return bestMove
                

    # def decidingMove(currentCell, neighbors, previousCell):
    #     knowledgeMap = Logic.getMap(Logic.getKnowledge(currentCell))
    #     bestMoves = []
    #     for neighbor in neighbors:
    #         status = neighbor[2]
    #         # if any neighbors have stench, set status of neighbor cells to danger, 
    #         # give option to shoot
    #         if knowledgeMap[status == "W"]:
    #             Explorer.shootArrow(neighbors[0] or neighbors[1])
    #         # if any neighbors have breeze, set status of neighbor cells to danger
    #         if knowledgeMap[status == "P"]:
    #             continue

    #         # this is not a possible move
    #         if knowledgeMap[status == "O"]:
    #             neighbors = neighbors.remove(neighbor)
    #         else: 
    #             if knowledgeMap[status == "S" or "G"]:
    #                 bestMoves.append(neighbor)
    #                 # add the previous cell as a possible choice
    #             bestMoves.append(previousCell)
    #     # Move to the closest safe cell
    #     return bestMoves

    # def bestMove(self, bestMoves):
    #     choices = []
    #     for choice in bestMoves:
            
    #         if choice == "S" or choice == "G":
    #             choices = choices.append(choice)
    #     return choices
                