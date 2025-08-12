package view.midmenubar.imagebar;

import interface_adapter.image.ImageFacade;
import interface_adapter.image.import_image.ImportController;
import view.DrawingView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Objects;

public class ImportButton {

    private final JButton button;
    private final ImportController importController;

    public ImportButton(ImageFacade controller, DrawingView drawingView) {
        this.importController = controller.getImportController();
        button = new JButton();

        URL url = Objects.requireNonNull(
                ImportButton.class.getResource("/images/wheel.png"),
                "Missing resource: /images/wheel.png"
        );
        ImageIcon icon = new ImageIcon(url);

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
                controller.importImage(selectedFile);
                drawingView.repaint();
            }
        });
    }

    public JButton getButton() {
        return button;
    }

    public ImportController getController() {
        return importController;
    }
}
