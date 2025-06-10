package store.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountOut {
    private String id;
    private String name;
    private String email;
}
