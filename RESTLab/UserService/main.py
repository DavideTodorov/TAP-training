import json

import requests
import uvicorn as uvicorn
from fastapi import FastAPI

app = FastAPI()

user_dictionary = {}


class User:
    def __init__(self, id, first_name, last_name):
        self.id = id
        self.first_name = first_name
        self.last_name = last_name


@app.get("/user/create")
def create_user(id: int, first_name: str, last_name: str):
    new_user = User(id, first_name, last_name)
    user_dictionary[id] = new_user

    requests.post(f"http://127.0.0.1:8002/address/create?user_id={id}")

    return json.dumps(new_user.__dict__)


@app.get("/user/get")
def get_user(id: int):
    user = user_dictionary[id]
    return json.dumps(user.__dict__)


if __name__ == '__main__':
    uvicorn.run("main:app", host="127.0.0.1", port=8001)
