### About test automation task:
Need to create tests for CRUD operations of '/product' endpoint

### Required Tools
Java 14<br />
Gradle 7.1<br />
allure 2.15.0<br />
docker 20.10.8

### Start of application
In the root of project put in command prompt:<br />
```docker-compose up -d```<br />
Then wait for some time until Swagger would be available<br />
```http://localhost/docs/swagger-ui/index.html#/```

### Launch of tests
In the root of project put in command prompt:<br />
```clean test --tests '*smoke*'```<br />
OR<br />
```./gradlew clean test --tests '*smoke*'``` (if you work at Mac)

### Test report
In the root of project put in command prompt:<br />
```allure serve build/allure-results/```  <br />
Then open ```Behaviors``` tab and you will see the result. <br />
To stop the application put in command prompt:<br />
```docker-compose down``` 