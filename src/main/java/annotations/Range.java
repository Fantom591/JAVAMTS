package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    double min() default -Double.MAX_VALUE;
    double max() default Double.MAX_VALUE;
    String message() default "числовое значение" +
        " должно находиться в замкнутом диапазоне [min, max]";
}