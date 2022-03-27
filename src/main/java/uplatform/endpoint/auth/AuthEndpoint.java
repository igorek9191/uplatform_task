package uplatform.endpoint.auth;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import uplatform.endpoint.RequestSpecHandler;
import uplatform.model.auth.AuthModel;
import uplatform.property_values.BasicPropertyValues;
import uplatform.property_values.AuthEndpointValues;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class AuthEndpoint {

    @Lazy
    @Autowired
    private RequestSpecHandler requestSpecHandler;

    @Autowired
    private BasicPropertyValues basicPropertyValues;

    @Autowired
    private AuthEndpointValues authEndpointValues;

    @Step("Making 'post' request for 'auth' endpoint")
    public String makeAuth() {
        Response response = given()
                .spec(requestSpecHandler.getSpec())
                .when()
                .body(AuthModel.builder()
                        .username(basicPropertyValues.getLogin())
                        .password(basicPropertyValues.getPassword())
                        .build())
                .post(authEndpointValues.getAuth())
                .thenReturn();
        assertEquals(200, response.getStatusCode(), "Check of status code in response of 'post' method");
        assert response.getBody() != null;
        return response.jsonPath().getString("jwt");
    }
}
