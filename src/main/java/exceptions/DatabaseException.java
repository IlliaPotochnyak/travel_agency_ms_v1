package exceptions;

//import constant.Path;
import exceptions.ApplicationException;

public class DatabaseException extends ApplicationException {

    public DatabaseException() {
        super();
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
//        setForward("/index.jsp");
    }

}