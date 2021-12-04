import json
import random

import uvicorn as uvicorn
from fastapi import FastAPI
import requests


app = FastAPI()

all_users_transactions = {}


@app.post("/transaction/create")
def create_transaction(user_id: int):
    new_transaction = requests.get("http://127.0.0.1:8000/info/generate/id").text
    new_transaction = json.loads(new_transaction)

    if user_id not in all_users_transactions:
        all_users_transactions[user_id] = []

    all_users_transactions[user_id].append(new_transaction)
    return new_transaction


@app.get("/transactions/all")
def get_all_transactions(user_id: int):
    return all_users_transactions[user_id]


if __name__ == '__main__':
    uvicorn.run("main:app", host="127.0.0.1", port=8003)
