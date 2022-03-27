package uplatform.model.product.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
public class ErrorProductResponse {
    private int code;
    private String reason;
    private List<Error> errors;

    @SneakyThrows
    @JsonIgnore
    public List<String> findNameErrors(){
        return this.getErrors().stream().filter(error -> error.getField().equals("name")).findFirst()
                .orElseThrow(() -> new Exception("Can't find field 'name' in error response")).getMessages();
    }

    @SneakyThrows
    @JsonIgnore
    public List<String> findDescriptionErrors(){
        return this.getErrors().stream().filter(error -> error.getField().equals("description")).findFirst()
                .orElseThrow(() -> new Exception("Can't find field 'description' in error response")).getMessages();
    }

    @SneakyThrows
    @JsonIgnore
    public List<String> findPriceErrors(){
        return this.getErrors().stream().filter(error -> error.getField().equals("price")).findFirst()
                .orElseThrow(() -> new Exception("Can't find field 'price' in error response")).getMessages();
    }
}
