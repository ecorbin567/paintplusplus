package view.midmenubar.colorbuttonsbar;

import javax.swing.*;
import java.awt.*;

public class CircularButton extends JToggleButton {

    public CircularButton(int diameter) {
        setPreferredSize(new Dimension(diameter, diameter));

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setMargin(new Insets(0, 0, 0, 0));

        setRolloverEnabled(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        // fill the circle
        g2.setColor(getBackground());
        g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // draw the outline
        g2.setColor(Color.BLACK);
        g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);

        // if the mouse is over the button, draw light grey halo (not working right now)
        if (getModel().isRollover()) {
            g2.setColor(new Color(200, 200, 200, 180));
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(-2, -2, getWidth() + 4, getHeight() + 4);
        }
        if (getModel().isSelected()) {
            g2.setColor(new Color(120, 120, 120, 200));
            g2.setStroke(new BasicStroke(4));
            g2.drawOval(-3, -3, getWidth() + 6, getHeight() + 6);
        }
        // manually paint the icon
        Icon icon = getIcon();
        if (icon != null) {
            int x = (getWidth() - icon.getIconWidth()) / 2;
            int y = (getHeight() - icon.getIconHeight()) / 2;
            icon.paintIcon(this, g2, x, y);
        }

        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        //true if only coordinates inside the drawn circle
        int radius = getWidth() / 2;
        int dx = x - radius, dy = y - radius;
        return dx * dx + dy * dy <= radius * radius;
    }
    @Override
    public void update(Graphics g){
        paintComponent(g);
    }
}
