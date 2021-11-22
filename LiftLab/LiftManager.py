import time
from datetime import datetime

from Lift import Lift


class LiftManager():
    lift = Lift()

    def run(self):

        print("If you want to stop the program please type 'exit'")

        while True:
            floor_name = "ground"

            if self.lift.curr_floor > 0:
                floor_name = self.lift.curr_floor

            input_command = input(f"You are currently on the {floor_name} floor. Enter floor to go to: ")

            if input_command == "exit":
                print("Exiting...")
                return

            if input_command.lower() == "ground":
                input_command = 0

            input_command = int(input_command)

            if input_command > self.lift.curr_floor:
                self.lift.go_up(input_command)
            elif input_command < self.lift.curr_floor:
                self.lift.go_down(input_command)
            else:
                if input_command == 0:
                    print("You are now on the ground floor")
                else:
                    print(f"You are on floor {input_command}")
