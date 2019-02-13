# playlist-by-weather

A micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist suggestion according to the current temperature.

## Run Application ##

```
    sudo docker-compose up
```

docker-compose version: 1.17.1

## Call API ##

* Make login on API:
 
``` 
curl -d '{"username":"admin", "password":"password"}' -H "Content-Type: application/json" -X POST http://localhost:8080/login 
```
* Get the token response and put on headher request like this: 

```
curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ..." -X GET http://localhost:8080/playlist?city=niteroi
```

## Documentation #
``` 
http://localhost:8080/swagger-ui.html
```


## Technologies ##

* Spring Boot
* Redis
* Docker
