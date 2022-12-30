package exceptions;

public class ApplicationException extends Exception{

    private String forward;

    public ApplicationException() {}

    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException(String message, String forward) {
        super(message);
        this.forward = forward;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApplicationException(String message, Throwable cause, String forward) {
        super(message, cause);
        this.forward = forward;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }
}
