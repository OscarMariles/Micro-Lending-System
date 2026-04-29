package com.microlending.micro_lending_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Mensaje errores de "No encontrado" (404)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex, WebRequest request){
       ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage(),request.getDescription(false));

       return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    // Maneja errores de "Ya existe" o conflictos (409)
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InstallmentAlreadyPaid.class)
    public ResponseEntity<ErrorMessage> installmentAlreadyPaidException(InstallmentAlreadyPaid ex,WebRequest request){
        ErrorMessage message=new ErrorMessage(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(message,HttpStatus.CONFLICT);
    }

    // Maneja transiciones de estado inválidas (409)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorMessage> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    // Maneja errores de validación de @Valid (400) — devuelve cada campo con su mensaje
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        body.put("description", request.getDescription(false));

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        body.put("errors", fieldErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    // Atrapalotodo: Errores inesperados (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocurrió un error inesperado en el servidor",
                ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
