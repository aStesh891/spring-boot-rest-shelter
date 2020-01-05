package ua.stesh.springbootrestshelter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ApiError apiError = new ApiError(status, "JSON request is malformed", ex);
        return new ResponseEntity(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        ApiError apiError = new ApiError(status, "Arguments of method are not valid", ex);
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());

        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        return new ResponseEntity<>(new ApiError(status, "No handler found exception", ex), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundEx(EntityNotFoundException ex,
                                                            WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Animal entity not found", ex);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAnimalTypeExc.class)
    protected ResponseEntity<Object> handleEntityNotFoundEx(InvalidAnimalTypeExc ex,
                                                            WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Animal type is incorrect", ex);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(String.format("Argument type mismatch exception! The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName()));
        apiError.setDebugMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex,
                                                         WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Undefined exception", ex);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}