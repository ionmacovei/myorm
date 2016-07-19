package intership.jdbc.anotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
public @interface Entity { 
	
	String name() default "";

	String shema() default "";

}
