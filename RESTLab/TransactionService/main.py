import json
import random

import uvicorn as uvicorn
from fastapi import FastAPI, HTTPException
import requests

app = FastAPI()

all_users_transactions = {}


@app.post("/transactions/create")
def create_transaction(user_id: int):
    new_transaction = "error"

    for i in range(10):
        new_transaction = requests.get("http://127.0.0.1:8000/info/generate/id").text
        if not new_transaction.__contains__("connection failed"):
            break

    if new_transaction == "error":
        return "Third party API failure."

    new_transaction = json.loads(new_transaction)

    if user_id not in all_users_transactions:
        all_users_transactions[user_id] = []

    all_users_transactions[user_id].append(new_transaction)
    return new_transaction


@app.get("/transactions/all")
def get_transactions(user_id: int, transactions_count: int):
    if user_id not in all_users_transactions:
        return []

    all_users_transactions[user_id].sort(key=lambda e: e["dateOfExecution"])

    transactions_to_return = []

    counter = 0
    for transaction in all_users_transactions[user_id]:
        if counter == transactions_count:
            break
        transactions_to_return.append(transaction)
        counter += 1

    return transactions_to_return


@app.post("/transactions/delete")
def delete_all_transactions(user_id: int):
    if user_id not in all_users_transactions:
        return "No transactions"

    del all_users_transactions[user_id]
    return "Transactions were deleted"


if __name__ == '__main__':
    uvicorn.run("main:app", host="127.0.0.1", port=8003)
