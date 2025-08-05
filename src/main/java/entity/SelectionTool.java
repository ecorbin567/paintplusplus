package entity;

import java.awt.*;

public class SelectionTool implements Drawable {

    private Point startPoint; // start point of the selection on the canvas
    private Point currentPoint; // end point of the selection on the canvas

    public SelectionTool() {}

    public void start(Point p){
        startPoint = p;
        currentPoint = p;
    }
    public void drag(Point p){
        currentPoint = p;
    }
    public void finish(Point p){
        currentPoint = p;
    }
    public void cancel(){
        startPoint = null;
        currentPoint = null;
    }
    public Rectangle getBounds() {
        if (startPoint == null || currentPoint == null) {
            return new Rectangle();
        }
        int x = Math.min(startPoint.x, currentPoint.x);
        int y = Math.min(startPoint.y, currentPoint.y);
        int w = Math.abs(startPoint.x - currentPoint.x);
        int h = Math.abs(startPoint.y - currentPoint.y);
        return new Rectangle(x, y, w, h);
    }
    public void render(Graphics2D g){
        Rectangle r = getBounds();
        if (r.width > 0 && r.height > 0){
            // save old stroke
            Stroke old = g.getStroke();
            //force teh 1px stroke
            g.setStroke(new BasicStroke(1f));
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(r.x, r.y, r.width, r.height);

            g.setStroke(old);
        }
    }


//        addMouseListener(new MouseAdapter() {
//            @Override
//             public void mousePressed(MouseEvent e) {
//                startPoint = e.getPoint();
//                currentPoint = startPoint;
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                currentPoint = e.getPoint();
//                // below not sure if needs change, but remove temp drawing box when mouse released
//                startPoint = null;
//                currentPoint = null;
//                repaint();
//            }
//        });
//
//        addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                currentPoint = e.getPoint();
//                repaint();
//            }
//        });
//    }
//    @Override
//    protected void paintComponent(Graphics g){
//        super.paintComponent(g);
//
//        if (startPoint!=null && currentPoint!=null) {
//            int x = Math.min(startPoint.x, currentPoint.x);
//            int y = Math.min(startPoint.y, currentPoint.y);
//            int width = Math.abs(startPoint.x - currentPoint.x);
//            int height = Math.abs(startPoint.y - currentPoint.y);
//
//            g.setColor(Color.lightGray);
//            g.drawRect(x, y, width, height);
//
//        }
//    }

}
