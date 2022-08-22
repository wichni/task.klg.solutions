package pl.jakubwichniarek.task.klg.solutions.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.jakubwichniarek.task.klg.solutions.exception.ServiceException;
import pl.jakubwichniarek.task.klg.solutions.exception.ResourceNotFoundException;
import pl.jakubwichniarek.task.klg.solutions.model.ErrorMessage;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AllControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<Object> handleServiceException(ServiceException ex) {
    return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
    return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
  }

  private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus status) {
    ErrorMessage errorMessage = new ErrorMessage(status, exception.getMessage());
    return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
  }
}
