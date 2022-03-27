package uplatform.endpoint.product;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import uplatform.endpoint.RequestSpecHandler;
import uplatform.model.product.post.ProductRequest;
import uplatform.model.product.post.ProductResponse;
import uplatform.property_values.ProductEndpointValues;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class ProductEndpoint {

    @Lazy
    @Autowired
    private RequestSpecHandler requestSpecHandler;

    @Autowired
    ProductEndpointValues endpointValues;

    @Step("Making 'post' request for 'product' endpoint")
    public ProductResponse postProduct(ProductRequest productRequest) {
        Response response = given()
                .spec(requestSpecHandler.getSpec())
                .when()
                .body(productRequest)
                .post(endpointValues.getPost())
                .thenReturn();
        assertEquals(200, response.getStatusCode(), "Check of status code in response of 'post' method");
        assert response.getBody() != null;
        return response.as(ProductResponse.class);
    }

    @Step("Making 'post' request for 'product' endpoint")
    public Response postProductError(ProductRequest productRequest) {
        Response response = given()
                .spec(requestSpecHandler.getSpec())
                .when()
                .body(productRequest)
                .post(endpointValues.getPost())
                .thenReturn();
        assert response.getBody() != null;
        return response;
    }

    @Step("Making 'patch' request for 'product' endpoint")
    public ProductResponse patchProduct(Integer id, ProductRequest patchProductRequest) {
        Response response = given()
                .spec(requestSpecHandler.getSpec())
                .when()
                .body(patchProductRequest)
                .patch(endpointValues.getPatch(), id)
                .thenReturn();
        assertEquals(200, response.getStatusCode(), "Check of status code in response of 'patch' method");
        assert response.getBody() != null;
        return response.as(ProductResponse.class);
    }

    @Step("Making 'patch' request for 'product' endpoint")
    public Response patchProductError(Integer id, ProductRequest patchProductRequest) {
        Response response = given()
                .spec(requestSpecHandler.getSpec())
                .when()
                .body(patchProductRequest)
                .patch(endpointValues.getPatch(), id)
                .thenReturn();
        assert response.getBody() != null;
        return response;
    }

    @Step("Making 'get' request for 'product' endpoint")
    public ProductResponse getProduct(Integer id) {
        Response response = given()
                .spec(requestSpecHandler.getSpec())
                .when()
                .get(endpointValues.getGet(), id)
                .thenReturn();
        assertEquals(200, response.getStatusCode(), "Check of status code in response of 'get' method");
        assert response.getBody() != null;
        return response.as(ProductResponse.class);
    }

    @Step("Making 'get' request for 'product' endpoint")
    public Response getProductError(Integer id) {
        Response response = given()
                .spec(requestSpecHandler.getSpec())
                .when()
                .get(endpointValues.getGet(), id)
                .thenReturn();
        assert response.getBody() != null;
        return response;
    }

    @Step("Making 'delete' request for 'product' endpoint")
    public Response deleteProduct(Integer id) {
        return given()
                .spec(requestSpecHandler.getSpec())
                .when()
                .delete(endpointValues.getDelete(), id)
                .thenReturn();
    }
}