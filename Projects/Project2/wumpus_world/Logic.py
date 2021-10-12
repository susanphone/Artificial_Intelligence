from Artificial_Intelligence.Projects.Project2.wumpus_world.Board import Board
from wumpus_world import Cell, Explorer
class Logic():
    
    def __init__(self, currentCell, previousCell, board):
        Cell(self.x, self.y, self.state) = currentCell
        Statistics.cellsExplored(self.previousCell) = previousCell
        Board.generate_board(self) = board
    #  know number of wumpuses, starting position, number of arrows

    
    def getKnowledge(self, currentCell):
        # separate row and column
        current = currentCell.split(",")
        currentRow = current[0]
        currentColumn = current[1]
        currentState = current[2]
        # get truth table from neighbors
        northNeighbor = [0,0, current[2]]
        southNeighbor = [0,0, current[2]]
        westNeighbor = [0,0, current[2]]
        eastNeighbor = [0,0, current[2]]

        for row in currentCell:
            northNeighbor = [row - 1,currentColumn, current[2], currentState]
            southNeighbor = [row + 1, currentColumn, current[2], currentState]
            for column in currentCell:
                eastNeighbor = [currentRow, column - 1, current[2], currentState]
                westNeighbor = [currentRow, column + 1, current[2], currentState]
        neighbors = [northNeighbor, southNeighbor, eastNeighbor, westNeighbor]
        return neighbors
    

    def getMap(self, board):
        # Create an empty Map
        knowledgeMap = {}
        # loop through board, key is position on board
        for row in board:
            for column in board:
                knowledgeMap[row, column] = Logic.getKnowledge(Cell(self.currentCell))
        return knowledgeMap
        # value will be the percepts from that cell

def decidingMove(currentCell, neighbors, previousCell):
    knowledgeMap = Logic.getMap(Logic.getKnowledge(currentCell))
    bestMoves = []
    for neighbor in neighbors:
        status = neighbor[2]
        # if any neighbors have stench, set status of neighbor cells to danger, 
        # give option to shoot
        if knowledgeMap[status == "W"]:
            Explorer.shootArrow(neighbors[0] or neighbors[1])
        # if any neighbors have breeze, set status of neighbor cells to danger
        if knowledgeMap[status == "P"]:
            continue

        # this is not a possible move
        if knowledgeMap[status == "O"]:
            neighbors = neighbors.remove(neighbor)
        else: 
            if knowledgeMap[status == "S" or "G"]:
                bestMoves.append(neighbor)
                # add the previous cell as a possible choice
            bestMoves.append(previousCell)
    # Move to the closest safe cell
    return bestMoves

def bestMove(bestMoves):
    choices = []
    for choice in bestMoves:
        
        if choice == "S" or choice == "G":
            choices = choices.append(choice)
    return choices
                


# number of wumpuses remaining?
# number of arrows remaining?

