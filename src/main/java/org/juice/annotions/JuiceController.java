package org.juice.annotions;

import org.juice.web.res.WebResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识此注解的类，返回结果将进行统一处理，包装为 {@link WebResponse} 对象返回
 *
 * @author wumin
 * @date 2024/05/22
 * @since 1.0.0
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JuiceController {
}
