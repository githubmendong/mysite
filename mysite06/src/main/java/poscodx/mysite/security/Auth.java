package poscodx.mysite.security;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Auth {
	// public String value() default "";
	public String Role() default "USER";
	public boolean test() default false;
}
