/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utitlities.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author variance
 */
public class SlideButton extends JButton {

    private char slidedCharacter;
    private char unslidedCharacter;
    private boolean slided;

    public SlideButton(String text, Icon icon, char slidedCharacter, char unslidedCharacter) {
        super(text, icon);
        this.slidedCharacter = slidedCharacter;
        this.unslidedCharacter = unslidedCharacter;
    }

    public SlideButton(Action a, char slidedCharacter, char unslidedCharacter) {
        super(a);
        this.slidedCharacter = slidedCharacter;
        this.unslidedCharacter = unslidedCharacter;
    }

    public SlideButton(String text, char slidedCharacter, char unslidedCharacter) {
        super(text);
        this.slidedCharacter = slidedCharacter;
        this.unslidedCharacter = unslidedCharacter;
    }

    public SlideButton(Icon icon, char slidedCharacter, char unslidedCharacter) {
        super(icon);
        this.slidedCharacter = slidedCharacter;
        this.unslidedCharacter = unslidedCharacter;
    }

    public SlideButton(char slidedCharacter, char unslidedCharacter) {
        this.slidedCharacter = slidedCharacter;
        this.unslidedCharacter = unslidedCharacter;
    }

    public SlideButton() {
        this.unslidedCharacter = '+';
        this.slidedCharacter = '-';
        this.slided = false;
    }

    public char getSlidedCharacter() {
        return slidedCharacter;
    }

    public void setSlidedCharacter(char slidedCharacter) {
        this.slidedCharacter = slidedCharacter;
        this.repaint();
    }

    public char getUnslidedCharacter() {
        return unslidedCharacter;
    }

    public void setUnslidedCharacter(char unslidedCharacter) {
        this.unslidedCharacter = unslidedCharacter;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            char c = slided ? this.slidedCharacter : unslidedCharacter;
            Graphics2D gd = (Graphics2D) g;
            Dimension d = this.getSize();
            LineMetrics fm = this.getFont().getLineMetrics("" + c, gd.getFontRenderContext());
            float h = fm.getHeight();
            float sh = (float) d.getHeight();
            //find how much space is left between the character height and the buttons height
            //then we draw from half of this
            float dh = sh - h;
            if (dh > 0) {
                dh /= 2;
                //draw
                gd.drawString("" + c, 2.0f, h);
            }
        } catch (Exception e) {
        }
    }
}
