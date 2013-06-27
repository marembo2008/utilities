/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utitlities.swing;

import java.util.EventObject;

/**
 *
 * @author variance
 */
public class SlideEvent extends EventObject {

    private SlideButton slideButton;
    private boolean slided;

    public SlideEvent(Object source) {
        super(source);
        if (!(source instanceof SlideButton)) {
            throw new IllegalArgumentException("Invalid Source");
        }
        this.slideButton = (SlideButton) source;
        this.slided = true;
    }

    public SlideEvent(Object source, boolean slided) {
        this(source);
        this.slided = slided;
    }

    public boolean isSlided() {
        return slided;
    }
    
}
