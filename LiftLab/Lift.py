import time


class Lift():

    def __init__(self):
        self.curr_floor = 0

    def go_up(self, floor_to_go_to):
        if floor_to_go_to > 5:
            print("The maximum floor is 5")
            return

        while self.curr_floor < floor_to_go_to:
            time.sleep(1)
            self.curr_floor += 1
            print(f"You are now on floor {self.curr_floor}")

    def go_down(self, floor_to_go_to):
        if floor_to_go_to < 0:
            print("The minimum floor is Ground")
            return

        while self.curr_floor > floor_to_go_to:
            time.sleep(1)
            self.curr_floor -= 1

            if self.curr_floor != 0:
                print(f"You are now on floor {self.curr_floor}")
            else:
                print("You are now on the ground floor")
