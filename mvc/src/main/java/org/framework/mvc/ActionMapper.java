package org.framework.mvc;

/**
 * 为了降低HandlerInvoker里面方法的耦合度
 */
public class ActionMapper {

	private HandlerDefinition definition;
	//方法
	private Object target;
	private Object[] params;

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public HandlerDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(HandlerDefinition definition) {
		this.definition = definition;
	}
}
