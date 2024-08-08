package com.example.demo.exception;

import com.example.demo.dto.response.ErrorResponseDto;
import com.example.demo.exception.domain.AppIdErrorException;
import com.example.demo.exception.domain.BadRequestException;
import com.example.demo.exception.domain.PermissionException;
import com.example.demo.exception.domain.VerificationErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.demo.constant.ExceptionConstant.*;


@Slf4j
@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
    public static final String ERROR_PATH = "/error";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponseDto> accountDisabledException() {
        return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> badCredentialsException() {
        return createHttpResponse(HttpStatus.BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> accessDeniedException() {
        return createHttpResponse(HttpStatus.FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorResponseDto> lockedException() {
        return createHttpResponse(HttpStatus.UNAUTHORIZED, ACCOUNT_LOCKED_MSG);
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handle(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining());
        return createHttpResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(MissingServletRequestParameterException .class)
    public ResponseEntity<ErrorResponseDto> handle(MissingServletRequestParameterException ex) {
        log.info("EX: {}", (Object[]) ex.getStackTrace());
        log.info("EX: {}", ex.getStackTrace().toString());
        log.info("EX: {}", Arrays.toString(ex.getStackTrace()));
        String customMessage = String.format("Parameter %s dibutuhkan", ex.getParameterName());
        return createHttpResponse(HttpStatus.BAD_REQUEST, customMessage);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException exception) {
        log.info("EX: {}", exception.getStackTrace().toString());
        log.info("EX: {}", Arrays.toString(exception.getStackTrace()));
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(VerificationErrorException.class)
    public ResponseEntity<ErrorResponseDto> verificationErrorException(VerificationErrorException exception) {
        return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(AppIdErrorException.class)
    public ResponseEntity<ErrorResponseDto> appIdErrorException(AppIdErrorException exception) {
        log.info("EX: {}", exception.getStackTrace().toString());
        log.info("EX: {}", Arrays.toString(exception.getStackTrace()));
        return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<ErrorResponseDto> handlePermissionException(PermissionException exception) {
        return createHttpResponse(HttpStatus.FORBIDDEN, exception.getMessage());
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED_MSG, supportedMethod));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> internalServerErrorException(Exception exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResponseDto> notFoundException(NoResultException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponseDto> iOException(IOException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<ErrorResponseDto> notFound404() {
        return createHttpResponse(HttpStatus.NOT_FOUND, "There is no mapping for this URL");
    }

    public String getErrorPath() {
        return ERROR_PATH;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                ex.getBindingResult()
        );
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    private ResponseEntity<ErrorResponseDto> createHttpResponse(HttpStatus httpStatus, String message) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(httpStatus, message);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
