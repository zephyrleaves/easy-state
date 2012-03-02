package com.github.easystate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 状态变更路径(弧度)
 *
 * @author <a href="mailto:zephyrleaves@gmail.com"></a>
 * @since 2010-4-13 13:48:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Next {
	/**
	 * 有向弧度 (指明事件->状态) <code>DOXXX->END</code>
	 *
	 * @return String
	 */
	String[] arc() default {};
}
