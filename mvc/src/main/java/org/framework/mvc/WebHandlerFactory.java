package org.framework.mvc;

public class WebHandlerFactory implements HandlerFactory {
    @Override
    public Object createHandler(HandlerDefinition definition) {
        Object instance = null;
        if (definition!= null) {
            try {
                instance = definition.getClazz().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
