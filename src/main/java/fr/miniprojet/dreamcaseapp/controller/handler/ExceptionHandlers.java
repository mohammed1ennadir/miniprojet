package fr.miniprojet.dreamcaseapp.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.miniprojet.dreamcaseapp.exception.NotFoundException;
import lombok.Getter;

@ControllerAdvice
public class ExceptionHandlers {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseError handleException(final Exception ex) {

		return new ResponseError("500", ex.getMessage());
	}

	@ExceptionHandler({ NotFoundException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseError handleExceptioNoutFount(final Exception ex) {
		return new ResponseError(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseError handle(final MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		StringBuilder st = new StringBuilder();
		for (ObjectError oe : result.getAllErrors()) {
			st.append(oe.getDefaultMessage() + " \n");
		}

		return new ResponseError("500", st.toString());
	}

	@Getter
	public static class ResponseError {
		private final String code;
		private final String message;

		private ResponseError(String code, String message) {
			super();
			this.code = code;
			this.message = message;
		}

	}
}
