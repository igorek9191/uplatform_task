package smoke;

import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import uplatform.endpoint.product.ProductEndpoint;
import uplatform.model.product.delete.DeleteErrorResponse;
import uplatform.model.product.get.GetErrorResponse;
import uplatform.model.product.post.ErrorProductResponse;
import uplatform.model.product.post.ProductRequest;
import uplatform.model.product.post.ProductResponse;

@TestMethodOrder(MethodOrderer.MethodName.class)
@Feature("Negative tests of CRUD operations for 'product' endpoint")
public class NegativeProductTest extends BaseTest {

    @Lazy
    @Autowired
    ProductEndpoint productEndpoint;

    private String postName = "";
    private String postDescription = "";
    private Double postPrice = 0.00;

    private String patchName = "";
    private String patchDescription = "";
    private Double patchPrice = 0.00;

    private ProductRequest postProductBody;
    private ProductRequest patchProductBody;

    private Integer id;
    private Integer notExistingId = 321;

    @BeforeAll
    public void prepareBodies() {
        ProductRequest correctPostProductBody = ProductRequest.builder()
                .name("Some name")
                .description("Some description")
                .price(10.00)
                .build();
        ProductResponse correctProduct = productEndpoint.postProduct(correctPostProductBody);
        id = correctProduct.getId();

        postProductBody = ProductRequest.builder()
                .name(postName)
                .description(postDescription)
                .price(postPrice)
                .build();

        patchProductBody = ProductRequest.builder()
                .name(patchName)
                .description(patchDescription)
                .price(patchPrice)
                .build();
    }

    @DisplayName("Check of 'post' method for product")
    @Test
    public void test1() {
        SoftAssertions softAssertions = new SoftAssertions();

        Response response = productEndpoint.postProductError(postProductBody);
        ErrorProductResponse errorProductResponse = response.as(ErrorProductResponse.class);

        softAssertions.assertThat(response.getStatusCode())
                .as("Check of status code in response of 'post' method")
                .isEqualTo(400);
        softAssertions.assertThat(errorProductResponse.getCode())
                .as("Check of code from body of 'post' method")
                .isEqualTo(400);
        softAssertions.assertThat(errorProductResponse.findNameErrors())
                .as("Check of error message about 'name' parameter in 'post' method")
                .contains("Length must be between 3 and 20 characters.");
        softAssertions.assertThat(errorProductResponse.findDescriptionErrors())
                .as("Check of error message about 'description' parameter in 'post' method")
                .contains("Length must be between 3 and 20 characters.");
        softAssertions.assertThat(errorProductResponse.findPriceErrors())
                .as("Check of error message about 'price' parameter in 'post' method")
                .contains("Value must be positive");
        softAssertions.assertAll();
    }

    @DisplayName("Check of 'patch' method for product")
    @Test
    public void test2() {
        SoftAssertions softAssertions = new SoftAssertions();

        Response response = productEndpoint.patchProductError(id, patchProductBody);
        ErrorProductResponse errorProductResponse = response.as(ErrorProductResponse.class);

        softAssertions.assertThat(response.getStatusCode())
                .as("Check of status code in response of 'patch' method")
                .isEqualTo(400);
        softAssertions.assertThat(errorProductResponse.getCode())
                .as("Check of code from body of 'patch' method")
                .isEqualTo(400);
        softAssertions.assertThat(errorProductResponse.findNameErrors())
                .as("Check of error message about 'name' parameter in 'patch' method")
                .contains("Length must be between 3 and 20 characters.");
        softAssertions.assertThat(errorProductResponse.findDescriptionErrors())
                .as("Check of error message about 'description' parameter in 'patch' method")
                .contains("Length must be between 3 and 20 characters.");
        softAssertions.assertThat(errorProductResponse.findPriceErrors())
                .as("Check of error message about 'price' parameter in 'patch' method")
                .contains("Value must be positive");
        softAssertions.assertAll();
    }

    @DisplayName("Check of 'get' method for product")
    @Test
    public void test3() {
        SoftAssertions softAssertions = new SoftAssertions();

        Response response = productEndpoint.getProductError(notExistingId);
        GetErrorResponse getErrorResponse = response.as(GetErrorResponse.class);

        softAssertions.assertThat(response.getStatusCode())
                .as("Check of status code in response of 'get' method")
                .isEqualTo(404);
        softAssertions.assertThat(getErrorResponse.getCode())
                .as("Check of code from body of 'get' method")
                .isEqualTo(404);
        softAssertions.assertThat(getErrorResponse.getReason())
                .as("Check of code in response body of 'get' method")
                .isEqualTo("Not Found");
        softAssertions.assertThat(getErrorResponse.getMessage())
                .as("Check of code in response body of 'get' method")
                .isEqualTo("Product with id="  +notExistingId+" was not found.");
        softAssertions.assertAll();
    }

    @DisplayName("Check of 'delete' method for product")
    @Test
    public void test4() {
        SoftAssertions softAssertions = new SoftAssertions();

        Response response = productEndpoint.deleteProduct(notExistingId);
        DeleteErrorResponse deleteErrorResponse = response.as(DeleteErrorResponse.class);

        softAssertions.assertThat(response.getStatusCode())
                .as("Check of status code in response of 'delete' method")
                .isEqualTo(404);
        softAssertions.assertThat(deleteErrorResponse.getCode())
                .as("Check of code from body of 'delete' method")
                .isEqualTo(404);
        softAssertions.assertThat(deleteErrorResponse.getReason())
                .as("Check of code in response body of 'delete' method")
                .isEqualTo("Not Found");
        softAssertions.assertThat(deleteErrorResponse.getMessage())
                .as("Check of code in response body of 'delete' method")
                .isEqualTo("Product with id="+notExistingId+" was not found.");
        softAssertions.assertAll();
    }
}
