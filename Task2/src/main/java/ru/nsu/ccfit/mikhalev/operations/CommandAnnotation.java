package ru.nsu.ccfit.mikhalev.operations;

<<<<<<<< HEAD:Task2/src/main/java/ru/nsu/ccfit/mikhalev/operations/CommandAnnotation.java
package ru.nsu.ccfit.mikhalev.operations;
========
package ru.nsu.org.mikhalev.clients.commands;
>>>>>>>> 525fa0b (fix bag):Task5/src/main/java/ru/nsu/org/mikhalev/clients/commands/CommandClients.java

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
<<<<<<<< HEAD:Task2/src/main/java/ru/nsu/ccfit/mikhalev/operations/CommandAnnotation.java
public @interface CommandAnnotation {
}
========
public @interface CommandClients {}
>>>>>>>> 525fa0b (fix bag):Task5/src/main/java/ru/nsu/org/mikhalev/clients/commands/CommandClients.java
