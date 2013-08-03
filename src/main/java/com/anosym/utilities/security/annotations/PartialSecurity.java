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
 * Implies that this class is partially secure. The security manager will only look for secured
 * fields.
 *
 * @author marembo
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE})
public @interface PartialSecurity {
}
