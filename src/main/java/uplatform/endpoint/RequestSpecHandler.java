package uplatform.endpoint;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uplatform.property_values.BasicPropertyValues;

@Getter
@Component
public class RequestSpecHandler {

    @Autowired
    private BasicPropertyValues basicPropertyValues;

    private RequestSpecification spec;

    public void initRequestSpecification() {
        this.spec = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBaseUri(basicPropertyValues.getUri())
                .setPort(basicPropertyValues.getPort())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

    public void setToken(String token){
        spec.header("Authorization", "Bearer " + token);
    }
}
