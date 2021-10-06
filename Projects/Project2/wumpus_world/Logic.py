from Artificial_Intelligence.Projects.Project2.wumpus_world.Explorer import Explorer
from wumpus_world import Cell, Statistics
class Logic():
    
    def __init__(self):
        currentCell = Cell.getCell(self.currentCell)
        previousCell = Statistics.cellsExplored(self.previousCell)
        

    def getKnowledge(self, currentCell):
        safeCell = False
        # separate row and column
        current = currentCell.split(",")
        currentRow = current[0]
        currentColumn = current[1]
        # get truth table from neighbors
        northNeighbor = [0,0]
        southNeighbor = [0,0]
        westNeighbor = [0,0]
        eastNeighbor = [0,0]

        for row in currentCell:
            northNeighbor = [row - 1,currentColumn]
            southNeighbor = [row + 1, currentColumn]
            for column in currentCell:
                eastNeighbor = [currentRow, column - 1]
                westNeighbor = [currentRow, column + 1]
        neighbors = [northNeighbor, southNeighbor, eastNeighbor, westNeighbor]
        return neighbors

def decide(currentCell, neighbors):
    neighbors = Logic.getKnowledge(currentCell)
    status = []
    for neighbor in neighbors:
        # if any neighbors have stench, set status of neighbor cells to danger, 
        # give option to shoot
        if neighbor["stench"] == True:
            stat = Cell.updateCell(neighbor)
            status = status.append(stat)
            Explorer.shootArrow(neighbors)

        # if any neighbors have breeze, set status of neighbor cells to danger
        if neighbor["breeze"] == True:
            stat = Cell.updateCell(neighbor)
            status = status.append(stat)


        # this is not a possible move
        if neighbor["obstacle"] == True:
            neighbors = neighbors.remove(neighbor)
        else: status[neighbors] = True

    return neighbors
