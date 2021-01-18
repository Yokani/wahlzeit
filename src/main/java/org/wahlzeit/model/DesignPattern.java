package org.wahlzeit.model;

import java.lang.annotation.Repeatable;

@Repeatable(DesignPatterns.class)
public @interface DesignPattern {
    String name() default "";
    String[] participants() default {};
}