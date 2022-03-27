package uplatform.property_values;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "endpoint.product")
public class ProductEndpointValues {
    private String post;
    private String patch;
    private String get;
    private String delete;
}
