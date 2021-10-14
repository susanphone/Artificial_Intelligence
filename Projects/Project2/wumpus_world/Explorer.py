from wumpus_world import Statistics, Board, Cell, Logic
from wumpus_world.Logic import get_neighbors

directions = ['n', 'e', 's', 'w']


class Explorer():
    def __init__(self, curr_cell):
        self.stats = Statistics
        self.board = Board
        self.pos = curr_cell
        self.stats.cells_explored += 1
        self.dead = False
        self.direction = 'n'
        self.logic = Logic

    def die(self, current_cell):
        state = current_cell.state

        if state == 'W':
            self.dead = True
            self.stats.death_by_wumpus()
            print("Explorer died to a wumpus")
        if state == 'P':
            self.dead = True
            self.stats.death_by_pit()
            print("Explorer died to a pit :(")

        return

    # :param dest: a character denoting either north, south, east, or west,
    # denoted by 'n', 's', 'e', and 'w' respectively :return: returns whether the explorer moved or not '''


def move(curr_cell, destination, board):
    success = True
    new_pos = curr_cell
    for n in destination:
        while success:
            if n == 'n':
                new_pos = destination[n]
            elif n == 's':
                new_pos = destination[n]
            elif n == 'w':
                new_pos = destination[n]
            elif n == 'e':
                new_pos = destination[n]
            if new_pos.state == 'O':
                success = False

        if not success:
            destination.remove(new_pos)
            Explorer.turn_left(n) or Explorer.turn_right(n)
            new_pos = curr_cell
            Logic.decide(curr_cell, destination, board)
        Statistics.increment_moves()

    return new_pos


def turn_right(direction):
    index = directions.index(direction)
    if index == 3:
        index = 0
    else:
        index += 1
    direction = directions[index]

    Statistics.increment_moves()
    return direction


def turn_left(direction):
    index = directions.index(direction)
    if index == 0:
        index = 3
    else:
        index -= 1
    direction = directions[index]

    Statistics.increment_moves()
    return direction
