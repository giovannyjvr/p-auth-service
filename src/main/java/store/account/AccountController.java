package store.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountController {
    @PostMapping("/accounts")
    ResponseEntity<AccountOut> create(@RequestBody AccountIn in);

    @PostMapping("/accounts/login")
    ResponseEntity<AccountOut> login(@RequestBody AccountIn in);
}
