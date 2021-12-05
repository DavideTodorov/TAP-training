## Transaction Service

### Contents

* [Create transaction](#create-transaction)
* [Get transactions](#get-transactions)
* [Delete transactions](#delete-transactions)

### Overview

> Service that handles creation and modification of user's transactions. It communicates with third party API for the creation
> of the transactions.

### Create transaction

```
@app.post("/transactions/create")
def create_transaction(user_id: int):
```

> This method handles post requests for creating a transaction. This method gets data from third party API. If the third party
> API returns exception we will retry to get valid data ten times. If the third party API still does not provide correct data
> the method will return message for the failure of the API.

### Get transactions

```
@app.get("/transactions/all")
def get_transactions(user_id: int, transactions_count: int):
```

> This method will handle get requests for retrieving a specific amount of transactions. The method will check if there are any
> present transactions for the user with 'user_id'. If the user does not have any transactions it will return an empty list.
> If the user has transactions the method will sort them in ascending order(the newest first) and return the desired count. If the user has
> fewer transactions than the desired count, the method will return all present transactions.

### Delete transactions

```
@app.post("/transactions/delete")
def delete_all_transactions(user_id: int):
```

> This method handles post requests for deleting transactions for specific user. The method will check if there are any
> present transactions for the user with 'user_id'. If the user does not have any transactions it will return a message
> that states "No transactions". If the user has transactions they will be removed from the dictionary and the method
> will return a message stating "Transactions were deleted".