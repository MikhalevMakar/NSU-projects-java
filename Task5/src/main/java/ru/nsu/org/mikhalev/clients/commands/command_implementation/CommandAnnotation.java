package ru.nsu.org.mikhalev.clients.commands.command_implementation;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface CommandAnnotation { }