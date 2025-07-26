package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;

public class CircularButton extends JButton{

    public CircularButton(int diameter) {
        setPreferredSize(new Dimension(diameter, diameter));
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // fill the circle
        g2.setColor(getBackground());
        g2.fillOval(0,0,getWidth(),getHeight());
        // draw the outline
        g2.setColor(Color.BLACK);
        g2.drawOval(0,0,getWidth()-1, getHeight()-1);

        g2.dispose();
    }
    @Override
    public boolean contains(int x, int y){
        //true if only coordinates inside the drawn circle
        int radius = getWidth() / 2;
        int dx = x - radius, dy = y - radius;
        return dx*dx + dy*dy <= radius*radius;
    }
}
