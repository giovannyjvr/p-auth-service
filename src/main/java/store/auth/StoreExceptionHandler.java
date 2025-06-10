package store.auth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class StoreExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(StoreExceptionHandler.class);

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, String>> handleFeignException(FeignException e) {
        // 1. Obtém o código HTTP que veio no FeignException
        HttpStatus code = HttpStatus.valueOf(e.status());

        // 2. Extrai a mensagem/bruta do erro (pode ser JSON ou texto simples)
        String raw = e.getMessage();

        // 3. Se você quiser, pode tentar puxar um "title" de dentro de um JSON
        //    Por simplicidade, aqui usamos raw tanto como título quanto como detalhe:
        String title = raw;
        String detail = raw;

        // 4. Registra em DEBUG para fins de auditoria
        logger.debug("handle FeignException - detail: " + detail);
        logger.debug("handle FeignException - title: " + title);

        // 5. Monta um corpo genérico de erro em forma de Map<"campo","valor">
        Map<String, String> body = new HashMap<>();
        body.put("status", String.valueOf(code.value()));
        body.put("error", title);
        body.put("details", detail);

        // 6. Retorna ResponseEntity com status original e corpo JSON (=Map)
        return ResponseEntity.status(code).body(body);
    }

    // Se você tiver outros @ExceptionHandler, adicione-os abaixo seguindo
    // o mesmo padrão (criando um Map de saída ou sua DTO de erro).
}
