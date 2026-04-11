package beny.hoptal.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class APIResponse<Object> {
    private String message;
    private boolean status;
    private Object data;

    public APIResponse(String message, Object data) {
        this.message = message;
        this.data = data;
        this.status= data!=null;


    }
}
