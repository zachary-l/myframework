package org.framework.dbutil;

public class SQLExectorException extends RuntimeException {
    public SQLExectorException(String message) {
        super(message);
    }

    public SQLExectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLExectorException(Throwable cause) {
        super(cause);
    }
}
