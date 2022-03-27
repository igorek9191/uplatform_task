package uplatform.model.product.get;

import lombok.Data;

@Data
public class GetErrorResponse {
    private Integer code;
    private String reason;
    private String message;
}
