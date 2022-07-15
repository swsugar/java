package kr.or.ddit.sample.stereotype;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Retention(RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface ViewLayer {

}
