import json
import random

import uvicorn as uvicorn
from fastapi import FastAPI

from Address import Address

app = FastAPI()

addresses = {}


@app.post("/address/create")
def create_address(user_id: int):
    new_address = Address()

    if user_id not in addresses:
        addresses[user_id] = []

    addresses[user_id].append(new_address)

    return new_address


@app.get("/address/all")
def get_all_addresses(user_id: int):
    if user_id not in addresses:
        return []

    return {user_id: addresses[user_id]}


@app.post("/address/delete")
def delete_addresses(user_id: int):
    del addresses[user_id]
    return f"Addresses for user with id {user_id} were deleted."


if __name__ == '__main__':
    uvicorn.run("main:app", host="127.0.0.1", port=8002)
