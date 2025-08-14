package view.midmenubar.imagebar;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import interface_adapter.image.ImageFacade;
import interface_adapter.image.import_image.ImportController;
import view.DrawingView;

public class ImportButton {

    private final JButton button;
    private final ImportController importController;

    public ImportButton(ImageFacade controller, DrawingView drawingView) {
        this.importController = controller.getImportController();
        button = new JButton();

        final URL url = Objects.requireNonNull(
                ImportButton.class.getResource("/images/ImageIcon.png"),
                "Missing resource: /images/ImageIcon.png"
        );
        final ImageIcon icon = new ImageIcon(url);

        final Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(e -> {
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import PNG or PDF");

            final FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Files", "png");
            fileChooser.setFileFilter(filter);

            final int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = fileChooser.getSelectedFile();
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
