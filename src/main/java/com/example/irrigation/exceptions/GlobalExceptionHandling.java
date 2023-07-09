package com.example.irrigation.exceptions;

import com.example.irrigation.dto.response.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<Object> handleSupermarketNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error =
        new ErrorResponse("Resource Not Found", HttpStatus.NOT_FOUND.toString(), details);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RecordNotFoundException.class)
  public final ResponseEntity<Object> handleRecordNotFoundException(
      RecordNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error =
        new ErrorResponse("Record Not Found", HttpStatus.NOT_FOUND.toString(), details);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex, WebRequest request) {
    List<String> details = new ArrayList<String>();
    details.add(ex.getLocalizedMessage());

    ErrorResponse error =
        new ErrorResponse("Validation Failed", HttpStatus.BAD_REQUEST.toString(), details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorResponse error =
        new ErrorResponse("Validation Failed", HttpStatus.BAD_REQUEST.toString(), details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      MissingServletRequestPartException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());

    ErrorResponse error =
        new ErrorResponse("missing parameter", HttpStatus.BAD_REQUEST.toString(), details);
    return handleExceptionInternal(ex, error, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());

    ErrorResponse error =
        new ErrorResponse("missing parameter", HttpStatus.BAD_REQUEST.toString(), details);
    return handleExceptionInternal(ex, error, headers, status, request);
  }


}
