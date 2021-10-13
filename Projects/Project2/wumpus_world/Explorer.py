from wumpus_world import Statistics, Board, Cell, Logic


class Explorer():

    def __init__(self):
        self.stats = Statistics()
        self.board = Board()
        self.pos = Board.starting_position()
        self.stats.cells_explored += 1
        self.dead = False
        self.direction = 'n'

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


		# Used to move to a neighboring cell
		'''
		:param dest: a character denoting either north, south, east, or west, denoted by 'n', 's', 'e', and 'w' respectively
		:return: returns whether the explorer moved or not
		'''
	def move(dest):
		directions = ['n', 'e', 's', 'w']
		current_index = directions.index(self.direction)
		dest_index = directions.index(dest)

		diff = current_index - dest_index

		if abs(diff) == 2:
			self.turnleft()
			self.turnleft()
		elif diff == 1 or diff == 3:
			self.turnright()
		elif diff == -1 or diff == -3:
			self.turnleft()

		neighbors = getNeighbors(self.pos, self.board)

		if dest = 'n':
			self.pos = neighbors[0]
			return true
		elif dest = 's':
			self.pos = neighbors[1]
			return true
		elif dest = 'w':
			self.pos = neighbors[2]
			return true
		elif dest = 'e':
			self.pos = neighbors[3]
			return true
		else:
			return false

	def turnright():
		directions = ['n', 'e', 's', 'w']

		index = directions.index(self.direction)

		if index == 3 :
			index = 0
		else:
			index +=1
		self.direction = directions[index]

		self.stats.incrementMoves()
		return
		
	def turnleft():
		directions = ['n', 'e', 's', 'w']

		index = directions.index(self.direction)

		if index == 3 :
			index = 0
		else:
			index +=1
		self.direction = directions[index]

		self.stats.incrementMoves()
		return