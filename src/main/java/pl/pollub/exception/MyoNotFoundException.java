package pl.pollub.exception;

public class MyoNotFoundException extends RuntimeException {
    public MyoNotFoundException(String message) {
        super(message);
    }


    public MyoNotFoundException() {
    }


    public MyoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public MyoNotFoundException(Throwable cause) {
        super(cause);
    }


    protected MyoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}