package ua.com.owu.sep2021javaadv.customErrors;

public class CustomError extends RuntimeException {
    public CustomError(String message) {
        super(message);
    }
}
