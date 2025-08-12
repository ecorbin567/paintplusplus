package view.midmenubar.colorbuttonsbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class ColorWheelPanel extends JPanel{

    private final int radius;
    private final Point center;
    private Color selectedColor = Color.WHITE;
    private Point selectionPoint;

    public ColorWheelPanel(int size){
        setPreferredSize(new Dimension(size, size + 50));
        radius = size / 2 - 10;
        center = new Point(size / 2, size / 2);
        selectionPoint = null;

        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateSelectedColor(e.getPoint());
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSelectedColor(e.getPoint());
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    private void updateSelectedColor(Point mousePoint){
        double dx = mousePoint.x - center.x;
        double dy = mousePoint.y - center.y;
        double distance = center.distance(mousePoint);

        if (distance <= radius){
            selectionPoint = new Point(mousePoint);
            // hue based on angle
            double angle = Math.atan2(dy, dx);
            double hue = (angle + Math.PI) / (2 * Math.PI);
            // saturation based on distance from center
            double saturation = distance / radius;

            selectedColor = Color.getHSBColor((float) hue, (float) saturation, 1.0f);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d  = (Graphics2D) g.create();
        // anti alsiasing

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // code for the actual circle/wheel
        for (int x = -radius; x <= radius; x++){
            for (int y = -radius; y <= radius; y++){
                Point2D p = new Point2D.Double(x, y);
                double distance = p.distance(0, 0);

                if (distance <= radius){
                    double angle = Math.atan2(y, x);
                    double hue = (angle + Math.PI) / (2 * Math.PI);
                    double saturation = distance / radius;
                    Color color = Color.getHSBColor((float) hue, (float) saturation, 1.0f);
                    g2d.setColor(color);
                    g2d.fillRect(center.x + x, center.y + y, 1, 1);

                }
            }
        }
        // make circle indicating selected color
        g2d.setColor(Color.BLACK);
        g2d.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
        // draw the click marker
        if (selectionPoint != null) {
            g2d.setColor(Color.BLACK);
            int m = 6;
            g2d.drawOval(selectionPoint.x - m/2, selectionPoint.y - m/2, m, m);
        }
        // draw selected color indicator
        g2d.setColor(selectedColor);
        g2d.fillOval(center.x - 10, center.y + radius + 10, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Selected Color", center.x - 40, center.y + radius + 45);

        g2d.dispose();
    }

    public Color getSelectedColor(){
        return this.selectedColor;
    }

}
