package view.MidMenuBar.ImageBar;

import interface_adapter.image.import_image.ImportController;
import view.CanvasView;
import view.MidMenuBar.SelectionToolButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class ImportButton {

    private final JButton button;

    public ImportButton(ImportController controller, CanvasView canvasView) {
        button = new JButton();

        ImageIcon icon = new ImageIcon(SelectionToolButton.class.getResource("/images/ImageIcon.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import PNG or PDF");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Files", "png");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                controller.execute(selectedFile);
                canvasView.repaint();
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}
