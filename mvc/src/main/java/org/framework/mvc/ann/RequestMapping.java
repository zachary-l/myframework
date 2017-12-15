package org.framework.mvc.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})//定义在类和方法上
@Retention(RetentionPolicy.RUNTIME)//允许时保存
public @interface RequestMapping {
    String value();
}
