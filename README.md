Rock Paper Scissor Game

-----
Run on docker:

./mvnw clean package  
docker build -t rock.paper.scissors .
docker run -p 8080:8080 rock.paper.scissors

----
To run tests:

./mvnw test

----

EXAMPLE CALLS:

POST
http://localhost:8080/game/start

POST
http://localhost:8080/game/end

POST
http://localhost:8080/game/play?userMove=scissors

GET
http://localhost:8080/game/all

GET
http://localhost:8080/game/stats