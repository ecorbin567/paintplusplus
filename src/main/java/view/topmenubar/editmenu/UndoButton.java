package view.topmenubar.editmenu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

public class UndoButton {
    private final JMenuItem menuItem;

    public UndoButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem("Undo");
        menuItem.setMnemonic(KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Undo");
        menuItem.addActionListener(evt -> {
            controller.undo();
            drawingView.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
