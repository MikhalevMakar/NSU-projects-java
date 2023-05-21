package ru.nsu.org.mikhalev.server.commands;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface CommandServer {}
