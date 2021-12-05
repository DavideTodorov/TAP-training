## Address Service

### Contents

* [Create address](#create-address)
* [Get addresses](#get-addresses)
* [Delete addresses](#delete-addresses)

###Overview
> Service that handles creation and modification of user's addresses.

### Create address

```
@app.post("/address/create")
def create_address(user_id: int):
```

> This method handles post requests for creating addresses. Its purpose is to provide address for specific user. It also
> stores the addresses in a dictionary.

### Get addresses

```
@app.get("/address/all")
def get_all_addresses(user_id: int):
```

> This method handles get requests for getting addresses for specific user. It finds them in a dictionary by user_id and returns
> a list of addresses

### Delete addresses

```
@app.post("/address/delete")
def delete_addresses(user_id: int):
```

> This method handles post requests for deleting addresses for specific user. It finds them in a dictionary by user_id and removes
> them. Returns string if the operation is successful.