package pl.pollub.exception;

public class MyoConnectNotFoundException extends HubNotFoundException {
    public MyoConnectNotFoundException(String message) {
        super(message);
    }


    public MyoConnectNotFoundException() {
    }


    public MyoConnectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public MyoConnectNotFoundException(Throwable cause) {
        super(cause);
    }


    protected MyoConnectNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}