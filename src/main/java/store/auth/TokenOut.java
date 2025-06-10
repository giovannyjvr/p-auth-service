package store.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenOut {
    private String token;
}
