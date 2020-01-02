package src.mua.Exception;

public class ParseError extends RuntimeException {
    public ParseError(String message) {
        super(message);
    }

    public ParseError() {

    }
}