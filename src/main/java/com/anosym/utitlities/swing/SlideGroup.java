/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utitlities.swing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author variance
 */
public class SlideGroup {

    private List<SlideButton> slideButtons;

    public SlideGroup() {
        this.slideButtons = new ArrayList<SlideButton>();
    }

    public void addSlideButton(SlideButton button) {
        if (!slideButtons.contains(button)) {
            this.slideButtons.add(button);
        }
    }

    public void addSlideListener(SlideListener listener, SlideButton button) {
    }

    public void fireSlideListener(SlideEvent event) {
    }
    
}
