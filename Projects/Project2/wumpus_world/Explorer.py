from wumpus_world import Statistics, Board, Cell, Logic


class Explorer():

    def __init__(self):
        self.stats = Statistics()
        self.board = Board()
        self.pos = Board.starting_position()
        self.stats.cells_explored += 1
        self.dead = False
        self.direction = [0, 1]

    def die(self, current_cell):
        state = current_cell.state

        if (state == 'W'):
            print("Explorer died to a wumpus")
            Statistics.deathByWumpus()
            return True

        if (state == 'P'):
            print("Explorer died to a pit :(")
            Statistics.deathByPit()
            return True

        return False

        # Used to detect either a scent, breeze, or glimmer. Pass in the truth table index and the function returns true if there is one nearby.
		'''
		:param state: the type of object being looked for, refrence cell for better description
		:return: returns whether the explorer senses the state
		'''
        def sense(self, state):
			#n s w e
			neighbors = getNeighbors(Cell(pos[0], pos[1], 'S'), self.board)
			for cell in neighbors:
				if cell != None and cell.state == state:
					return true

    
    		return false

#	def move(dest):
#		move_direction = {dest[0] - pos[0], pos[1] - dest[1]}

#		if Math.abs(move_direction[0] - direction[0]) == 2 or Math.abs(move_direction[1] - direction[1]) == 2 :
#			turnright()
#			turnright()
#			pos[0] += move_direction[0]
#			pos[1] += move_direction[1]

#		else:
#			if pos[1] == dest[0] or pos[0] == -1*dest[1]:
#				turnright()
#				pos[0] += move_direction[0]
#				pos[1] += move_direction[1]

#			elif pos[1] == -1*dest[0] or pos[0] == dest[1]:
# 				turnleft()
# 				pos[0] += move_direction[0]
# 				pos[1] += move_direction[1]

#     return dest[0] == pos[0] && dest[1] == pos[1]

# public void turnright():
#     if direction[0] != 0 :
#         direction[1] = direction[0] * -1
#         direction[0] = 0

#     if direction[1] != 0 :
#         direction[0] = direction[1]
#         direction[1] = 0

#     moves++

# public void turnleft():
#     if direction[0] != 0 :
#         direction[1] = direction[0]
#         direction[0] = 0

#     if direction[1] != 0 :
#         direction[0] = direction[1] * -1
#         direction[1] = 0

#     moves++
