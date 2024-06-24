

1. **Update User**
   - **Endpoint**: `PUT /users/update/{id}`
   - **Description**: Updates user information in the MyFinEx App.
   - **Parameters**: 
     - `id` (path, required): The unique identifier of the user.
   - **Request Body**: User object containing the updated details.
   - **Responses**: 
     - `200 OK`: User updated successfully.
     - `500 Internal Server Error`: An unexpected server error.

2. **Login User**
   - **Endpoint**: `POST /users/login`
   - **Description**: Logs in a user to the MyFinEx App.
   - **Request Body**: Login DTO containing `userName` and `password`.
   - **Responses**: 
     - `200 OK`: User logged in successfully.
     - `500 Internal Server Error`: An unexpected server error.

3. **Create User**
   - **Endpoint**: `POST /users/create`
   - **Description**: Creates a new user in the MyFinEx App.
   - **Request Body**: User object containing user details.
   - **Responses**: 
     - `201 Created`: User created successfully.
     - `500 Internal Server Error`: An unexpected server error.

4. **Test Endpoint**
   - **Endpoint**: `GET /users/test`
   - **Description**: A test endpoint returning a dummy property.
   - **Responses**: 
     - `200 OK`: Successful response with a string.

5. **Get User Details**
   - **Endpoint**: `GET /users/find/{id}`
   - **Description**: Retrieves details of a specific user in the MyFinEx App.
   - **Parameters**: 
     - `id` (path, required): The unique identifier of the user.
   - **Responses**: 
     - `200 OK`: User details retrieved successfully.
     - `500 Internal Server Error`: An unexpected server error.

6. **Get User Expenses**
   - **Endpoint**: `GET /users/expenses/{userId}`
   - **Description**: Retrieves a list of expenses for a specified user.
   - **Parameters**: 
     - `userId` (path, required): The unique identifier of the user.
   - **Responses**: 
     - `200 OK`: List of expenses retrieved successfully.
     - `500 Internal Server Error`: An unexpected server error.

7. **Get All Users**
   - **Endpoint**: `GET /users/all`
   - **Description**: Retrieves details of all users in the MyFinEx App.
   - **Responses**: 
     - `200 OK`: List of users retrieved successfully.
     - `500 Internal Server Error`: An unexpected server error.

8. **Delete User**
   - **Endpoint**: `DELETE /users/delete/{id}`
   - **Description**: Deletes a specific user from the MyFinEx App.
   - **Parameters**: 
     - `id` (path, required): The unique identifier of the user.
   - **Responses**: 
     - `204 No Content`: User deleted successfully.
     - `500 Internal Server Error`: An unexpected server error.


![UserMS](https://github.com/MentalCoder91/myfinex-user-service/assets/97496417/5e1f5a25-65d6-4db0-aaa5-80058276fe85)

![schemasUser](https://github.com/MentalCoder91/myfinex-user-service/assets/97496417/212f2b9b-e959-485e-96f2-af1d17bdd18c)




