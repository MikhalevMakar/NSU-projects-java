package org.ru.nsu.mikhalev.task2.Operations;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface CommandAnnotation {
}
