package store.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/register")
    ResponseEntity<TokenOut> register(@RequestBody RegisterIn registerIn);

    @PostMapping("/login")
    ResponseEntity<TokenOut> login(@RequestBody LoginIn loginIn);

    @PostMapping("/solve")
    ResponseEntity<SolveOut> solve(@RequestBody TokenOut token);
}
