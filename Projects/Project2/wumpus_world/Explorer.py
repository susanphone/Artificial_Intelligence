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

    def move(self, curr_cell):
        success = True
        new_pos = curr_cell
        neighbors = get_neighbors(curr_cell, self.board)

        while success:
            n = self.logic.decide(curr_cell, neighbors, self.board)
            if n == 'n':
                new_pos = neighbors[0]
            elif n == 's':
                new_pos = neighbors[1]
            elif n == 'w':
                new_pos = neighbors[2]
            elif n == 'e':
                new_pos = neighbors[3]
            if new_pos.state == 'O':
                success = False

            if not success:
                neighbors.remove(new_pos)
                Explorer.turn_left() or Explorer.turn_right()
                new_pos = curr_cell
                self.logic.decide(curr_cell, neighbors, self.board)
            self.stats.increment_moves()

        return new_pos

    def turn_right(self):
        index = directions.index(self.direction)
        if index == 3:
            index = 0
        else:
            index += 1
        direction = directions[index]

        self.stats.increment_moves()
        return direction

    def turn_left(self):
        index = directions.index(self.direction)
        if index == 0:
            index = 3
        else:
            index -= 1
        direction = directions[index]

        self.stats.increment_moves()
        return direction
