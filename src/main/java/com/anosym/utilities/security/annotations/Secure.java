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
 * This annotation tells the security manager that the class or the the field is to be encrypted. If
 * specified on a field, the class must be annotated with {@link PartialSecurity} so that all other
 * fields are ignored, but the ones marked secure. By default, if a class is not annotated
 *
 * @Ignore, and there is no field marked
 * @Ignore, then the security manager assumes that all fields are secure.
 * @author marembo
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD})
public @interface Secure {
}
