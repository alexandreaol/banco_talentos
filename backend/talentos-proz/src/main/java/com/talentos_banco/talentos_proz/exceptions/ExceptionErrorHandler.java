package com.talentos_banco.talentos_proz.exceptions;

import com.talentos_banco.talentos_proz.errors.NaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionErrorHandler {

    @ExceptionHandler(NaoEncontrado.class)
    public ResponseEntity<ErrorException> errorResponse(NaoEncontrado e, HttpServletRequest request) {
        ErrorException error = new ErrorException();

        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Recurso não encontrado");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }


}
