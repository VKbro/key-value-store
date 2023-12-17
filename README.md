# KeyValueStore

This project is a simple key-value store implemented in Java using Spring Boot. It provides a RESTful API to interact with the key-value data.

Persistence is provided using a file based database (inbuilt H2 Database).

## Table of Contents
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Samples](#samples)

## Getting Started

To get a local copy up and running, follow these steps:

1. Clone the repo
```bash
git clone https://github.com/VKbro/key-value-store.git
```
2. Install dependencies
```bash
./mvnw clean install
```
3. Run the application
```bash
./mvnw spring-boot:run
```

## Usage

The application exposes several endpoints to interact with the key-value data:

- `GET /`: Fetch all key-value data.
- `GET /{userID}`: Fetch all key-value data for a specific user.
- `GET /{user}/{key}`: Fetch all key-value data for a specific user and key.
- `GET /filter?userID={userID}&starting={startingTime}&ending={endingTime}`: Fetch all key-value data for a specific user within a time range.
- `PUT /update`: Update a key-value pair.
- `POST /`: Insert a new key-value pair.

All times are in the format `yyyy-MM-dd'T'HH:mm:ss`.

By default applications runs in port 8080.

## Samples

Below are some examples of how to use the API endpoints:

### Fetch All Key-Value Data

```bash
curl -X GET 'http://localhost:8080'
```

### Fetch Key-Value Data by User ID

```bash
curl -X GET 'http://localhost:8080/{userID}'
```

Replace `{userID}` with the actual user ID.

### Fetch Key-Value Data by User and Key

```bash
curl -X GET 'http://localhost:8080/{user}/{key}'
```

Replace `{user}` with the actual user ID and `{key}` with the actual key.

### Fetch Key-Value Data by Time Range

```bash
curl -X GET 'http://localhost:8080/filter?userID={userID}&starting={startingTime}&ending={endingTime}'
```

Replace `{userID}` with the actual user ID, `{startingTime}` and `{endingTime}` with the actual time range in the format `yyyy-MM-dd'T'HH:mm:ss`.

### Update a Key-Value Pair

```bash
curl -X PUT 'http://localhost:8080/update' -H 'Content-Type: application/json' -d '{ "id": 1, "userID": "user1", "keyValueData": { "key1": "value1" } }'
```

Replace the JSON body with the actual data.

### Insert a New Key-Value Pair

```bash
curl -X POST 'http://localhost:8080' -H 'Content-Type: application/json' -d '{ "userID": "user1", "keyValueData": { "key1": "value1" } }'
```

Replace the JSON body with the actual data.
