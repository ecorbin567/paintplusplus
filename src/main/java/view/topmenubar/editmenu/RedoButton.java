package view.topmenubar.editmenu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

public class RedoButton {
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
