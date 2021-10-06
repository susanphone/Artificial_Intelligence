import wumpus_world
class Cell():
    # fields
    

    # constructor
    def __init__(self):
        pass

    def getCell(self):
        # get table and state of cell
        pass

    def setCell(self):
        pass

    def updateCell(self, isSafe):
        if self.isSafe == False:
            isSafe = True
        if self.isSafe == True:
            isSafe = False
        # update cell to explored or danger cell to safe cell
        return isSafe


# booleans are the following
def gold():
        pass

def wumpus():
        pass

def obstacle():
        pass

def empty():
        pass

