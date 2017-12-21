package org.framework.mvc.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//定义在类
@Retention(RetentionPolicy.RUNTIME)//允许时保存
public @interface MyFilterAnn {
    String value();
    int order();
}
