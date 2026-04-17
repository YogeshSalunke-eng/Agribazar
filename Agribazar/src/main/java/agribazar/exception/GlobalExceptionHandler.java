package agribazar.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handleResourceExists(ResourceAlreadyExistException ex,
			HttpServletRequest request) {

		ErrorResponse error = ErrorResponse.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.CONFLICT.value()).error(HttpStatus.CONFLICT.getReasonPhrase())
				.message(ex.getMessage()).path(request.getRequestURI()).build();

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceExists(ResourceNotFoundException ex,
			HttpServletRequest request) {

		ErrorResponse error = ErrorResponse.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.CONFLICT.value()).error(HttpStatus.CONFLICT.getReasonPhrase())
				.message(ex.getMessage()).path(request.getRequestURI()).build();

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {

		ErrorResponse error = ErrorResponse.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Something went wrong")
				.path(request.getRequestURI()).build();

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
