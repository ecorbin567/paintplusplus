package view.TopMenuBar.FileMenu;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;
import view.CanvasView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveAsButton implements ActionListener {
    private final ToolEnum toolName = ToolEnum.SAVE;
    private final JMenuItem menuItem;
    private final CanvasController canvasController;
    private final CanvasView canvasView;

    public SaveAsButton(CanvasView canvasView, CanvasController canvasController) {
        this.canvasView = canvasView;
        this.canvasController = canvasController;
        menuItem = new JMenuItem("Save As");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setActionCommand("saveAs");
        menuItem.addActionListener(this);
    }
    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public void actionPerformed(ActionEvent e) {
        BufferedImage image = this.canvasView.getImage();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setSelectedFile(new File(System.getProperty("user.dir") + "/untitled.png"));
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file.getName().endsWith(".png")) {
                file = new File(file.getAbsolutePath() + ".png");
            }
            this.canvasController.handleSaveButtonPress(toolName, image, file);
        }
        else {
            System.out.println("No file selected");
        }
        }
    }
