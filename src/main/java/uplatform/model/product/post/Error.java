package uplatform.model.product.post;

import lombok.Data;

import java.util.List;

@Data
public class Error {
    private String field;
    private List<String> messages;
}
