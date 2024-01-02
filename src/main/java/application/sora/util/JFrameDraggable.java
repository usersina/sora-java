package application.sora.util;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 * Extend from any JFrame to make it draggable.
 */
public abstract class JFrameDraggable extends JFrame {
    Point mouseDownCompCoords;

    public JFrameDraggable() {
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }

            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
    }
}
