import json
import random

import uvicorn as uvicorn
from fastapi import FastAPI

app = FastAPI()

addresses = []


class Address:
    def __init__(self, user_id):
        self.user_id = user_id
        self.address_name = str(f"{random.randint(1, 1000)} street")


@app.post("/address/create")
def create_address(user_id: int):
    new_address = Address(user_id)
    addresses.append(new_address)
    return new_address


@app.get("/address/getAll")
def get_all_addresses(user_id: int):
    for_user = []

    for address in addresses:
        if user_id == address.user_id:
            for_user.append(address)

    return for_user


if __name__ == '__main__':
    uvicorn.run("main:app", host="127.0.0.1", port=8002)
