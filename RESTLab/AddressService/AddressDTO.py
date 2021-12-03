import random


class Address:
    def __init__(self):
        self.address_name = str(f"{random.randint(1, 1000)} street")
