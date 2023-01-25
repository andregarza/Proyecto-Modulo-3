package org.AndrePerezGarza.API.Project.delivery.Controller.Handlers;

import org.AndrePerezGarza.API.Project.delivery.Entity.Builders.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.TreeMap;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new TreeMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrores(errors);
        errorResponse.setRuta(request.getDescription(false).substring(4));
        return handleExceptionInternal(
                ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new TreeMap<>();

        StringBuilder builder = new StringBuilder();
        builder.append("The method ");
        builder.append(ex.getMethod());
        builder.append(" is not supported for this petition. The supported method are ");

        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        errors.put("Error", builder.toString());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrores(errors);
        errorResponse.setRuta(request.getDescription(false).substring(4));

        return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new TreeMap<>();

        StringBuilder builder = new StringBuilder();
        builder.append(" The path variable is missing ");
        errors.put("Error", builder.toString());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrores(errors);
        errorResponse.setRuta(request.getDescription(false).substring(4));

        return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }


}
