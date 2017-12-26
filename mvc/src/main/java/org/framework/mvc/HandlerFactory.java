package org.framework.mvc;

public interface HandlerFactory {
    Object createHandler(HandlerDefinition definition);
}
