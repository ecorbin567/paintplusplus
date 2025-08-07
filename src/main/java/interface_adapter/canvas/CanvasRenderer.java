package interface_adapter.canvas;

import entity.Drawable;
import entity.StrokeRecord;

import java.awt.*;
import java.util.Stack;

public class CanvasRenderer {
    public void renderDraw(Graphics2D graphics2D, CanvasViewModel canvasViewModel){
        if(canvasViewModel.getRepaintState()){
            Stack<Drawable> drawables = canvasViewModel.getDrawables();
            for (Drawable drawable: drawables) {
                if (drawable instanceof StrokeRecord strokeRecord){
                    graphics2D.setColor(strokeRecord.colour);
                    graphics2D.setStroke(new BasicStroke(strokeRecord.width,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND));

                    for (int i = 1; i < strokeRecord.pts.size(); i++){
                        Point p1 = strokeRecord.pts.get(i - 1);
                        Point p2 = strokeRecord.pts.get(i);
                        graphics2D.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }

                else if (drawable instanceof entity.Image image){
                    image.draw(graphics2D);
                }
            }
        }
    }


}
