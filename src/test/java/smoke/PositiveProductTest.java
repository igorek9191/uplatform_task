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
import uplatform.model.product.post.ProductRequest;
import uplatform.model.product.post.ProductResponse;

@TestMethodOrder(MethodOrderer.MethodName.class)
@Feature("Positive tests of CRUD operations for 'product' endpoint")
public class PositiveProductTest extends BaseTest {

    @Lazy
    @Autowired
    ProductEndpoint productEndpoint;

    private String postName = "Some name";
    private String postDescription = "Some description";
    private Double postPrice = 10.00;

    private String patchName = "Another name";
    private String patchDescription = "Another description";
    private Double patchPrice = 20.00;

    private ProductRequest postProductBody;
    private ProductRequest patchProductBody;

    private Integer id;

    @BeforeAll
    public void prepareBodies(){
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

        ProductResponse productResponse = productEndpoint.postProduct(postProductBody);

        softAssertions.assertThat(productResponse.getName()).as("Check of name after 'post' method")
                .isEqualTo(postName);
        softAssertions.assertThat(productResponse.getDescription()).as("Check of description after 'post' method")
                .isEqualTo(postDescription);
        softAssertions.assertThat(productResponse.getPrice()).as("Check of price after 'post' method")
                .isEqualTo(postPrice);
        softAssertions.assertAll();
        id = productResponse.getId();
    }

    @DisplayName("Check of 'patch' method for product")
    @Test
    public void test2() {
        SoftAssertions softAssertions = new SoftAssertions();

        ProductResponse productResponse = productEndpoint.patchProduct(id, patchProductBody);

        softAssertions.assertThat(productResponse.getName()).as("Check of name after 'patch' method")
                .isEqualTo(patchName);
        softAssertions.assertThat(productResponse.getDescription()).as("Check of description after 'patch' method")
                .isEqualTo(patchDescription);
        softAssertions.assertThat(productResponse.getPrice()).as("Check of price after 'patch' method")
                .isEqualTo(patchPrice);
        softAssertions.assertAll();
    }

    @DisplayName("Check of 'get' method for product")
    @Test
    public void test3() {
        SoftAssertions softAssertions = new SoftAssertions();

        ProductResponse productResponse = productEndpoint.getProduct(id);

        softAssertions.assertThat(productResponse.getName()).as("Check of name after 'get' method")
                .isEqualTo(patchName);
        softAssertions.assertThat(productResponse.getDescription()).as("Check of description after 'get' method")
                .isEqualTo(patchDescription);
        softAssertions.assertThat(productResponse.getPrice()).as("Check of price after 'get' method")
                .isEqualTo(patchPrice);
        softAssertions.assertAll();
    }

    @DisplayName("Check of 'delete' method for product")
    @Test
    public void test4() {
        SoftAssertions softAssertions = new SoftAssertions();

        Response response = productEndpoint.deleteProduct(id);

        softAssertions.assertThat(response.statusCode()).as("Check of status code after 'delete' method")
                .isEqualTo(204);
        softAssertions.assertAll();
    }
}
