from Artificial_Intelligence.Projects.Project2.wumpus_world.Board import Board
from wumpus_world import Cell, Statistics, Explorer
class Logic():
    
    def __init__(self, currentCell, previousCell):
        Cell(self.x, self.y, self.state) = currentCell
        Statistics.cellsExplored(self.previousCell) = previousCell
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
            northNeighbor = [row - 1,currentColumn, current[2]]
            southNeighbor = [row + 1, currentColumn, current[2]]
            for column in currentCell:
                eastNeighbor = [currentRow, column - 1, current[2]]
                westNeighbor = [currentRow, column + 1, current[2]]
        neighbors = [northNeighbor, southNeighbor, eastNeighbor, westNeighbor]
        return neighbors
    

    def getMap(self):
        # Create an empty Map
        knowledgeMap = {}
        # Generate the board and loop through the board
        board = Board.generate_board(self)
        # loop through board, key is position on board
        for row in board:
            for column in board:
                knowledgeMap[row, column] = Logic.getKnowledge(Cell.getCell(self.currentCell))
        return knowledgeMap
        # value will be the percepts from that cell
    # state will be G (gold), W (wumpus), P (pit), O (obstacle), or S (safe)
def decidingMove(currentCell, neighbors):
    knowledgeMap = Logic.getMap(Logic.getKnowledge(currentCell))
    status = neighbors[2]
    bestMoves = []
    for neighbor in neighbors:
        # if any neighbors have stench, set status of neighbor cells to danger, 
        # give option to shoot
        if knowledgeMap[neighbors[2] == "W"]:
            Explorer.shootArrow(neighbors[0] or neighbors[1])
        # if any neighbors have breeze, set status of neighbor cells to danger
        if knowledgeMap[neighbors[2] == "P"]:
            continue

        # this is not a possible move
        if knowledgeMap[neighbors[2] == "O"]:
            neighbors = neighbors.remove(neighbor)
        else: 
            if knowledgeMap[neighbors[2] == "S" or "G"]:
                bestMoves = bestMoves.append(neighbor)
    # Move to the closest safe cell
    return bestMoves

def bestMove(bestMoves, neighbors):
    choices = []
    for choice in bestMoves:
        if neighbors[2] == "S" or "G":
            choices = choices.append(choice)
    return choices
                


# number of wumpuses remaining?
# number of arrows remaining?

