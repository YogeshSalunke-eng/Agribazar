package agribazar.exception;

import java.time.LocalDateTime;

@lombok.Data
@lombok.Builder
public class ErrorResponse {
	private int status;
	private LocalDateTime timestamp;
	private String error;
	private String message;
	private String path;
}
