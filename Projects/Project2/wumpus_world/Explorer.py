from wumpus_world import Statistics, Board, Cell, Logic
from wumpus_world.Logic import get_neighbors

directions = ['n', 'e', 's', 'w']


class Explorer():
    def __init__(self):
        self.stats = Statistics()
        self.board = Board()
        self.pos = Board.starting_position()
        self.stats.cells_explored += 1
        self.dead = False
        self.direction = 'n'

    def die(self, current_cell):
        stats = Statistics()
        state = current_cell.state
        if state == 'W':
            self.dead = True
            stats.death_by_wumpus()
            print("Explorer died to a wumpus")

        if state == 'P':
            self.dead = True
            stats.death_by_pit()
            print("Explorer died to a pit :(")

        return

    # Used to detect either a scent, breeze, or glimmer.
    # Pass in the truth table index and the function returns true
    # if there is one nearby.
    '''
    :param state: the type of object being looked for, reference cell for better description
    :return: returns whether the explorer senses the state
    '''

    # def sense(self, state):
    #     # n s w e
    #     danger = False
    #     neighbors = get_neighbors(self.pos, self.board)
    #     for cell in neighbors:
    #         if cell is not None and cell.state == state:
    #             return danger
    #         return danger
    #
    # # Used to move to a neighboring cell ''' :param dest: a character denoting either north, south, east, or west,
    # denoted by 'n', 's', 'e', and 'w' respectively :return: returns whether the explorer moved or not '''

    def move(self, dest):
        stats = Statistics()
        stats.increment_moves()
        current_index = directions.index(self.direction)
        dest_index = directions.index(dest)

        diff = current_index - dest_index

        if abs(diff) == 2:
            self.turn_left()
            self.turn_left()
        elif diff == 1 or diff == 3:
            self.turn_right()
        elif diff == -1 or diff == -3:
            self.turn_left()

        neighbors = get_neighbors(self.pos, self.board)
        success = False
        if dest == 'n':
            self.pos = neighbors[0]
            success = True
            return success
        elif dest == 's':
            self.pos = neighbors[1]
            success = True
            return success
        elif dest == 'w':
            self.pos = neighbors[2]
            success = True
            return success
        elif dest == 'e':
            self.pos = neighbors[3]
            success = True
            return success

        return success

    def turn_right(self):
        stats = Statistics()
        stats.increment_moves()
        index = directions.index(self.direction)

        if index == 3:
            index = 0
        else:
            index += 1
        self.direction = directions[index]

        self.stats.incrementMoves()
        return

    def turn_left(self):
        stats = Statistics()
        stats.increment_moves()
        index = directions.index(self.direction)

        if index == 3:
            index = 0
        else:
            index += 1
        self.direction = directions[index]

        self.stats.incrementMoves()
        pass
