from wumpus_world import Statistics, Board


class Explorer():

    def __init__(self):
        self.stats = Statistics()
        self.board = Board()
        startPos = Board.starting_position()
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
        # def sense(ind):
        sense = False
    # /*
    # if(pos[0]-1 >= 0):
    #     if(board.cells[pos[0]-1][pos[1]][ind] == True):
    #         sense = True

    # if(pos[1]-1 >= 0):
    #     if(board.cells[pos[0]][pos[1]-1][ind]] == True ):
    #         sense = True

    # if(pos[0]+1 < board.size):
    #     if(board.cells[pos[0]+1][pos[1]][ind]] == True ):
    #         sense = True

    # if(pos[1]+1 < board.size):
    #     if( board.cells[pos[0]][pos[1]+1][ind]] == True):
    #         sense = True

    # */
    # return sense

# def move(dest):
#     move_direction = {dest[0] - pos[0], pos[1] - dest[1]}

#     if Math.abs(move_direction[0] - direction[0]) == 2 or Math.abs(move_direction[1] - direction[1]) == 2 :
#         turnright()
#         turnright()
#         pos[0] += move_direction[0]
#         pos[1] += move_direction[1]

#     else:
#         if pos[1] == dest[0] or pos[0] == -1*dest[1]:
#             turnright()
#             pos[0] += move_direction[0]
#             pos[1] += move_direction[1]

#         else:
# 			if pos[1] == -1*dest[0] or pos[0] == dest[1]:
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
