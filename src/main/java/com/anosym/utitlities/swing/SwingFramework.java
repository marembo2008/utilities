/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utitlities.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Luffy
 */
public class SwingFramework {

    public static void toCentre(Component comp) {
        Dimension dd = Toolkit.getDefaultToolkit().getScreenSize(),
                d = comp.getSize();
        comp.setLocation((dd.width - d.width) / 2, (dd.height - d.height) / 2);
    }

    public static void toCentre(Component comp, boolean resizeToScreen) {
        Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension resD = new Dimension(dd.width * 3 / 4, dd.height * 3 / 4);
        comp.setPreferredSize(resD);
        comp.setSize(resD);
        comp.setMinimumSize(resD);
        comp.setMaximumSize(resD);
        Dimension d = comp.getPreferredSize();
        int w = dd.width - d.width, h = dd.height - d.height;
        if (w > 10) {
            w += 10;
        } else {
            d.width -= 10;
            comp.setPreferredSize(d);
            w = dd.width - d.width;
        }
        if (h > 10) {
            h += 10;
        } else {
            d.height -= 10;
            comp.setPreferredSize(d);
            h = dd.height - d.height;
        }
        comp.setLocation(w / 2, h / 2);
    }

    /**
     * Does nothing if there is no parent which is an instance of JDialog
     * @param child
     */
    public static void closeJDialog(Component child) {
        Container c = child.getParent();
        while (c != null && !(c instanceof JDialog)) {
            c = c.getParent();
        }
        if (c != null && c instanceof JDialog) {
            ((JDialog) c).dispose();
        }
    }

    public static void printStackTrace(Throwable ex) {
        String s = ex.toString() + "\n";
        StackTraceElement[] trace = ex.getStackTrace();
        for (int i = 0; i < trace.length; i++) {
            s += ("\n\tat " + trace[i]);
        }

        Throwable ourCause = ex.getCause();
        s += "\n\n";
        if (ourCause != null) {
            s += printStackTraceAsCause(trace, ourCause);
        }
        s = s.replaceAll("\n", "<br/>");
        s = "<html>" + s + "</html>";
        String[] errors = s.split("<br/>");
        List<String> errs = Arrays.asList(errors);
        ErrorPanel panel = new ErrorPanel();
        panel.setErrors(errs);
        final JDialog dd = new JDialog(new JFrame(), true);
        dd.getContentPane().setLayout(new BorderLayout());
        dd.setTitle("Exception Trace:");
        dd.getContentPane().add(panel, BorderLayout.CENTER);
        dd.pack();
        showDialog(dd, true);
    }

    public static void printStackTrace(Throwable ex, String error) {
        String s = "....................................\n";
        s += "\t\t" + error + "\t\t\n";
        s += "....................................\n";
        s = ex.toString() + "\n";
        StackTraceElement[] trace = ex.getStackTrace();
        for (int i = 0; i < trace.length; i++) {
            s += ("\n\tat " + trace[i]);
        }
        Throwable ourCause = ex.getCause();
        s += "\n\n";
        if (ourCause != null) {
            s += printStackTraceAsCause(trace, ourCause);
        }
        s = s.replaceAll("\n", "<br/>");
        s = "<html>" + s + "</html>";
        JLabel ll = new JLabel(s);
        JPanel pp = new JPanel();
        pp.add(ll);
        final JDialog dd = new JDialog(new JFrame(), true);
        dd.getContentPane().setLayout(new BoxLayout(dd.getContentPane(), BoxLayout.PAGE_AXIS));
        dd.setTitle("Exception Trace:");
        JScrollPane sp = new JScrollPane(pp);
        dd.getContentPane().add(sp);
        dd.pack();
        showDialog(dd, true);
    }

    public static String getStackStrace(Throwable ex) {
        String s = ex.toString() + "\n";
        StackTraceElement[] trace = ex.getStackTrace();
        for (int i = 0; i < trace.length; i++) {
            s += ("\n\tat " + trace[i]);
        }
        Throwable ourCause = ex.getCause();
        s += "\n\n";
        if (ourCause != null) {
            s += printStackTraceAsCause(trace, ourCause);
        }
        return s;
    }

    private static String printStackTraceAsCause(StackTraceElement[] causedTrace, Throwable e) {
        // assert Thread.holdsLock(s);
        String s = "";
        // Compute number of frames in common between this and caused
        StackTraceElement[] trace = e.getStackTrace();
        int m = trace.length - 1, n = causedTrace.length - 1;
        while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
            m--;
            n--;
        }
        int framesInCommon = trace.length - 1 - m;

        s += ("Caused by: " + e);
        for (int i = 0; i <= m; i++) {
            s += ("\n\tat " + trace[i]);
        }
        if (framesInCommon != 0) {
            s += ("\n\t... " + framesInCommon + " more");
        }

        // Recurse if we have a cause
        Throwable ourCause = e.getCause();
        s += "\n";
        if (ourCause != null) {
            s += printStackTraceAsCause(trace, ourCause);
        }
        return s;
    }

    public static void informationMessage(String info) {
        JOptionPane.showMessageDialog(null, info, "Information", JOptionPane.ERROR_MESSAGE);
    }

    public static void errorMessage(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void errorMessage(Exception error) {
        JOptionPane.showMessageDialog(null, error.getLocalizedMessage());
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void showMessage(Object message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void showAccessDeniedMessage() {
        showMessage("Permission Denied");
    }

    public static void showDialog(Component panel) {
        SwingFramework.toCentre(panel);
        if (panel instanceof JDialog) {
            ((JDialog) panel).setVisible(true);
        } else {
            Container cc = null;
            Container _cc = (Container) panel;
            do {
                cc = _cc.getParent();
                if (cc == null || (cc instanceof JDialog)) {
                    break;
                } else {
                    _cc = cc;
                }
            } while (true);
            if (cc == null) {
                cc = new JDialog(new JFrame(), true);
                ((JDialog) cc).addWindowListener(new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
            }
            JDialog d = (JDialog) cc;
            d.getContentPane().add(panel);
            d.pack();
            SwingFramework.toCentre(d);
            d.setVisible(true);
        }
    }

    public static void showDialog(Component panel, boolean resizeToFit) {
        SwingFramework.toCentre(panel, resizeToFit);
        if (panel instanceof JDialog) {
            ((JDialog) panel).setVisible(true);
        } else {
            JDialog d = new JDialog(new JFrame(), true);
            d.getContentPane().add(panel);
            d.pack();
            SwingFramework.toCentre(d);
            d.setVisible(true);
        }
    }

    public static void showDialog(Component panel, String title) {
        SwingFramework.toCentre(panel);
        if (panel instanceof JDialog) {
            ((JDialog) panel).setVisible(true);
            ((JDialog) panel).setTitle(title);
        } else {
            JDialog d = new JDialog(new JFrame(), true);
            d.getContentPane().add(panel);
            d.setTitle(title);
            d.pack();
            SwingFramework.toCentre(d);
            d.setVisible(true);
        }
    }

    public static void restartApplication(File path, String execFile) throws Exception {
        try {
            final ArrayList<String> command = new ArrayList<String>();
            command.add("javaw");
            command.add(path.getAbsolutePath() + File.separator + execFile);
            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
            System.exit(0);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void invalidateTree(Container aThis) {
        Container cn = aThis.getParent();
        if (cn == null) {
            aThis.invalidate();
        }
    }

    public static void validateTree(Container aThis) {
        Container cn = aThis.getParent();
        if (cn == null) {
            aThis.validate();
        }
    }
}
