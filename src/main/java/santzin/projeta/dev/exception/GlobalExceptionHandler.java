package santzin.projeta.dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import santzin.projeta.dev.DTOs.exception.ResponseExceptionDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FailedLoginException.class)
    public ResponseEntity<ResponseExceptionDTO> handleFailedLoginException(FailedLoginException ex) {
        ResponseExceptionDTO response = new ResponseExceptionDTO(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage()
        );
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(FailedRegisterException.class)
    public ResponseEntity<ResponseExceptionDTO> handleFailedRegisterException(FailedRegisterException ex){
        ResponseExceptionDTO response = new ResponseExceptionDTO(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ResponseExceptionDTO> handlerItemNotFoundException(ItemNotFoundException ex){
        ResponseExceptionDTO response = new ResponseExceptionDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NotPermitException.class)
    public ResponseEntity<ResponseExceptionDTO> handleNotPermitException(NotPermitException ex){
        ResponseExceptionDTO response = new ResponseExceptionDTO(
                HttpStatus.FORBIDDEN,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(ItemWithValueAlreadyExistsException.class)
    public ResponseEntity<ResponseExceptionDTO> handleItemWithValueAlreadyExistsException(ItemWithValueAlreadyExistsException ex){
        ResponseExceptionDTO response = new ResponseExceptionDTO(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}
