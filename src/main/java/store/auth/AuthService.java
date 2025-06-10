package store.auth;

import store.auth.Register;
import store.auth.SolveOut;
import store.auth.JwtService;
import store.account.AccountIn;
import store.account.AccountOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    /** 
     * Registra um novo usuário no account-service e retorna o JWT gerado.
     */
    public String register(Register register) {
        // Monta DTO para o account-service
        AccountIn accountIn = AccountIn.builder()
            .email(register.email())
            .name(register.name())
            .password(register.password())
            .build();

        // Chama account-service via HTTP POST /accounts
        ResponseEntity<AccountOut> response = restTemplate.postForEntity(
            "http://account-service:8084/accounts",
            accountIn,
            AccountOut.class
        );
        AccountOut accountOut = response.getBody();

        // Gera JWT usando o ID retornado
        return generateToken(accountOut.getId());
    }

    /**
     * Faz login no account-service e retorna o JWT.
     */
    public String login(String email, String password) {
        AccountIn accountIn = AccountIn.builder()
            .email(email)
            .password(password)
            .build();

        // Chama account-service via HTTP POST /accounts/login
        ResponseEntity<AccountOut> response = restTemplate.postForEntity(
            "http://account-service:8084/accounts/login",
            accountIn,
            AccountOut.class
        );
        AccountOut accountOut = response.getBody();

        return generateToken(accountOut.getId());
    }

    // Gera um token que expira em 24h
    private String generateToken(String id) {
        Date now = new Date();
        Date expires = new Date(now.getTime() + 1000L * 60 * 60 * 24);
        return jwtService.create(id, now, expires);
    }

    /**
     * Valida o token JWT e retorna um DTO com o ID da conta.
     */
    public SolveOut solve(String token) {
        String idAccount = jwtService.getId(token);
        return SolveOut.builder()
            .idAccount(idAccount)
            .build();
    }
}
