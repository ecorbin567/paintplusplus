package view.MidMenuBar.ImageBar;

import interface_adapter.midmenu.image.ImageFacade;
import interface_adapter.midmenu.image.import_image.ImportController;
import view.DrawingView;
import view.MidMenuBar.SelectionToolButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class ImportButton {

    private final JButton button;
    private ImportController importController;

    public ImportButton(ImageFacade controller, DrawingView drawingView) {
        this.importController = controller.getImportController();
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
