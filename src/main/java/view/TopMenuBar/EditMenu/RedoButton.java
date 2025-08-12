package view.TopMenuBar.EditMenu;

import interface_adapter.topmenu.TopMenuFacade;
import interface_adapter.topmenu.TopMenuFacadeImpl;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RedoButton{
    private final JMenuItem menuItem;

    public RedoButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(evt -> {
            controller.redo();
            drawingView.repaint();
        });
        menuItem.setActionCommand("redo");
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
