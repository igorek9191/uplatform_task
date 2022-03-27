package uplatform.model.product.delete;

import lombok.Data;

@Data
public class DeleteErrorResponse {
    private Integer code;
    private String reason;
    private String message;
}
