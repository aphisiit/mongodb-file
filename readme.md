# Spring-Boot | Kotlin | File
Run project
-
`$ gradle bootRun`

MongoBD name
-
springboot-mongo (username and password is not defined)

Run Mongo in Docker
-
`$ docker run --name file-mongo -p 20717:20717 -d mongo:latest`

Run Application With MongoDB on Docker
-
1. Build your project with command  
`$ gradle package`
2. Build your docker file  
`$ docker build -t aphisiit/kotlin-mongo-pdf -f DockerFile .`
3. Run docker compose  
`$ docker-compose up`
4. Open link http://localhost:8080/pdf/