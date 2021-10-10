

class Cell:

    """
    :param int x: the x position for the current cell
    :param int y: the y position for the current cell
    :param char state: maintains the state of the current cell
    state will be G (gold), W (wumpus), P (pit), O (obstacle), or S (safe)
    """
    def __init__(self, x, y, state):
        self.x = x
        self.y = y
        self.state = state


