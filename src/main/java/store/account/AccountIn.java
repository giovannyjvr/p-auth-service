package store.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountIn {
    private String id;       // null ao fazer login
    private String name;     // usado somente em register
    private String email;
    private String password;
}
