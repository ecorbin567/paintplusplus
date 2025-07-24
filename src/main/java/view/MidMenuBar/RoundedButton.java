package view.MidMenuBar;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedButton implements Border {

    private final int radius;

    public RoundedButton(int radius){
        this.radius = radius;

    }
    public Insets getBorderInsets(Component c){
        return new Insets(this.radius+1, this.radius+1,
                this.radius+2, this.radius+2);
    }
    public boolean isBorderOpaque(){
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
        g.drawRoundRect(x,y,width-1,height-1,radius,radius);
    }
}
