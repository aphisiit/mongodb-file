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
- Build your project with command  
`$ gradle package`
- Build your docker images (Make it sure in spring.profile.active=docker in application.yaml file)  
`$ docker build -t aphisiit/kotlin-mongo-pdf -f DockerFile .`
- Run docker-compose  
`$ docker-compose up`
- Url Default  
http://localhost:8080/pdf/