import json

import requests
import uvicorn as uvicorn
from fastapi import FastAPI

from UserDTO import User

app = FastAPI()

user_dictionary = {}


@app.get("/user/create")
def create_user(id: int, first_name: str, last_name: str):
    if id in user_dictionary:
        return "User already exists!"

    new_user = User(id, first_name, last_name)
    user_dictionary[id] = new_user

    new_address = requests.post(f"http://127.0.0.1:8002/address/create?user_id={id}").text
    address_name = json.loads(new_address)["address_name"]
    new_user.addresses_list.append(address_name)

    return json.dumps(new_user.__dict__)


@app.get("/user/get")
def get_user(id: int):
    if id not in user_dictionary:
        return "User doesn't exist!"

    user = user_dictionary[id]
    get_addresses(user)
    get_transactions(user)

    return json.dumps(user.__dict__)


def get_addresses(user):
    new_address = requests.get(f"http://127.0.0.1:8002/address/all?user_id={user.id}")
    addresses_dic = json.loads(new_address.text)
    values = addresses_dic[str(user.id)]

    for address in values:
        address_value = address["address_name"]
        if address_value not in user.addresses_list:
            user.addresses_list.append(address_value)


def get_transactions(user):
    user_transactions_response = requests.get(f"http://127.0.0.1:8003/transactions/all/?user_id={user.id}").text
    user_transactions_list = json.loads(user_transactions_response)
    user.transactions_list = user_transactions_list


if __name__ == '__main__':
    uvicorn.run("main:app", host="127.0.0.1", port=8001)
