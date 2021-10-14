from wumpus_world import Board, Cell, Logic
from wumpus_world.Statistics import Statistics
from wumpus_world.Logic import get_neighbors

#Used as a reference for moving. Each character is listed in orders of right turns (if you're facing norht and turn right, you're facing east)
directions = ['n', 'e', 's', 'w']


class Explorer:
    def __init__(self, pos):
        self.stats = Statistics(0)
        self.board = Board
        self.pos = pos
        #self.stats.cells_explored += 1
        self.dead = False
        self.direction = 'n'

    def die(self, current_cell):
        stats = Statistics
        state = current_cell.state
        if state == 'W':
            self.dead = True
            stats.death_by_wumpus
            print("Explorer died to a wumpus")

        if state == 'P':
            self.dead = True
            stats.death_by_pit
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
    # Used to move to a neighboring cell 

    ''' 
	:param dest: a character denoting either north, south, east, or west, denoted by 'n', 's', 'e', and 'w' respectively 
	:return: returns true if the explorer moved, false if the explorer hit an object 
    '''

    def move(self, dest, kb, neighbors):
        stats = self.stats
        stats.increment_moves()

        #Gets how many right turns would need to be made (A negative right turn is a left turn)
        current_index = directions.index(self.direction)
        dest_index = directions.index(dest)
        success = True
        diff = current_index - dest_index

		#Calls the turn functions as needed
        if abs(diff) == 2:
            self.turn_left()
            self.turn_left()
        elif diff == 1 or diff == -3:
            self.turn_right()
        elif diff == -1 or diff == 3:
            self.turn_left()

		#Gets the neighbors for reference

		#Moves towards the correct place
        if dest == 'n':
            new_pos = neighbors[0]
        elif dest == 's':
            new_pos = neighbors[1]
        elif dest == 'w':
            new_pos = neighbors[2]
        elif dest == 'e':
            new_pos = neighbors[3]

		#Tests for an obstacle, if there is none then the explorer moves
        if new_pos != None:
            if new_pos.state == 'O':
                success = False
         
        else:
            self.pos = new_pos

        self.stats.increment_moves()
        return success

	#Turns the explorer right once
    def turn_right(self):
        index = directions.index(self.direction)

        if index == 3:
            index = 0
        else:
            index += 1
        self.direction = directions[index]

        self.stats.increment_moves()
        return

	#Turns the explorer left once
    def turn_left(self):
        index = directions.index(self.direction)

        if index == 0:
            index = 3
        else:
            index -= 1
        self.direction = directions[index]

        self.stats.increment_moves()
        pass
