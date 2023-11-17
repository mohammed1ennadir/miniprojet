package fr.miniprojet.dreamcaseapp.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    private final String code;

    public NotFoundException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
    }
    public NotFoundException(String errorMessage) {
		super(errorMessage);
        this.code = "404";
    }

}
