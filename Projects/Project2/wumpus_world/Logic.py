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
    def decide(self, neighbors):
        stench = False
        breeze = False
        bump = False
        safe = False
        glitter = False
        clauses = []


        # first order logic
        # lets look at the neighbor in front of the explorer
        for cell in neighbors:
            # if a Wumpus is in a neighboring cell, then stench is true
            if cell.state == 'W':
                stench = True
                clauses.append(stench)
            # if a pit is in a neighboring cell, then breeze is true
            if cell.state == 'P':
                breeze = True
                clauses.append(breeze)
            # if no pit or wumpus
            if cell.state == 'G':
                glitter = True
                clauses.append(glitter)
            if cell.state == 'B':
                bump = True
                clauses.append(bump)
            else:
                safe = True
                clauses.append(safe)
        return clauses

    #  the best choice for the explorer
    def bestMove(self, clauses):
        self.knowledge_base
        for choice in clauses:
            # if Wumpus in neighbor, give explorer chance to shoot
            if choice.state == 'W':
                print("shoot")
                Explorer.shootArrow()
                hit = False
                if Explorer.shootArrow():
                    pass
                    # if hit == True:
                    #     Statistics.wumpusKilled()
                    #     remainingArrows -= 1
                    # else: 
                    #     remainingArrows -= 1
                else:
                    continue

            #  if safe cell, in current direction, go forward
            if choice == "S" or choice == "G":
                print("move")
                Explorer.move()
                # else turn to nearest safe cell
            else:
                print("turn") 
                Explorer.turnRight() or Explorer.turnLeft()
                continue
        return

    # if we know the states of the surrounding cells, 
    # then we can infer the state of a neighboring cell
    def elementaryMyDearWatson(self):
        pass
