import time
from colorama import init
from termcolor import colored

from Lift import Lift


class LiftManager():

    def __init__(self):
        self.lift = Lift()

    def run(self):
        global last_used_time
        last_used_time = time.time()
        last_used_time += 15

        print(colored("If you want to stop the program please type 'exit'", 'yellow'))
        print(colored("If the elevator is not used for 15 seconds it will return to the ground floor", 'yellow'))

        while True:
            floor_name = "ground"

            if self.lift.curr_floor > 0:
                floor_name = self.lift.curr_floor

            input_command = input(
                colored(f"You are currently on the {floor_name} floor. Enter floor to go to: ", 'green'))

            if input_command == "exit":
                print("Exiting...")
                return

            if time.time() >= last_used_time:
                print(colored("The lift was not used for 15 seconds and it returned to the ground floor", 'red'))
                self.lift.curr_floor = 0
                last_used_time = time.time()
                last_used_time += 15
                continue

            if input_command.lower() == "ground":
                input_command = 0

            input_command = int(input_command)

            if input_command > self.lift.curr_floor:
                self.lift.go_up(input_command)
            elif input_command < self.lift.curr_floor:
                self.lift.go_down(input_command)
            # else:
            #     if input_command == 0:
            #         print("You are now on the ground floor")
            #     else:
            #         print(f"You are on floor {input_command}")

            last_used_time = time.time()
            last_used_time += 15
