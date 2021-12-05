## User Service

### Contents

* [Create user](#create-user)
* [Get user](#get-user)
* [Update user](#update-user)
* [Delete user](#delete-user)
* [Create transaction for user](#create-transaction-for-user)

### Overview

> Service that handles creation and modification of users and creation of transactions for a user.

### Create user

```
@app.get("/user/create")
def create_user(id: int, first_name: str, last_name: str):
```

> This method handles post requests for creating a user. If user with the specified ID does not exist the method will create a new
> user. It will provide an address for the new user from the Address Service. The method will return the user.

### Get user

```
@app.get("/user/get")
def get_user(id: int, transactions_count: int):
```

> This method handles get requests for retrieving a user. If the user is not present, the method will return a message stating
> "User does not exist!". If the user is present the method will get the desired number of transactions for the user from
> Transactions Service and the addresses related to the user from Address Service. The method will return the user with the
> transactions and addresses.

### Update user

```
@app.post("/user/update")
def update_user(id: int, new_first_name: str, new_last_name: str):
```

> This method will handle post requests for updating a user. If the user is not the present the method will return a message
> stating "User does not exist!". If the user is present a new first and last name will be set to it. The method will return
> the updated user.

### Delete user

```
@app.post("/user/delete")
def delete_user(user_id: int):
```

> This method handles post requests for deleting a user. If the user is not present the method will return a message stating
> "User does not exist!". If the user is present the method will invoke delete methods from Address Service and Transaction
> Service for the specific user. Finally, it will remove the user and return a message stating "User with id {user_id} was deleted."

### Create transaction for user

```
@app.post("/user/transactions/create")
def create_transaction_for_user(user_id: int):
```

> This method will handle post requests for creating a transaction for a specific user. If the user is not present the method will return a message stating
> "User does not exist!". If the user is present the method will invoke the Transaction Service for the creation of a new transaction
> for the specific user. The method will return a message stating "New transaction: {transaction_response}".