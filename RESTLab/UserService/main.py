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

    return new_user


@app.get("/user/get")
def get_user(id: int, transactions_count: int):
    if id not in user_dictionary:
        return "User does not exist!"

    user = user_dictionary[id]
    get_addresses(user)
    get_transactions(user, transactions_count)

    return user


@app.post("/user/update")
def update_user(id: int, new_first_name: str, new_last_name: str):
    if id not in user_dictionary:
        return "User does not exist!"

    user = user_dictionary[id]
    user.first_name = new_first_name
    user.last_name = new_last_name

    return user


@app.post("/user/delete")
def delete_user(user_id: int):
    if user_id not in user_dictionary:
        return "User does not exist!"

    requests.post(f"http://127.0.0.1:8002/address/delete?user_id={user_id}")
    requests.post(f"http://127.0.0.1:8003/transactions/delete?user_id={user_id}")

    del user_dictionary[user_id]
    return f"User with id {user_id} was deleted."


@app.post("/user/transactions/create")
def create_transaction_for_user(user_id: int):
    transaction_response = requests.post(f"http://127.0.0.1:8003/transactions/create/?user_id={user_id}").text
    transaction_response = json.loads(transaction_response)
    return f"New transaction: {transaction_response}"


def get_addresses(user):
    new_address = requests.get(f"http://127.0.0.1:8002/address/all?user_id={user.id}")
    addresses_dic = json.loads(new_address.text)
    values = addresses_dic[str(user.id)]

    for address in values:
        address_value = address["address_name"]
        if address_value not in user.addresses_list:
            user.addresses_list.append(address_value)


def get_transactions(user, transactions_count):
    user_transactions_response = requests.get(f"http://127.0.0.1:8003/transactions/all/?user_id={user.id}&transactions_count={transactions_count}").text
    user_transactions_list = json.loads(user_transactions_response)
    user.transactions_list = user_transactions_list


if __name__ == '__main__':
    uvicorn.run("main:app", host="127.0.0.1", port=8001)
