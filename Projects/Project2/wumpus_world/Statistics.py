import wumpus_world
class Statistics():
    

    def __init__(self):
        self.gold_found = 0
		self.wumps_killed = 0
		self.deaths_by_wumps = 0
		self.deaths_by_pit = 0
		self.cells_explored = 0
		self.moves = 0

    def goldFound(self):
		self.gold_found += 1
        pass

    def WumpusKilled(self):
		self.wumps_killed += 1
        pass

    def deathByWumpus(self):
		self.deaths_by_wumps += 1
        pass

    def deathByPit(self):
		self.deaths_by_pit += 1
        pass

    def cellsExplored(self):
		self.cells_explored += 1
        pass

	def incrementMoves(self):
		self.moves += 1
		pass
