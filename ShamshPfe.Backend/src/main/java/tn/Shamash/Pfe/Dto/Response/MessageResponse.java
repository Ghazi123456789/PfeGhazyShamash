package tn.Shamash.Pfe.Dto.Response;

import lombok.Data;
import lombok.NonNull;

@Data
public class MessageResponse {
    @NonNull
    private String message;
}
