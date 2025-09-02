package desafio.transacao_api.infrastructure.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
   @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<String> handleUnprocessableEntity(UnprocessableEntity e) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body("Erro de validação: " + e.getMessage());
    }

    
}