# Redis Docker Spring Boot Demo (Java 21)

This is a simple demonstration project that launches a Redis server using Docker and connects it to a Spring Boot application to store and retrieve key-value data.

---

## Tech Stack

| Item             | Description                         |
|------------------|-------------------------------------|
| Java             | 21                                  |
| Spring Boot      | 3.5.3                               |
| Redis            | Latest stable (Docker image)        |
| Library          | `spring-boot-starter-data-redis`    |
| Environment      | Docker                              |

---

## What This Project Does

This project demonstrates how to:

- Launch a Redis server using Docker.
- Connect a Spring Boot application to Redis.
- Store and retrieve key-value data using REST API endpoints.

---

## Sample Code

### `RedisController.java`

```java
@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/set")
    public String set(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
        return "Saved!";
    }

    @GetMapping("/get")
    public String get(@RequestParam String key) {
        return redisService.get(key);
    }
}
```

### `RedisService.java`

```java
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void save(String key, String value) {
        System.out.println("Saving key: " + key + ", value: " + value);
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        String value = redisTemplate.opsForValue().get(key);
        System.out.println("Retrieving key: " + key + ", value: " + value);
        return value;
    }
}
```

### Spring Boot Configuration

Create `src/main/resources/application.properties` and add:

```properties
spring.application.name=redis-docker-springboot-demo
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

And add the dependency:
```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
```

---

## How to Start Redis
Use the official Redis image from Docker Hub to launch the Redis server.
```bash
docker run --name redis-docker-springboot-demo -p 6379:6379 -d redis
docker ps  # Confirm the container is running
```
Reference: https://hub.docker.com/_/redis

---

## How to Run Spring Boot
```bash
./gradlew bootRun
```

---

## How to Test
Use curl or browser:
```bash
curl "http://localhost:8080/set?key=name&value=devtkms"
```
Response:
```
saved!
```

```bash
curl "http://localhost:8080/get?key=name"
```
Response:
```
devtkms
```

Console Logs:
```
Saving key: name, value: devtkms
Retrieving key: name, value: devtkms
```

---