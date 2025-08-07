package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;


public class LowerColorChooserButton extends CircularButton{
    private final ToolEnum toolName = ToolEnum.CHANGECOLOR;
    private final CanvasController canvasController;
    private Color lowerCurrentColor = Color.WHITE;

    public LowerColorChooserButton(CanvasController canvasController){
        super(30);
        this.canvasController = canvasController;
        setBackground(lowerCurrentColor);

        addActionListener(e->{
            this.canvasController.handleChangeColorButtonPress(toolName, );
        });

    }

    public Color getLowerCurrentColor(){
        return this.lowerCurrentColor;
    }

    public void setCurrentColor(Color c){
        this.lowerCurrentColor = c;
        setBackground(c);
    }

    public JToggleButton getButton(){
        return this;
    }
}
