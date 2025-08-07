package view.MidMenuBar.ColorButtonsBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;


public class UpperColorChooserButton extends CircularButton{
    private final ToolEnum toolName = ToolEnum.CHANGECOLOR;
    private final CanvasController canvasController;
    private Color upperCurrentColor = Color.BLACK;

    public UpperColorChooserButton(CanvasController canvasController){
        super(30);
        this.canvasController = canvasController;
        setBackground(upperCurrentColor); // default color


        addActionListener(e->{
            this.canvasController.handleChangeColorButtonPress(toolName, );
        });

    }

    public Color getUpperCurrentColor(){
        return this.upperCurrentColor;
    }

    public void setCurrentColor(Color c){
        this.upperCurrentColor = c;
        setBackground(c);
    }

    public JToggleButton getButton(){
        return this;
    }
}
