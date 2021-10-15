from wumpus_world import Board
from wumpus_world.Statistics import Statistics

# Used as a reference for moving. Each character is listed in orders of right turns
# (if you're facing north and turn right, you're facing east)
directions = ['n', 'e', 's', 'w']


class Explorer:

    """
    :param pos: position of the explorer
    :param stats: a Statistics object to keep track of different statistics
    """

    def __init__(self, pos, stats):

        self.stats = stats
        self.board = Board
        self.pos = pos
        self.dead = False
        self.direction = 'n'

    # def die(self, current_cell):
    #     stats = Statistics
    #     state = current_cell.state
    #     if state == 'W':
    #         self.dead = True
    #         stats.death_by_wumpus()
    #         print("Explorer died to a wumpus")
    #
    #     if state == 'P':
    #         self.dead = True
    #         stats.death_by_pit()
    #         print("Explorer died to a pit :(")
    #
    #     return

    """
        :param dest: direction of the cell the explorer is moving to
        :param kb: the knowledge base
        :param neighbors: the neighbors of the current cell the explorer is in
        :return: True if explorer can move, False if not
    """

    def move(self, dest, neighbors):

        stats = self.stats
        stats.moves = stats.increment_moves()

        # Gets how many right turns would need to be made (A negative right turn is a left turn)
        current_index = directions.index(self.direction)
        dest_index = directions.index(dest)
        success = True
        diff = current_index - dest_index

        # Calls the turn functions as needed
        if abs(diff) == 2:
            self.turn_left()
            self.turn_left()
        elif diff == 1 or diff == -3:
            self.turn_right()
        elif diff == -1 or diff == 3:
            self.turn_left()

        # Moves towards the correct place
        if dest == 'n':
            new_pos = neighbors[0]
        elif dest == 's':
            new_pos = neighbors[1]
        elif dest == 'w':
            new_pos = neighbors[2]
        elif dest == 'e':
            new_pos = neighbors[3]

        if new_pos != None:
            self.pos = new_pos
        else:
            success = False
        stats.moves = stats.increment_moves()
        return success

    # Turns the explorer right once
    def turn_right(self):
        index = directions.index(self.direction)

        if index == 3:
            index = 0
        else:
            index += 1
        self.direction = directions[index]

        self.stats.increment_moves()
        return

    # Turns the explorer left once
    def turn_left(self):
        index = directions.index(self.direction)

        if index == 0:
            index = 3
        else:
            index -= 1
        self.direction = directions[index]

        self.stats.increment_moves()
