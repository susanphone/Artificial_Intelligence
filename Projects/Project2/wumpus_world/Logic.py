from wumpus_world import Cell
    
'''
:param current_cell: the specified cell
:param board: the current board the explorer is on
:return: returns a list of the neighbor cells & their states in N, S, W, E
'''

def getNeighbors(currentCell, board):

    currentRow = currentCell.y
    currentColumn = currentCell.x
    neighbors = []

    for c in board:
        if c.x == currentRow + 1 and c.y == currentColumn:
            northState = c.state
        if c.x == currentRow - 1 and c.y == currentColumn:
            southState = c.state
        if c.x == currentRow and c.y == currentColumn - 1:
            westState = c.state
        if c.x == currentRow and c.y == currentColumn + 1:
            eastState = c.state

    northNeighbor = Cell(currentRow + 1, currentColumn, northState)
    neighbors.append(northNeighbor)

    southNeighbor = Cell(currentRow - 1, currentColumn, southState)
    neighbors.append(southNeighbor)

    westNeighbor = Cell(currentRow, currentColumn - 1, westState)
    neighbors.append(westNeighbor)

    eastNeighbor = Cell(currentRow, currentColumn + 1, eastState)
    neighbors.append(eastNeighbor)

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
    def decide(self, cell, neighbors):
        # lets look at the neighbor in front of the explorer
        bestMove = [neighbors]
        for cell in neighbors:
            if cell.state == "w":
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
                