package org.framework.beans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//定义在类上
@Retention(RetentionPolicy.RUNTIME)//允许时保存
public @interface Component {
    String value();
}
