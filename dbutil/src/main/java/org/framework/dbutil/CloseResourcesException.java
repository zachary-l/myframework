package org.framework.dbutil;

public class CloseResourcesException extends SQLExectorException {
    public CloseResourcesException(String message) {
        super(message);
    }

    public CloseResourcesException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloseResourcesException(Throwable cause) {
        super(cause);
    }
}
