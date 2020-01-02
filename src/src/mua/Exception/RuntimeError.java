package src.mua.Exception;

public class RuntimeError extends RuntimeException {
    public RuntimeError(String message) {
        super(message);
    }

    public RuntimeError() {

    }
}