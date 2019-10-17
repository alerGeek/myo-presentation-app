package pl.pollub.exception;

public class HubNotFoundException extends com.thalmic.myo.exception.HubNotFoundException {
    public HubNotFoundException(String message) {
        super(message);
    }


    public HubNotFoundException() {
    }


    public HubNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public HubNotFoundException(Throwable cause) {
        super(cause);
    }


    protected HubNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}