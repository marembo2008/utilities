/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.security.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If found on a class or field, or a collection for which its members annotated with this
 * annotation, the object will not be encrypted.
 *
 * @author marembo
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Ignore {
}
