import wumpus_world


class Statistics:

    def __init__(self, moves):
        self.gold_found = 0
        self.wumps_killed = 0
        self.deaths_by_wumps = 0
        self.deaths_by_pit = 0
        self.cells_explored = 0
        self.moves = moves

    def gold_found(self):
        gf = self.gold_found + 1
        return gf

    def wumpus_killed(self):
        wk = self.wumps_killed + 1
        return wk

    def death_by_wumpus(self):
        dbw = self.deaths_by_wumps + 1
        return dbw

    def death_by_pit(self):
        dbp = self.deaths_by_pit + 1
        return dbp

    def cells_explored(self):
        ce = self.cells_explored + 1
        return ce

    def increment_moves(self):
        im = self.moves + 1
        return im
