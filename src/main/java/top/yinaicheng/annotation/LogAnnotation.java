package top.yinaicheng.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义日志注解
 * @author yinaicheng
 */
/*注解的作用目标为方法、类、接口或枚举类型*/
@Target({ElementType.TYPE,ElementType.METHOD})
/*注解会在class字节码文件中存在，在运行时可以通过反射获取到*/
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation
{

    /**
     * 日志描述
     */
    String value() default "";

}
