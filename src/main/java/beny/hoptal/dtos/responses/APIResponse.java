package beny.hoptal.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<Object> {
    private String message;
    private String status;
    private Object data;

    public APIResponse(boolean bool, Object data) {
    }
}
