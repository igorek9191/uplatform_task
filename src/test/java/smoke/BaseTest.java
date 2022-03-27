package smoke;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import uplatform.MyApplication;
import uplatform.endpoint.RequestSpecHandler;
import uplatform.endpoint.auth.AuthEndpoint;

@SpringBootTest(classes = MyApplication.class)
public class BaseTest {

    @Lazy
    @Autowired
    private RequestSpecHandler requestSpecHandler;

    @Lazy
    @Autowired
    private AuthEndpoint authEndpoint;

    private String token;

    @BeforeAll
    public void beforeSuite() {
        requestSpecHandler.initRequestSpecification();
        token = authEndpoint.makeAuth();
        requestSpecHandler.setToken(token);
    }
}