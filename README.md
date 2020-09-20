#PAPER, ROCK, SCISSOR API BACKEND
##Configuration
###Specification
this project was made with the following technologies <br>
- Java 8
- Spring boot
   - Spring Web
   - Spring Security
- JWT
- Java Validations
- Lombok
- MapStruct
- Swagger
- Junit & Mockito

### How to Run
like a maven project in spring boot  we want run our Jar Project with the following command <br/>
`mvnw spring-boot:run` <br />

## User's manual
To user all the round's endpoints we need a user and login, the login endpoint gives you a JWT to use on each endpoint on the authorization header with the following format <br />
`bearer token` <br/>
we user the following Steps: <br>

1.**Create User**: we create a valid user (_not empty or null values in username in password_)<br>
**Endpoint**: POST /user/signup <br>
Request Body
```json
{
  "userName": "test",
  "password": "test"
}
```
The Response will be Http Code 201 (_Created_) <br/> and the user will be created in userList's bean
if username already exist will return a Http Code 400 (_Bad Request_) <br/> and a json with the description error<br/>
 
2.**Login User**: we sign in in the application, we chack that username exist in usrList's Bean and the password it´s correct <br/>
**Endpoint**: GET /user/login <br>
Request Body
```json
{
  "userName": "test",
  "password": "test"
}
```
The Response will be Http Code 200 (_OK_) <br/> and well get the JWT in Authorization headers Response
if username or password it's incorrect  will return a Http Code 403 (_Forbidden_) <br/><br/>

On this point, we could use any Round's endpoind:<br/>
* **Play Round**: User Logged play a Round, we should attache in request Body the moves of players1 and Player2, and return the result Game<br />
**Endpoint**: POST /round/play <br>
Request Header: 'Authorization: Bearer _JWT value_'<br>
Request Body
```json
{
    "player1Move": "PAPER", // values [ROCK|PAPER|SCISSOR]
    "player2Move": "ROCK"   // values [ROCK|PAPER|SCISSOR]
}
```
So We Return the following Response<br>
Response Status: 201 Created <br>
Response Body
```json
{
    "player1Move": "PAPER",
    "player2Move": "ROCK",
    "result": "PLAYER1_WINS" // values [ PLAYER1_WINS | PLAYER2_WINS | DRAW ] 
}
```

* **Get user sumary:**: get user summary, we pass the user logged's JWT across Request Header Authorization, and we obtain the user visible sumary used in Screen one<br>
 **Endpoint**: GET /round/sumary  <br>
 Request Header: 'Authorization: Bearer _JWT value_'<br><br>
 Response Status: 200 OK <br>
 Response Body:
 ```json
 {
    "totalRounds": 2, // Total Visible user's rounds
    "rounds": [       // visible user round's list order desc by created date 
        {
            "player1Move": "ROCK",
            "player2Move": "PAPER",
            "resultRound": "PLAYER2_WINS"
        },
        {
            "player1Move": "ROCK",
            "player2Move": "ROCK",
            "resultRound": "DRAW"
        }
    ]
 }
 ```
* **Reset user sumary:**: Update user summary change all round visible to invisible<br>
 **Endpoint**: PUT /round/reset  <br>
 Request Header: 'Authorization: Bearer _JWT value_'<br><br>
 Response Status: 200 OK <br>
 
* **Get general sumary:**: Get a general sumary used in screen 2, with All rounds played, All rounds won by Player 1, All Rounds won by player2 and draw rounds <br>
 **Endpoint**: GET /round/sumary/general  <br>
 Request Header: 'Authorization: Bearer _JWT value_'<br><br>
 Response Status: 200 OK <br>
 Response Body:
 ```json
 {
    "totalRounds": {   // Sumary Total Rounds
        "totalRounds": 5, // Total Rounds played
        "totalResultsRounds": [ // total round's List
            {
                "player1Move": "SCISSOR",
                "player2Move": "ROCK",
                "resultRound": "PLAYER2_WINS",
                "userName": "test",
                "createdAt": "20/09/20 22:06"
            },
            {
                "player1Move": "PAPER",
                "player2Move": "ROCK",
                "resultRound": "PLAYER1_WINS",
                "userName": "test",
                "createdAt": "20/09/20 22:06"
            },
            {
                "player1Move": "ROCK",
                "player2Move": "PAPER",
                "resultRound": "PLAYER2_WINS",
                "userName": "test",
                "createdAt": "20/09/20 22:01"
            },
            {
                "player1Move": "ROCK",
                "player2Move": "ROCK",
                "resultRound": "DRAW",
                "userName": "test",
                "createdAt": "20/09/20 22:01"
            },
            {
                "player1Move": "PAPER",
                "player2Move": "ROCK",
                "resultRound": "PLAYER1_WINS",
                "userName": "test",
                "createdAt": "20/09/20 20:00"
            }
        ]
    },
    "totalRoundsWinsPlayer1": { // Sumary Total Rounds where player1 won
        "totalRounds": 2,
        "totalResultsRounds": [
            {
                "player1Move": "PAPER",
                "player2Move": "ROCK",
                "resultRound": "PLAYER1_WINS",
                "userName": "test",
                "createdAt": "20/09/20 22:06"
            },
            {
                "player1Move": "PAPER",
                "player2Move": "ROCK",
                "resultRound": "PLAYER1_WINS",
                "userName": "test",
                "createdAt": "20/09/20 20:00"
            }
        ]
    },
    "totalRoundsWinsPlayer2": { // Sumary Total Rounds where player2 won
        "totalRounds": 2,
        "totalResultsRounds": [
            {
                "player1Move": "SCISSOR",
                "player2Move": "ROCK",
                "resultRound": "PLAYER2_WINS",
                "userName": "test",
                "createdAt": "20/09/20 22:06"
            },
            {
                "player1Move": "ROCK",
                "player2Move": "PAPER",
                "resultRound": "PLAYER2_WINS",
                "userName": "test",
                "createdAt": "20/09/20 22:01"
            }
        ]
    },
    "totalRoundsDraw": { // Sumary Total Rounds where result was draw
        "totalRounds": 1,
        "totalResultsRounds": [
            {
                "player1Move": "ROCK",
                "player2Move": "ROCK",
                "resultRound": "DRAW",
                "userName": "test",
                "createdAt": "20/09/20 22:01"
            }
        ]
    }
 }
 ```
 ## Test Api
 there are 2 ways to prove the Api
 
 1.**PostMan** : Find attach in project's root the file '_Paper Rock Scissor.postman_collection.json_' and import the collection
 
 2.**Swagger** : Swagger it's include in the api across thw URL [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

   